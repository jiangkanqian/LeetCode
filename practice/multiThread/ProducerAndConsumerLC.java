package multiThread;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

import multiThread.ProducerAndConsumerBQ.Consumer;
import multiThread.ProducerAndConsumerBQ.Producer;

/**
 * 用Lock和Condition实现生产者-消费者
 * @author jkq
 * @version 2018年7月30日
 */
public class ProducerAndConsumerLC {
	
	private static final ReentrantLock lock = new ReentrantLock();
	private static final Condition noFull = lock.newCondition();
	private static final Condition noEmpty = lock.newCondition();
	
	static class Producer extends Thread{
				
		private List<Integer> list;
		private int limit;
		public Producer(List<Integer> list,int limit){
			this.list = list;
			this.limit = limit;
		}
		
		@Override
		public void run() {
			// TODO Auto-generated method stub						
			while(true){
				lock.lock();
				try{
					if(list.size() == limit){
						System.out.println("队列已满");
						noFull.await();
					}
					int num = new Random().nextInt(100);
					System.out.println("队列：" + list.toString());
					System.out.println("生产数据：" + num);
					list.add(num);
					noEmpty.signalAll();
					Thread.sleep(new Random().nextInt(1000));
				}
				catch(InterruptedException e){
					
				}
				finally{
					lock.unlock();
				}
			}
		}
		
	}
	
	static class Consumer extends Thread{
		
		private List<Integer> list;
		private int limit;
		public Consumer(List<Integer> list,int limit){
			this.list = list;
			this.limit = limit;
		}
		
		@Override
		public void run() {
			while(true){
				lock.lock();
				try{
					if(list.size() == 0){
						System.out.println("队列为空");
						noEmpty.await();
					}
					System.out.println("队列：" + list.toString());
					Integer remove = list.remove(0);
					System.out.println("消费数据：" + remove);
					noFull.signalAll();
					Thread.sleep(new Random().nextInt(1000));
				}
				catch(InterruptedException e){
					
				}
				finally{
					lock.unlock();
				}
			}
		}
		
	}
	
	public static void main(String[] args) {
        final List list = new ArrayList<Integer>(10);
        Producer producer = new Producer(list,10);
        Consumer consumer = new Consumer(list,10);
        producer.start();
        consumer.start();
    }
	
}
