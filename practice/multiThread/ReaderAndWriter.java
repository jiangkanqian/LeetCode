package multiThread;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
 
/**
 * 用ReadWriteLock读写锁来实现一个高效的Map缓存
 * Created by JKQ on 2018/7/30.
 * 在Java中Lock接口比synchronized块的优势是什么？
 * 你需要实现一个高效的缓存，它允许多个用户读，但只允许一个用户写，以此来保持它的完整性，你会怎样去实现它？
 */
public class ReaderAndWriter<K, V> {
    private final ReadWriteLock lock = new ReentrantReadWriteLock();
    private final Lock readLock = lock.readLock();
    private final Lock writeLock = lock.writeLock();
    private final Map<K, V> map;
 
    public ReaderAndWriter(Map<K, V> map) {
        this.map = map;
    }
 
    /*************   这是用lock()方法写的   ********************/
//    public V put(K key, V value){
//        writeLock.lock();
//        try {
//            return map.put(key, value);
//        }finally {
//            writeLock.unlock();
//        }
//    }
//    public V get(K key){
//        readLock.lock();
//        try {
//            return map.get(key);
//        }finally {
//            readLock.unlock();
//        }
//    }
 
    /*************   这是用tryLock()方法写的   ********************/
    public V put(K key, V value){
        while (true){
            if(writeLock.tryLock()){
                try {
                    System.out.println("put "+ key +" = " + value);
                    return map.put(key, value);
                }finally {
                    writeLock.unlock();
                }
            }
        }
    }
    public V get(K key){
        while (true){
            if (readLock.tryLock()) {
                try {
                    V v = map.get(key);
                    System.out.println("get "+ key +" = " + v);
                    return v;
                } finally {
                    readLock.unlock();
                }
            }
        }
    }
 
 
    /********************    下面是测试区       *********************************/
    public static void main(String[] args) {
        final ReaderAndWriter<String, Integer> rw = new ReaderAndWriter<>(new HashMap<>());
        ExecutorService exec = Executors.newCachedThreadPool();
        for (int i = 0; i < 100; i++) {
            exec.execute(new TestRunnable(rw));
        }
        exec.shutdown();
    }
 
    static class TestRunnable implements Runnable{
        private final ReaderAndWriter<String, Integer> rw;
        private final String KEY = "x";
 
        TestRunnable(ReaderAndWriter<String, Integer> rw) {
            this.rw = rw;
        }
 
        @Override
        public void run() {
            Random random = new Random();
            int r = random.nextInt(100);
            //生成随机数，小于30的写入缓存，大于等于30则读取数字
            if (r < 30){
                rw.put(KEY, r);
            } else {
                rw.get(KEY);
            }
        }
    }
}
