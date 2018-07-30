package multiThread;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import multiThread.ProducerAndConsumerBQ.Consumer;
import multiThread.ProducerAndConsumerBQ.Producer;

/**
 * 用wait()和notify()实现生产者-消费者
 * @author jkq
 * @version 2018年7月30日
 */
public class ProducerAndConsumerWN {
	 
	static class Producer implements Runnable{
		
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
				synchronized(list){
					while(list.size() == limit){
						try {
							list.wait();
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					int i = new Random().nextInt(100);
					System.out.println("list : " + list.toString());
					System.out.println("生产数据：" + i);
					list.add(i);
					list.notifyAll();
				}
			}
		}
		
	}
	
	static class Consumer implements Runnable{
		
		private List<Integer> list;
		public Consumer(List<Integer> list){
			this.list = list;
		}
		
		@Override
		public void run() {
			// TODO Auto-generated method stub
			while(true){
				synchronized(list){
					while(list.size() == 0){
						try {
							list.wait();
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					System.out.println("list : " + list.toString());
					Integer remove = (Integer) list.remove(0);
					System.out.println("消费数据：" + remove);
					list.notifyAll();
				}
			}
		}				
		
	}
	
	public static void main(String[] args) {
        final List<Integer> list = new ArrayList<Integer>();
        Thread producer = new Thread(new Producer(list,10));
        Thread consumer = new Thread(new Consumer(list));
        producer.start();
        consumer.start();
    }
	
}
