
abstract class Account8 { //账户类

	private String id;	//帐号
	private double balance;	//余额
	private static double total; //所有账户的总金额

	//供派生类调用的构造函数，id为账户
	protected Account8(final Date8 date, final String id){
		this.id = id;
		balance = 0;
		date.show();
		System.out.println("\t#" + id + " created");
	}
	
	//记录一笔帐，date为日期，amount为金额，desc为说明
	protected void record(final Date8 date, double amount, final String desc){
		amount = Math.floor(amount * 100 + 0.5) / 100;	//保留小数点后两位
		balance += amount;
		total += amount;
		date.show();
		System.out.println("\t#" + id + "\t" + amount + "\t" + balance + "\t" + desc);
	}
	
	//报告错误信息
	final protected void error(final String msg){
		System.out.println("Error(#" + id + "): " + msg);
	}

    final public String getId() { return id; }
    final public double getBalance() { return balance; }
	public static double getTotal() { return total; }
	//存入现金，date为日期，amount为金额，desc为款项说明
	public abstract void deposit(final Date8 date, double amount, final String desc);
	//取出现金，date为日期，amount为金额，desc为款项说明
	public abstract void withdraw(final Date8 date, double amount, final String desc);
	//结算（计算利息、年费等），每月结算一次，date为结算日期
	public abstract void settle(final Date8 date);
	//显示账户信息
	public void show(){
		System.out.println(id + "\tBalance: " + balance);
	}
};


