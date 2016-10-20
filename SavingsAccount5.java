
public class SavingsAccount5 {
		private int id;				//�˺�
		private double balance;		//���
		private double rate;		//����������
		private int lastDate;		//�ϴα������ʱ��
		private double accumulation;	//�����ۼ�֮��
		private static double total;	//�����˻����ܽ��

		//��¼һ���ʣ�dateΪ���ڣ�amountΪ��descΪ˵��
		private void record(int date, double amount){
			accumulation = accumulate(date);
			lastDate = date;
			amount = Math.floor(amount * 100 + 0.5) / 100;	//����С�������λ
			balance += amount;
			total += amount;
		    System.out.println(date + "\t#" + id + "\t" + amount + "\t" + balance);
		}
			
		//��õ�ָ������Ϊֹ�Ĵ������ۻ�ֵ
		final private double accumulate(int date){
			return accumulation + balance * (date - lastDate);
		}
		
		//���캯��
		public SavingsAccount5(int date, int id, double rate){
			this.id = id;
			this.rate = rate;
			balance = 0;
			this.lastDate = date;
			accumulation = 0;
			System.out.println(date + "\t#" + id + "is create");
		}
		final public int getId(){ return id; }
		final public double getBalance(){ return balance; }
		final public double getRate(){ return rate; }
		public static double getTotal() { return total; }

		//�����ֽ�
		public void deposit(int date, double amount){
			record(date, amount);
		}
		//ȡ���ֽ�
		public void withdraw(int date, double amount){
			if (amount > getBalance())
				System.out.println("Error: not enough money");
			else
				record(date, -amount);
		}
		//������Ϣ��ÿ��1��1�յ���һ�θú���
		public void settle(int date){
			double interest = accumulate(date) * rate / 365;	//������Ϣ
			if (interest != 0)
				record(date, interest);
			accumulation = 0;
		}
		//��ʾ�˻���Ϣ
		final public void show(){
			System.out.println("#" + id + "\tBalance: " + balance);
		}

		public static void main(String args[]) {
			//���������˻�
			SavingsAccount5 sa0 = new SavingsAccount5(1, 21325302, 0.015);
			SavingsAccount5 sa1 = new SavingsAccount5(1, 58320212, 0.015);

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
			System.out.println("Total: " + SavingsAccount5.getTotal());
			return ;
		}
}
