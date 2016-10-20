class SavingsAccount6 { //�����˻���
	private String id;		//�ʺ�
	private double balance;		//���
	private double rate;		//����������
	private Date6 lastDate;		//�ϴα������ʱ��
	private double accumulation;	//�����ۼ�֮��
	private static double total;	//�����˻����ܽ��

	//��¼һ���ʣ�dateΪ���ڣ�amountΪ��descΪ˵��
	private void record(final Date6 date, double amount, final String desc){
		accumulation = accumulate(date);
		lastDate = date;
		amount = Math.floor(amount * 100 + 0.5) / 100;	//����С�������λ
		balance += amount;
		total += amount;
		date.show();
		System.out.println("\t#" + id + "\t" + amount + "\t" + balance + "\t" + desc);
	}
	
	//���������Ϣ
	final void error(final String msg){
		System.out.println("Error(#" + id + "):" + msg);
	}
	
	//��õ�ָ������Ϊֹ�Ĵ������ۻ�ֵ
	final private double accumulate(final Date6 date){
		return accumulation + balance * date.distance(lastDate);
	}
	
	//���캯��
	public SavingsAccount6(final Date6 date, final String id, double rate){
		this.id = id;
		this.rate = rate;
		lastDate = date;
		accumulation = 0;
		date.show();
		System.out.println("\t#" + id + " created");
	}
	
	final public String getId(){ return id; }
	final public double getBalance(){ return balance; }
	final public double getRate(){ return rate; }
	public static double getTotal() { return total; }

	//�����ֽ�
	public void deposit(final Date6 date, double amount, final String desc){
		record(date, amount, desc);
	}
	//ȡ���ֽ�
	
	public void withdraw(final Date6 date, double amount, final String desc){
		if(amount > getBalance())
			error("not enough money!");
		else 
			record(date, -amount, desc);
	}
	
	//������Ϣ��ÿ��1��1�յ���һ�θú���
	public void settle(final Date6 date){
		Date6 temDate = new Date6(date.getYear()-1, 1, 1);
		double interest = accumulate(date) * rate / date.distance(temDate);	//������Ϣ
		if (interest != 0)
			record(date, interest, "interest");
		accumulation = 0;
	}
	
	//��ʾ�˻���Ϣ
	final public void show(){
		System.out.println(id + "\tBalance: " + balance);
	}
	
	public static void main(String args[]){
		Date6 date = new Date6(2008, 11, 1);	//��ʼ����
		//���������˻�
		SavingsAccount6 accounts[] = {
			new SavingsAccount6(date, "S3755217", 0.015),
			new SavingsAccount6(date, "02342342", 0.015)
		};
		//final int n = sizeof(accounts) / sizeof(SavingsAccount6); //�˻�����     java�в�û��������sizeof()�ķ���
		final int n = accounts.length;
		//11�·ݵļ�����Ŀ
		Date6 date1 = new Date6(2008, 11, 5);
		accounts[0].deposit(date1, 5000, "salary");
		Date6 date2 = new Date6(2008, 11, 25);
		accounts[1].deposit(date2, 10000, "sell stock 0323");
		//12�·ݵļ�����Ŀ
		Date6 date3 = new Date6(2008, 12, 5);
		accounts[0].deposit(date3, 5500, "salary");
		Date6 date4 = new Date6(2008, 12, 20);
		accounts[1].withdraw(date4, 4000, "buy a laptop");

		//���������˻�����������˻���Ϣ
		for (int i = 0; i < n; i++) {
			Date6 date0 = new Date6(2009, 1, 1);
			accounts[i].settle(date0);
			accounts[i].show();
		}
		System.out.println( "Total: " + getTotal() );
		return ;
	}
}
