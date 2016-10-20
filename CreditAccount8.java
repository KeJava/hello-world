
public class CreditAccount8 extends Account8 { //�����˻���

	private Accumulator8 acc;	//����������Ϣ���ۼ���
	private double credit;		//���ö��
	private double rate;		//Ƿ���������
	private double fee;			//���ÿ����

	final private double getDebt() {	//���Ƿ���
		double balance = getBalance();
		return (balance < 0 ? balance : 0);
	}

	//���캯��
	public CreditAccount8(final Date8 date, final String id, double credit, double rate, double fee){
		super(date, id);
		this.credit = credit;
		this.rate = rate;
		this.fee = fee;
		acc = new Accumulator8(date, 0);
	}
	
	final public double getCredit() { return credit; }
	final public double getRate() { return rate; }
	final public double getFee() { return fee; }
	final public double getAvailableCredit() {	//��ÿ�������
		if (getBalance() < 0) 
			return credit + getBalance();
		else
			return credit;
	}
	
	public void deposit(final Date8 date, double amount, final String desc){
		record(date, amount, desc);
		acc.change(date, getDebt());
	}
	
	public void withdraw(final Date8 date, double amount, final String desc){
		if (amount - getBalance() > credit) {
			error("not enough credit");
		} else {
			record(date, -amount, desc);
			acc.change(date, getDebt());
		}
	}
	
	public void settle(final  Date8 date){
		double interest = acc.getSum(date) * rate;
		if (interest != 0)
			record(date, interest, "interest");
		if (date.getMonth() == 1)
			record(date, -fee, "annual fee");
		acc.reset(date, getDebt());
	}
	
	final public void show(){
		super.show();
		System.out.println("\tAvailable credit:" + getAvailableCredit());
	}
	
}

