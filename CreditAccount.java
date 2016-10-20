class CreditAccount extends Account { //信用账户类
	private Accumulator acc;	//辅助计算利息的累加器
	private double credit;		//信用额度
	private double rate;		//欠款的日利率
	private double fee;			//信用卡年费

	final private double getDebt() {	//获得欠款额
		double balance = getBalance();
		return (balance < 0 ? balance : 0);
	}

	//构造函数
	public CreditAccount(final Date7 date, final String id, double credit, double rate, double fee){
		super(date, id);
		this.rate = rate;
		acc = new Accumulator(date, 0);
		this.fee = fee;
		this.credit = credit;
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
	
	//存入现金
	void deposit(final Date7 date, double amount, final String desc){
		record(date, amount, desc);
		acc.change(date, getDebt());
	}
	
	//取出现金
	void withdraw(final Date7 date, double amount, final String desc){
		if (amount - getBalance() > credit) {
			error("not enough credit");
		} 
		else {
			record(date, -amount, desc);
			acc.change(date, getDebt());
		}
	}
	
	//结算利息和年费，每月1日调用一次该函数
	void settle(final Date7 date){
		double interest = acc.getSum(date) * rate;
		if (interest != 0)
			record(date, interest, "interest");
		if (date.getMonth() == 1)
			record(date, -fee, "annual fee");
		acc.reset(date, getDebt());
	}

	final void show(){
		super.show();
		System.out.println("\tAvailable credit:" + getAvailableCredit());
	}
}