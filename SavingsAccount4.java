//4_9.cpp
//import java.io.*;
//import java.math.*;
//using namespace std;
//import java.text.DecimalFormat;  //��ʽ��
//import java.math.BigDecimal;       //��������

class SavingsAccount { //�����˻���
	private int id;				//�˺�
	private double balance;		//���
	private double rate;		//����������
	private int lastDate;		//�ϴα������ʱ��
	private double accumulation;	//�����ۼ�֮��

	//��¼һ���ʣ�dateΪ���ڣ�amountΪ��descΪ˵��
	private void record(int date, double amount) {
		accumulation = accumulate(date);
		lastDate = date;
		//��ʽ���ķ���
		//java.text.DecimalFormat df = new java.text.DecimalFormat("#.00");
		//df.format(amount);	//����С�������λ
		amount = Math.floor(amount * 100 + 0.5) / 100;
		
		//��������ķ���
		//BigDecimal   b = new  BigDecimal((amount * 100 + 0.5) / 100);  
		//amount = b.setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();  
		balance += amount;
		System.out.println(date + "\t#" + id + "\t" + amount + "\t" + balance );
		
	}
	//��õ�ָ������Ϊֹ�Ĵ������ۻ�ֵ
	private double accumulate(int date){
		return accumulation + balance * (date - lastDate);
	}

	//���캯��
	public SavingsAccount(int date, int id, double rate) {
		this.id = id;  
		balance = 0;
		this.rate = rate;
		lastDate = date;
		accumulation = 0;
		System.out.println(date + "\t#" + id + " is created" );
	}
	
	public int getId() { return id; }
	public double getBalance() { return balance; }
	public double getRate() { return rate; }

	//�����ֽ�
	void deposit(int date, double amount) {
		record(date, amount);
	}
	//ȡ���ֽ�
	void withdraw(int date, double amount) {
		if (amount > getBalance())
			System.out.println( "Error: not enough money" );
		else
			record(date, -amount);
	}
	//������Ϣ��ÿ��1��1�յ���һ�θú���
	void settle(int date) {
		double interest = accumulate(date) * rate / 365;	//������Ϣ
		if (interest != 0)
			record(date, interest);
		accumulation = 0;
	}
	//��ʾ�˻���Ϣ
	void show() {
		System.out.println("#"+id+"\tBalance: "+balance);
	}

   public static void main(String args[]) {
	 //���������˻�
		SavingsAccount sa0 = new SavingsAccount(1, 21325302, 0.015);
		SavingsAccount sa1 = new SavingsAccount(1, 58320212, 0.015);

		//������Ŀ
		sa0.deposit(5, 5000);
		sa1.deposit(25, 10000);
		sa0.deposit(45, 5500);
		sa1.withdraw(60, 4000);

		//�������90�쵽�����еļ�Ϣ�գ����������˻�����Ϣ
		sa0.settle(90);
		sa1.settle(90);

		//��������˻���Ϣ
		sa0.show();
		sa1.show();	
		return ;
   }
  }
