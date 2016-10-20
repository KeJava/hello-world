
public class CreditAccount9 extends Account9 { //信用账户类
private
	Accumulator9 acc;	//辅助计算利息的累加器
	double credit;		//信用额度
	double rate;		//欠款的日利率
	double fee;		//信用卡年费

	final double getDebt() {	//获得欠款额
		double balance = getBalance();
		return (balance < 0 ? balance : 0);
	}
	
public
	//构造函数
	CreditAccount9(final Date9 date, final String id, double credit, double rate, double fee){
	    super(date, id);
	    acc = new Accumulator9(date, 0);
	    this.credit = credit;
	    this.rate = rate;
	    this.fee = fee;
    }

	final double getCredit() { return credit; }
	final double getRate() { return rate; }
	final double getFee() { return fee; }
	final double getAvailableCredit() {	//获得可用信用
		if (getBalance() < 0) 
			return credit + getBalance();
		else
			return credit;
	}
	
	void deposit(final Date9 date, double amount, final String desc){
		record(date, amount, desc);
		acc.change(date, getDebt());
	}
	
	void withdraw(final Date9 date, double amount, final String desc){
		if (amount - getBalance() > credit) {
			error("not enough credit");
		} else {
			record(date, -amount, desc);
			acc.change(date, getDebt());
		}
	}
	
	void settle(final Date9 date){
		double interest = acc.getSum(date) * rate;
		if (interest != 0)
			record(date, interest, "interest");
		if (date.getMonth() == 1)
			record(date, -fee, "annual fee");
		acc.reset(date, getDebt());
	}
	
     final void show(){
		super.show();
		System.out.println( "\tAvailable credit:" + getAvailableCredit() );
	}
}
