
public class SavingsAccount5 {
		private int id;				//账号
		private double balance;		//余额
		private double rate;		//存款的年利率
		private int lastDate;		//上次变更余额的时期
		private double accumulation;	//余额按日累加之和
		private static double total;	//所有账户的总金额

		//记录一笔帐，date为日期，amount为金额，desc为说明
		private void record(int date, double amount){
			accumulation = accumulate(date);
			lastDate = date;
			amount = Math.floor(amount * 100 + 0.5) / 100;	//保留小数点后两位
			balance += amount;
			total += amount;
		    System.out.println(date + "\t#" + id + "\t" + amount + "\t" + balance);
		}
			
		//获得到指定日期为止的存款金额按日累积值
		final private double accumulate(int date){
			return accumulation + balance * (date - lastDate);
		}
		
		//构造函数
		public SavingsAccount5(int date, int id, double rate){
			this.id = id;
			this.rate = rate;
			balance = 0;
			this.lastDate = date;
			accumulation = 0;
			System.out.println(date + "\t#" + id + "is create");
		}
		final public int getId(){ return id; }
		final public double getBalance(){ return balance; }
		final public double getRate(){ return rate; }
		public static double getTotal() { return total; }

		//存入现金
		public void deposit(int date, double amount){
			record(date, amount);
		}
		//取出现金
		public void withdraw(int date, double amount){
			if (amount > getBalance())
				System.out.println("Error: not enough money");
			else
				record(date, -amount);
		}
		//结算利息，每年1月1日调用一次该函数
		public void settle(int date){
			double interest = accumulate(date) * rate / 365;	//计算年息
			if (interest != 0)
				record(date, interest);
			accumulation = 0;
		}
		//显示账户信息
		final public void show(){
			System.out.println("#" + id + "\tBalance: " + balance);
		}

		public static void main(String args[]) {
			//建立几个账户
			SavingsAccount5 sa0 = new SavingsAccount5(1, 21325302, 0.015);
			SavingsAccount5 sa1 = new SavingsAccount5(1, 58320212, 0.015);

			//几笔账目
			sa0.deposit(5, 5000);
			sa1.deposit(25, 10000);
			sa0.deposit(45, 5500);
			sa1.withdraw(60, 4000);

			//开户后第90天到了银行的计息日，结算所有账户的年息
			sa0.settle(90);
			sa1.settle(90);

			//输出各个账户信息
			sa0.show();	
			sa1.show();
			System.out.println("Total: " + SavingsAccount5.getTotal());
			return ;
		}
}
