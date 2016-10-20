class CreditAccount extends Account { //�����˻���
	private Accumulator acc;	//����������Ϣ���ۼ���
	private double credit;		//���ö��
	private double rate;		//Ƿ���������
	private double fee;			//���ÿ����

	final private double getDebt() {	//���Ƿ���
		double balance = getBalance();
		return (balance < 0 ? balance : 0);
	}

	//���캯��
	public CreditAccount(final Date7 date, final String id, double credit, double rate, double fee){
		super(date, id);
		this.rate = rate;
		acc = new Accumulator(date, 0);
		this.fee = fee;
		this.credit = credit;
	}
	
	final double getCredit() { return credit; }
	final double getRate() { return rate; }
	final double getFee() { return fee; }
	final double getAvailableCredit() {	//��ÿ�������
		if (getBalance() < 0) 
			return credit + getBalance();
		else
			return credit;
	}
	
	//�����ֽ�
	void deposit(final Date7 date, double amount, final String desc){
		record(date, amount, desc);
		acc.change(date, getDebt());
	}
	
	//ȡ���ֽ�
	void withdraw(final Date7 date, double amount, final String desc){
		if (amount - getBalance() > credit) {
			error("not enough credit");
		} 
		else {
			record(date, -amount, desc);
			acc.change(date, getDebt());
		}
	}
	
	//������Ϣ����ѣ�ÿ��1�յ���һ�θú���
	void settle(final Date7 date){
		double interest = acc.getSum(date) * rate;
		if (interest != 0)
			record(date, interest, "interest");
		if (date.getMonth() == 1)
			record(date, -fee, "annual fee");
		acc.reset(date, getDebt());
	}

	final void show(){
		super.show();
		System.out.println("\tAvailable credit:" + getAvailableCredit());
	}
}