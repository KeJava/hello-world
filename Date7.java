class Date7 {	//������private
	final int DAYS_BEFORE_MONTH[] = { 0, 31, 59, 90, 120, 151, 181, 212, 243, 273, 304, 334, 365 };
	private int year;		//��
	private int month;		//��
	private int day;		//��
	private int totalDays;	//�������Ǵӹ�ԪԪ��1��1�տ�ʼ�ĵڼ���

	//���ꡢ�¡��չ�������
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
	
	//��õ����ж�����
	final public int getMaxDay() {
		if (isLeapYear() && month == 2)
			return 29;
		else
			return DAYS_BEFORE_MONTH[month] - DAYS_BEFORE_MONTH[month - 1];
	}		
	
	//�жϵ����Ƿ�Ϊ����
	final public boolean isLeapYear(){	
		return year % 4 == 0 && year % 100 != 0 || year % 400 == 0;
	}
	
	//�����ǰ����
	final void show(){
		System.out.print(getYear() + "-" + getMonth() + "-" + getDay());
	};			
	
	//������������֮��������	
	int distance(final Date7 date) {
		return totalDays - date.totalDays;
	}
};