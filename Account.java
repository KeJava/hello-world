class Account { //�˻���
	private String id;	//�ʺ�
	private double balance;	//���
	private static double total = 0; //�����˻����ܽ��
    
	protected Account(){};
	//����������õĹ��캯����idΪ�˻�
	protected Account(final Date7 date, final String id){
		this.id = id;
		balance = 0;
		date.show();
		System.out.println("\t#" + id + " created");
	}
	
	//��¼һ���ʣ�dateΪ���ڣ�amountΪ��descΪ˵��
	protected void record(final Date7 date, double amount, final String desc){
		amount = Math.floor(amount * 100 + 0.5) / 100;	//����С�������λ
		balance += amount;
		total += amount;
		date.show();
		System.out.println("\t#" + id + "\t" + amount + "\t" + balance + "\t" + desc);
	}
	
	//���������Ϣ
	final protected void error(final String msg) {
		System.out.println("Error(#" + id + "): " + msg);
	}

	final String getId() { return id; }
	final double getBalance() { return balance; }
	static double getTotal() { return total; }
	
	//��ʾ�˻���Ϣ
    void show() {
		System.out.println(id + "\tBalance: " + balance);
	}

};
