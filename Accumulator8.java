
class Accumulator8 {	//将某个数值按日累加

	private Date8 lastDate;	//上次变更数值的时期
    private double value;	//数值的当前值
    private double sum;		//数值按日累加之和

	//构造函数，date为开始累加的日期，value为初始值
	public Accumulator8(final Date8 date, double value){
		lastDate = date;
		this.value = value;
	}

	//获得到日期date的累加结果
	final double getSum(final Date8 date) {
		return sum + value * (date.substruct(lastDate));
	}

	//在date将数值变更为value
	void change(final Date8 date, double value) {
		sum = getSum(date);
		lastDate = date;
		this.value = value;
	}

	//初始化，将日期变为date，数值变为value，累加器清零
	void reset(final Date8 date, double value) {
		lastDate = date;
		this.value = value;
		sum = 0;
	}
	

}