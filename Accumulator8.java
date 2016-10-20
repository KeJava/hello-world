
class Accumulator8 {	//��ĳ����ֵ�����ۼ�

	private Date8 lastDate;	//�ϴα����ֵ��ʱ��
    private double value;	//��ֵ�ĵ�ǰֵ
    private double sum;		//��ֵ�����ۼ�֮��

	//���캯����dateΪ��ʼ�ۼӵ����ڣ�valueΪ��ʼֵ
	public Accumulator8(final Date8 date, double value){
		lastDate = date;
		this.value = value;
	}

	//��õ�����date���ۼӽ��
	final double getSum(final Date8 date) {
		return sum + value * (date.substruct(lastDate));
	}

	//��date����ֵ���Ϊvalue
	void change(final Date8 date, double value) {
		sum = getSum(date);
		lastDate = date;
		this.value = value;
	}

	//��ʼ���������ڱ�Ϊdate����ֵ��Ϊvalue���ۼ�������
	void reset(final Date8 date, double value) {
		lastDate = date;
		this.value = value;
		sum = 0;
	}
	

}