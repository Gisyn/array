package test3;
import java.util.*;
public class bank {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
        String g1= "guest1", g2 = "guest2";
        Scanner input = new Scanner(System.in);
    	System.out.println("请输入用户1取款金额");
		int m1 = input.nextInt();
    	System.out.println("请输入用户2取款金额");
		int m2 = input.nextInt();
        ATM atm = new ATM(g1,g2,m1,m2);
        
        Thread guest1, guest2;
        guest1 = new Thread(atm); // 目标对象bank
        guest2 = new Thread(atm); // 目标对象bank
        guest1.setName(g1);
        guest2.setName(g2);         
        guest1.start();
        guest2.start();
	}
}
package test3;
public class ATM implements Runnable {
    int money = 300;
    String guest1,guest2;
    int money1,money2;
    public ATM(String s1,String s2,int m1,int m2){ 
    	guest1=s1; guest2=s2;money1=m1;money2=m2; 
    	System.out.println("原本 银行资金："+money);
    }
    public void run(){ drawMoney(); }
    public synchronized void drawMoney(){
	    if(Thread.currentThread().getName().equals(guest1)){
	    	while(money<money1) {
	    		try {
	    			System.out.println("用户1操作失败 银行资金不足");
					wait();
				} catch (InterruptedException e) {}
	    	}
	    	money-=money1;
	    	System.out.println("用户1操作结束后 银行资金："+money);
	    }
	    else if(Thread.currentThread().getName().equals(guest2)){
	    	while(money<money2) {
	    		try {
	    			System.out.println("用户2操作失败 银行资金不足");
					wait();
				} catch (InterruptedException e) {}
	    	}
	    	money-=money2;
	    	System.out.println("用户2操作结束后 银行资金："+money);
	    }    
	    notifyAll();
    }
}
