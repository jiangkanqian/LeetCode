package multiThread;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Mrsw {
	
	/*
	 * 在Java中Lock接口比synchronized块的优势是什么？
	 * 你需要实现一个高效的缓存，它允许多个用户读，但只允许一个用户写，以此来保持它的完整性，你会怎样去实现它？
	 * */
	
	public class MyData{
		private int data=0;
		
		
		public void read(){
			System.out.println("ThreadName=" + Thread.currentThread().getName() + " 正在读,"
					+ "数据为:" + data);
		}
		
		public void write(){
				data++;
				System.out.println("ThreadName=" + Thread.currentThread().getName() + " 正在写");			
		}
		
	}
	
	public class Worker implements Runnable{
		
		MyData data;
		private Lock lock = new ReentrantLock();
		public Worker(MyData data){
			this.data=data;
		}
		
		@Override
		public void run() {
			// TODO Auto-generated method stub			
			try {
				read();
				Thread.sleep(500);
				write();
				read();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		public void read(){
			data.read();
		}
		
		public void write(){
			try{
				lock.lock();
				data.write();
			}
			finally{ 
				lock.unlock(); 
			}
			
		}
		
	}
	
	public static void main(String[] args){
		Mrsw mw = new Mrsw();
		MyData md = mw.new MyData();
		Worker worker = mw.new Worker(md);
		Thread t1 = new Thread(worker);
		Thread t2 = new Thread(worker);
		Thread t3 = new Thread(worker);
		t1.start();
		t2.start();
		t3.start();
	}
	
}
