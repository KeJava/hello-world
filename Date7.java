class Date7 {	//日期类private
	final int DAYS_BEFORE_MONTH[] = { 0, 31, 59, 90, 120, 151, 181, 212, 243, 273, 304, 334, 365 };
	private int year;		//年
	private int month;		//月
	private int day;		//日
	private int totalDays;	//该日期是从公元元年1月1日开始的第几天

	//用年、月、日构造日期
	public Date7(int year, int month, int day){             
		this.year = year;
		this.month = month;
		this.day = day;
		if (day <= 0 || day > getMaxDay()) {
			System.out.print( "Invalid date: ");
			show();
			System.exit(1);	
		}
		int years = year - 1;
		totalDays = years * 365 + years / 4 - years / 100 + years / 400
			+ DAYS_BEFORE_MONTH[month - 1] + day;
		if (isLeapYear() && month > 2) totalDays++;
	}
	
	final public int getYear() { return year; }
	final public int getMonth() { return month; }
	final public int getDay() { return day; }
	
	//获得当月有多少天
	final public int getMaxDay() {
		if (isLeapYear() && month == 2)
			return 29;
		else
			return DAYS_BEFORE_MONTH[month] - DAYS_BEFORE_MONTH[month - 1];
	}		
	
	//判断当年是否为闰年
	final public boolean isLeapYear(){	
		return year % 4 == 0 && year % 100 != 0 || year % 400 == 0;
	}
	
	//输出当前日期
	final void show(){
		System.out.print(getYear() + "-" + getMonth() + "-" + getDay());
	};			
	
	//计算两个日期之间差多少天	
	int distance(final Date7 date) {
		return totalDays - date.totalDays;
	}
};