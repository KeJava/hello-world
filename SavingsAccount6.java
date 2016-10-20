class SavingsAccount6 { //储蓄账户类
	private String id;		//帐号
	private double balance;		//余额
	private double rate;		//存款的年利率
	private Date6 lastDate;		//上次变更余额的时期
	private double accumulation;	//余额按日累加之和
	private static double total;	//所有账户的总金额

	//记录一笔帐，date为日期，amount为金额，desc为说明
	private void record(final Date6 date, double amount, final String desc){
		accumulation = accumulate(date);
		lastDate = date;
		amount = Math.floor(amount * 100 + 0.5) / 100;	//保留小数点后两位
		balance += amount;
		total += amount;
		date.show();
		System.out.println("\t#" + id + "\t" + amount + "\t" + balance + "\t" + desc);
	}
	
	//报告错误信息
	final void error(final String msg){
		System.out.println("Error(#" + id + "):" + msg);
	}
	
	//获得到指定日期为止的存款金额按日累积值
	final private double accumulate(final Date6 date){
		return accumulation + balance * date.distance(lastDate);
	}
	
	//构造函数
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

	//存入现金
	public void deposit(final Date6 date, double amount, final String desc){
		record(date, amount, desc);
	}
	//取出现金
	
	public void withdraw(final Date6 date, double amount, final String desc){
		if(amount > getBalance())
			error("not enough money!");
		else 
			record(date, -amount, desc);
	}
	
	//结算利息，每年1月1日调用一次该函数
	public void settle(final Date6 date){
		Date6 temDate = new Date6(date.getYear()-1, 1, 1);
		double interest = accumulate(date) * rate / date.distance(temDate);	//计算年息
		if (interest != 0)
			record(date, interest, "interest");
		accumulation = 0;
	}
	
	//显示账户信息
	final public void show(){
		System.out.println(id + "\tBalance: " + balance);
	}
	
	public static void main(String args[]){
		Date6 date = new Date6(2008, 11, 1);	//起始日期
		//建立几个账户
		SavingsAccount6 accounts[] = {
			new SavingsAccount6(date, "S3755217", 0.015),
			new SavingsAccount6(date, "02342342", 0.015)
		};
		//final int n = sizeof(accounts) / sizeof(SavingsAccount6); //账户总数     java中并没有类似于sizeof()的方法
		final int n = accounts.length;
		//11月份的几笔账目
		Date6 date1 = new Date6(2008, 11, 5);
		accounts[0].deposit(date1, 5000, "salary");
		Date6 date2 = new Date6(2008, 11, 25);
		accounts[1].deposit(date2, 10000, "sell stock 0323");
		//12月份的几笔账目
		Date6 date3 = new Date6(2008, 12, 5);
		accounts[0].deposit(date3, 5500, "salary");
		Date6 date4 = new Date6(2008, 12, 20);
		accounts[1].withdraw(date4, 4000, "buy a laptop");

		//结算所有账户并输出各个账户信息
		for (int i = 0; i < n; i++) {
			Date6 date0 = new Date6(2009, 1, 1);
			accounts[i].settle(date0);
			accounts[i].show();
		}
		System.out.println( "Total: " + getTotal() );
		return ;
	}
}
