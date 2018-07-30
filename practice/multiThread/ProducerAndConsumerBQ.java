package multiThread;

import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * 用阻塞队列快速实现生产者-消费者
 * @author jkq
 * @version 2018年7月30日
 */
public class ProducerAndConsumerBQ {
	
	public static void main(String[] args) {
        final BlockingQueue<Integer> list = new ArrayBlockingQueue<Integer>(10);
        Producer producer = new Producer(list);
        Consumer consumer = new Consumer(list);
        producer.start();
        consumer.start();
    }
 
    static class Producer extends Thread{
        private final BlockingQueue<Integer> list;
        Producer(BlockingQueue<Integer> list) {
            this.list = list;
        }
        @Override public void run() {
            while(true){
                try {                	                                       
                    int i = new Random().nextInt(100);
                    list.put(i);
                    System.out.println("生产数据：" + i);                  
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
 
    static class Consumer extends Thread{
        private final BlockingQueue<Integer> list;
        Consumer(BlockingQueue<Integer> list) {
            this.list = list;
        }
        @Override public void run() {
            while (true){
                try {              	
                	Integer take = list.take();
                    System.out.println("消费数据：" + take);                    
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

	
}
