
import java.util.Scanner; 

public class SavingsAccount8 extends Account8 { //�����˻���

	private Accumulator8 acc;	//����������Ϣ���ۼ���
	private double rate;		//����������

	//���캯��
	public SavingsAccount8(final Date8 date, final String id, double rate){
		super(date, id);
		this.rate = rate;
		acc = new Accumulator8(date, 0);
	}
	
	final double getRate() { return rate; }
	
	public void deposit(final Date8 date, double amount, final String desc){
		record(date, amount, desc);
		acc.change(date, getBalance());
	}
	
	public void withdraw(final Date8 date, double amount, final String desc){
		if (amount > getBalance()) {
			error("not enough money");
		} else {
			record(date, -amount, desc);
			acc.change(date, getBalance());
		}
	}
	
	public void settle(final Date8 date){
		if (date.getMonth() == 1) {	//ÿ���һ�¼���һ����Ϣ
			Date8 temDate = new Date8(date.getYear() - 1, 1, 1);
			double interest = acc.getSum(date) * rate
				/ (date.substruct(temDate));
			if (interest != 0)
				record(date, interest, "interest");
			acc.reset(date, getBalance());
		}
	}
	
	public static void main(String args[]){
		Date8 date = new Date8(2008, 11, 1);	//��ʼ����
		//���������˻�
		SavingsAccount8 sa1 = new SavingsAccount8(date, "S3755217", 0.015);
		SavingsAccount8 sa2 = new SavingsAccount8(date, "02342342", 0.015);
		CreditAccount8 ca = new CreditAccount8(date, "C5392394", 10000, 0.0005, 50);
		Account8 accounts[] = { sa1, sa2, ca };
		final int n = accounts.length ;	//�˻�����

		System.out.println("(d)deposit (w)withdraw (s)show (c)change day (n)next month (e)exit");
		
		String cmd;
		Scanner sin = new Scanner( System.in );        //ע�⣡��������������������
		cmd = sin.nextLine();
		do {
			//��ʾ���ں��ܽ��
			date.show();
			System.out.print( "\tTotal: " + getTotal() + "\tcommand> " );

			int index, day;
			double amount;
			String desc;

			switch (cmd) {
			case "d":	//�����ֽ�
				//cin >> index >> amount;
				index = sin.nextInt();
				amount = sin.nextDouble();
				//getline(cin, desc);
				desc = sin.nextLine();
				accounts[index].deposit(date, amount, desc);
				break;
				
			case "w":	//ȡ���ֽ�
				//cin >> index >> amount;
				index = sin.nextInt();
				amount = sin.nextDouble();
				//getline(cin, desc);
				desc = sin.nextLine();
				accounts[index].withdraw(date, amount, desc);
				break;
				
			case "s":	//��ѯ���˻���Ϣ
				for (int i = 0; i < n; i++) {
					System.out.print( "[" + i + "] " );
					accounts[i].show();
				}
				break;
				
			case "c":	//�ı�����
				//cin >> day;
				day = sin.nextInt();
				if (day < date.getDay())
					System.out.println( "You cannot specify a previous day" );
				else if (day > date.getMaxDay())
					System.out.println( "Invalid day" );
				else
					date = new Date8(date.getYear(), date.getMonth(), day);
				break;
			case "n":	//�����¸���
				if (date.getMonth() == 12)
					date = new Date8(date.getYear() + 1, 1, 1);
				else
					date = new Date8(date.getYear(), date.getMonth() + 1, 1);
				for (int i = 0; i < n; i++)
					accounts[i].settle(date);
				break;
			}
		} while (!cmd.equals("e"));
		
		sin.close();
	}
	
}

