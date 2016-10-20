
class Accumulator9 {	//��ĳ����ֵ�����ۼ�
private
	Date9 lastDate;	//�ϴα����ֵ��ʱ��
	double value;	//��ֵ�ĵ�ǰֵ
	double sum;		//��ֵ�����ۼ�֮��
public
	//���캯����dateΪ��ʼ�ۼӵ����ڣ�valueΪ��ʼֵ
	Accumulator9(final Date9 date, double value){
	    lastDate = date;
	    this.value = value;
	    sum = 0;
    }

	//��õ�����date���ۼӽ��
	final double getSum(final Date9 date) {
		return sum + value * (date.substruct(lastDate));
	}

	//��date����ֵ���Ϊvalue
	void change(final Date9 date, double value) {
		sum = getSum(date);
		lastDate = date;
		this.value = value;
	}

	//��ʼ���������ڱ�Ϊdate����ֵ��Ϊvalue���ۼ�������
	void reset(final Date9 date, double value) {
		lastDate = date;
		this.value = value;
		sum = 0;
	}
}
