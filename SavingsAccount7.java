
public class SavingsAccount7 extends Account { //�����˻���
	private Accumulator acc;	//����������Ϣ���ۼ���
	private double rate;		//����������

	//���캯��
	public SavingsAccount7(final Date7 date, final String id, double rate){
	    super(date, id);
		this.rate = rate;
		acc = new Accumulator(date, 0 );
	}
	
	final double getRate(){ return rate; }
	
	//�����ֽ�
	void deposit(final Date7 date, double amount, final String desc){
		record(date, amount, desc);
		acc.change(date, getBalance());
	}
	
	//ȡ���ֽ�
	void withdraw(final Date7 date, double amount, final String desc){
		if (amount > getBalance()) {
			error("not enough money");
		} 
		else {
			record(date, -amount, desc);
			acc.change(date, getBalance());
		}
	}
	
	//������Ϣ��ÿ��1��1�յ���һ�θú���
	void settle(final Date7 date){
		Date7 date0 = new Date7(date.getYear() - 1, 1, 1);
		double interest = acc.getSum(date) * rate	//������Ϣ
				/ date.distance(date0);
			if (interest != 0)
				record(date, interest, "interest");
			acc.reset(date, getBalance());
	}

    public static void main(String args[]) {
    	Date7 date1 = new Date7(2008, 11, 1);	//��ʼ����
    	//���������˻�
    	SavingsAccount7 sa1 = new SavingsAccount7(date1, "S3755217", 0.015);
    	SavingsAccount7 sa2 = new SavingsAccount7(date1, "02342342", 0.015);
    	CreditAccount ca = new CreditAccount(date1, "C5392394", 10000, 0.0005, 50);
    	//11�·ݵļ�����Ŀ
    	Date7 date2 = new Date7(2008, 11, 5);
    	sa1.deposit(date2, 5000, "salary");
    	Date7 date3 = new Date7(2008, 11, 15);
    	ca.withdraw(date3, 2000, "buy a cell");
    	Date7 date4 = new Date7(2008, 11, 25);
    	sa2.deposit(date4, 10000, "sell stock 0323");
    	//�������ÿ�
    	Date7 date5 = new Date7(2008, 12, 1);
    	ca.settle(date5);
    	//12�·ݵļ�����Ŀ
    	ca.deposit(date5, 2016, "repay the credit");
    	Date7 date6 = new Date7(2008, 12, 5);
    	sa1.deposit(date6, 5500, "salary");
    	//���������˻�
    	Date7 date7 = new Date7(2009, 1, 1);
    	sa1.settle(date7);
    	sa2.settle(date7);
    	ca.settle(date7);
    	//��������˻���Ϣ
    	sa1.show();
    	sa2.show();
    	ca.show(); 
    	System.out.println("Total: " + getTotal());
    	return ;
    }
}