
abstract class Account8 { //�˻���

	private String id;	//�ʺ�
	private double balance;	//���
	private static double total; //�����˻����ܽ��

	//����������õĹ��캯����idΪ�˻�
	protected Account8(final Date8 date, final String id){
		this.id = id;
		balance = 0;
		date.show();
		System.out.println("\t#" + id + " created");
	}
	
	//��¼һ���ʣ�dateΪ���ڣ�amountΪ��descΪ˵��
	protected void record(final Date8 date, double amount, final String desc){
		amount = Math.floor(amount * 100 + 0.5) / 100;	//����С�������λ
		balance += amount;
		total += amount;
		date.show();
		System.out.println("\t#" + id + "\t" + amount + "\t" + balance + "\t" + desc);
	}
	
	//���������Ϣ
	final protected void error(final String msg){
		System.out.println("Error(#" + id + "): " + msg);
	}

    final public String getId() { return id; }
    final public double getBalance() { return balance; }
	public static double getTotal() { return total; }
	//�����ֽ�dateΪ���ڣ�amountΪ��descΪ����˵��
	public abstract void deposit(final Date8 date, double amount, final String desc);
	//ȡ���ֽ�dateΪ���ڣ�amountΪ��descΪ����˵��
	public abstract void withdraw(final Date8 date, double amount, final String desc);
	//���㣨������Ϣ����ѵȣ���ÿ�½���һ�Σ�dateΪ��������
	public abstract void settle(final Date8 date);
	//��ʾ�˻���Ϣ
	public void show(){
		System.out.println(id + "\tBalance: " + balance);
	}
};


