class Account { //账户类
	private String id;	//帐号
	private double balance;	//余额
	private static double total = 0; //所有账户的总金额
    
	protected Account(){};
	//供派生类调用的构造函数，id为账户
	protected Account(final Date7 date, final String id){
		this.id = id;
		balance = 0;
		date.show();
		System.out.println("\t#" + id + " created");
	}
	
	//记录一笔帐，date为日期，amount为金额，desc为说明
	protected void record(final Date7 date, double amount, final String desc){
		amount = Math.floor(amount * 100 + 0.5) / 100;	//保留小数点后两位
		balance += amount;
		total += amount;
		date.show();
		System.out.println("\t#" + id + "\t" + amount + "\t" + balance + "\t" + desc);
	}
	
	//报告错误信息
	final protected void error(final String msg) {
		System.out.println("Error(#" + id + "): " + msg);
	}

	final String getId() { return id; }
	final double getBalance() { return balance; }
	static double getTotal() { return total; }
	
	//显示账户信息
    void show() {
		System.out.println(id + "\tBalance: " + balance);
	}

};
