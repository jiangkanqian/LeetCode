package multiThread;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ExecutorService {
		
	public static void main(String[] args){
		java.util.concurrent.ExecutorService threadPool = Executors.newFixedThreadPool(10); 
		while(true) { 
			threadPool.execute(new Runnable() { 
				// 提交多个线程任务，并执行
				@Override 
				public void run() { 
					System.out.println(Thread.currentThread().getName() + " is running .."); 
					try { 
						Thread.sleep(3000); 
						} 
					catch (InterruptedException e) { 
						e.printStackTrace(); 
						} 
					} 
				}); 
			}
	}
		
	
	
}
