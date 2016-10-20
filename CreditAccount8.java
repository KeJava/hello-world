
public class CreditAccount8 extends Account8 { //信用账户类

	private Accumulator8 acc;	//辅助计算利息的累加器
	private double credit;		//信用额度
	private double rate;		//欠款的日利率
	private double fee;			//信用卡年费

	final private double getDebt() {	//获得欠款额
		double balance = getBalance();
		return (balance < 0 ? balance : 0);
	}

	//构造函数
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
	final public double getAvailableCredit() {	//获得可用信用
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

