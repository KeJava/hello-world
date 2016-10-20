
abstract class Account9 { //�˻���
private
	String id;	//�ʺ�
	double balance;	//���
	static double total; //�����˻����ܽ��
protected
	//����������õĹ��캯����idΪ�˻�
	Account9(final Date9 date, final String id){
	    this.id = id;
	    balance = 0; 
	    date.show();
	    System.out.println( "\t#" + id + " created" );
    }
	//��¼һ���ʣ�dateΪ���ڣ�amountΪ��descΪ˵��
	void record(final Date9 date, double amount, final String desc){
		amount = Math.floor(amount * 100 + 0.5) / 100;	//����С�������λ
		balance += amount;
		total += amount;
		date.show();
		System.out.println( "\t#" + id + "\t" + amount + "\t" + balance + "\t" + desc );
	}
	//���������Ϣ
	final void error(final String msg) {
		System.out.println( "Error(#" + id + "): " + msg );
	}
public
	final String getId() { return id; }
	final double getBalance() { return balance; }
	static double getTotal() { return total; }
	//�����ֽ�dateΪ���ڣ�amountΪ��descΪ����˵��
	abstract void deposit(final Date9 date, double amount, final String desc);
	//ȡ���ֽ�dateΪ���ڣ�amountΪ��descΪ����˵��
	abstract void withdraw(final Date9 date, double amount, final String desc);
	//���㣨������Ϣ����ѵȣ���ÿ�½���һ�Σ�dateΪ��������
	abstract void settle(final Date9 date);
	//��ʾ�˻���Ϣ
	void show(){
		System.out.println( id + "\tBalance: " + balance );
	}
};

