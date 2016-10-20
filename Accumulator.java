class Accumulator {	//��ĳ����ֵ�����ۼ�
	private Date7 lastDate;	//�ϴα����ֵ��ʱ��
	private double value;	//��ֵ�ĵ�ǰֵ
	private double sum;		//��ֵ�����ۼ�֮��

	//���캯����dateΪ��ʼ�ۼӵ����ڣ�valueΪ��ʼֵ
	public Accumulator(final Date7 date, double value){
		this.lastDate = date;
		this.value = value;
		sum = 0;
	}

	//��õ�����date���ۼӽ��
	final double getSum(final Date7 date) {
		return sum + value * date.distance(lastDate);
	}

	//��date����ֵ���Ϊvalue
	void change(final Date7 date, double value) {
		sum = getSum(date);
		lastDate = date;
		this.value = value;
	}

	//��ʼ���������ڱ�Ϊdate����ֵ��Ϊvalue���ۼ�������
	void reset(final Date7 date, double value) {
		lastDate = date;
		this.value = value;
		sum = 0;
	}
}