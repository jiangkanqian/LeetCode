package multiThread;

public class T123 {
	
	/*
	 * 现在有T1、T2、T3三个线程，你怎样保证T2在T1执行完后执行，T3在T2执行完后执行
	 * */
	
	public static void main(String[] args) {
        //method01();
        method02();
    }
 
    /**
     * 第一种实现方式，顺序写死在线程代码的内部了，有时候不方便
     */
    private static void method01() {
        Thread t1 = new Thread(new Runnable() {
            @Override public void run() {
                System.out.println("t1 is finished");
            }
        });
        Thread t2 = new Thread(new Runnable() {
            @Override public void run() {
                try {
                    t1.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("t2 is finished");
            }
        });
        Thread t3 = new Thread(new Runnable() {
            @Override public void run() {
                try {
                    t2.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("t3 is finished");
            }
        });
 
        t3.start();
        t2.start();
        t1.start();
    }
 
 
    /**
     * 第二种实现方式，线程执行顺序可以在方法中调换
     */
    private static void method02(){
        Runnable runnable = new Runnable() {
            @Override public void run() {
                System.out.println(Thread.currentThread().getName() + "执行完成");
            }
        };
        Thread t1 = new Thread(runnable, "t1");
        Thread t2 = new Thread(runnable, "t2");
        Thread t3 = new Thread(runnable, "t3");
        try {
            t1.start();
            t1.join();
            t2.start();
            t2.join();
            t3.start();
            t3.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
	
}
