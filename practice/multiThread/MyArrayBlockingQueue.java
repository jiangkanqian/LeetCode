package multiThread;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class MyArrayBlockingQueue<T> {
	
	private final List<T> list;
	private final int limit;
	private final Lock lock = new ReentrantLock();
	private final Condition notFull = lock.newCondition();
	private final Condition notEmpty = lock.newCondition();
	
	public MyArrayBlockingQueue(int limit){
		this.limit = limit;
		this.list = new ArrayList<T>();
	}
	
	public void add(T t){
		try{
			lock.lock();
			if(list.size() == limit){
				notFull.await();
			}
			System.out.println("list : " + list.toString());
	        System.out.println("put : " + t);
			list.add(t);
			notEmpty.signalAll();
		}
		catch(InterruptedException e){
			
		}
		finally{
			lock.unlock();
		}
	}
	
	public T take(){
		T remove = null;
		try{
			lock.lock();
			while(list.size() == 0){
				notEmpty.await();
			}
			System.out.println("list : " + list.toString());
			remove = list.remove(0);
			System.out.println("take : " + remove);
			notFull.signalAll();			
		}
		catch(InterruptedException e){
			
		}
		finally{			
			lock.unlock();
		}
		return remove;
	}
	
	public static void main(String[] args){
		
		MyArrayBlockingQueue abq = new MyArrayBlockingQueue(10);
		ExecutorService exec = Executors.newCachedThreadPool();
		for(int i=0;i<100;i++){
			exec.execute(new TestRunnable(abq));
		}
		exec.shutdown();
		
	}
	
	static class TestRunnable implements Runnable{
		
		private final MyArrayBlockingQueue<Integer> mabq;
		
		public TestRunnable(MyArrayBlockingQueue<Integer> mabq){
			this.mabq = mabq;
		}
		
		@Override
		public void run() {
			// TODO Auto-generated method stub
			Random random = new Random();
            int r = random.nextInt(100);
            if(r<50){
            	mabq.add(r);
            }
            if(r>50){
            	mabq.take();
            }
		}
		
		
		
	}
	
}
