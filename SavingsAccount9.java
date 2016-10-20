
import java.util.ArrayList;
import java.util.Scanner;

public class SavingsAccount9 extends Account9 { //�����˻���
private
	Accumulator9 acc;	//����������Ϣ���ۼ���
	double rate;		//����������
public
	//���캯��
	SavingsAccount9(final Date9 date, final String id, double rate){
	    super(date, id);
	    this.rate = rate;
	    acc = new Accumulator9(date, 0);
}
	final double getRate() { return rate; }
	
    void deposit(final Date9 date, double amount, final String desin){
    	record(date, amount, desin);
    	acc.change(date, getBalance());
    }
    
	void withdraw(final Date9 date, double amount, final String desin){
		if (amount > getBalance()) {
			error("not enough money");
		} else {
			record(date, -amount, desin);
			acc.change(date, getBalance());
		}
	}
	
	void settle(final Date9 date){
		if (date.getMonth() == 1) {	//ÿ���һ�¼���һ����Ϣ
			Date9 temDate = new Date9(date.getYear() - 1, 1, 1);
			double interest = acc.getSum(date) * rate
				/ (date.substruct(temDate));
			if (interest != 0)
				record(date, interest, "interest");
			acc.reset(date, getBalance());
		}
	}
	
	
	
	public static void main(String args[]){
		Date9 date = new Date9(2008, 11, 1);	//��ʼ����
		ArrayList<Account9> accounts = new ArrayList<Account9>();	//�����˻����飬Ԫ�ظ���Ϊ0
		System.out.println( "(a)add account (d)deposit (w)withdraw (s)show (c)change day (n)next month (e)exit" );
		
		String cmd;
		Scanner sin = new Scanner( System.in );  
		do {
			//��ʾ���ں��ܽ��
			date.show();
			System.out.print( "\tTotal: " + getTotal() + "\tcommand> " );

			String type;
			int index, day;
			double amount, credit, rate, fee;
			String id, desin;
			Account9 account;

			cmd = sin.next();
			switch (cmd) {
			case "a"://�����˻�
				type = sin.next();
				id = sin.next();
				if (type.equals("s")) {
				    rate = sin.nextDouble();
					account = new SavingsAccount9(date, id, rate);
				} else {
					credit = sin.nextDouble();
					rate = sin.nextDouble() ;
					fee = sin.nextDouble();
					account = new CreditAccount9(date, id, credit, rate, fee);
				}
//				accounts.resize(accounts.getSize() + 1);
//				accounts[accounts.getSize() - 1] = account;
				accounts.add(account);
				break;
			case "d":	//�����ֽ�
				index  = sin.nextInt();
				amount = sin.nextDouble();
				desin = sin.nextLine();
				accounts.get(index).deposit(date, amount, desin);
				break;
			case "w":	//ȡ���ֽ�
				index  = sin.nextInt();
				amount = sin.nextDouble();
//				getline(cin, desin);
				desin = sin.nextLine();
				accounts.get(index).withdraw(date, amount, desin);
				break;
			case "s":	//��ѯ���˻���Ϣ
				for (int i = 0; i < accounts.size(); i++) {
					System.out.print( "[" + i + "] " );
					accounts.get(i).show();
				}
				break;
			case "c":	//�ı�����
				day = sin.nextInt();
				if (day < date.getDay())
					System.out.print( "You cannot specify a previous day" );
				else if (day > date.getMaxDay())
					System.out.print( "Invalid day" );
				else
					date = new Date9(date.getYear(), date.getMonth(), day);
				break;
			case "n":	//�����¸���
				if (date.getMonth() == 12)
					date = new Date9(date.getYear() + 1, 1, 1);
				else
					date = new Date9(date.getYear(), date.getMonth() + 1, 1);
				for (int i = 0; i < accounts.size(); i++)
					accounts.get(i).settle(date);
				break;
			}
		} while (!cmd.equals("e"));

//		for (int i = 0; i < accounts.size(); i++)
//			delete accounts[i];

		sin.close();
	}
}


