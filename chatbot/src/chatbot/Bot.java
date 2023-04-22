package chatbot;
import java.util.*;
import java.sql.*;
import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
public class Bot 
{
	Scanner sc=new Scanner(System.in);
	Desktop d=Desktop.getDesktop();
	String s="BOT-> ";
	String u="YOU-> ";
	String name;
	
		public void chatbot() throws SQLException 
	   {
			
			System.out.print(s+"Kindly Enter 'HI' to start the bot"+"\n"+u);
			   
			String start=sc.nextLine();
			 if(start.equalsIgnoreCase("Hi"))
			    {
				 System.out.print(s+"Hello, Enter your name : "+"\n"+u);
				 name=sc.next();	
				 rec();
			    }
				 else
				 {
			    	System.out.println(s+"Wrong Input, Please try again.");
			    	chatbot();
			    }
			 System.exit(0);
			 }
			 
			public void rec() throws SQLException 
			{
		    System.out.println(s+"Welcome "+name+" to the SBI chatbot");    
		    System.out.println("      "+"Are you an existing customer? Reply(Y/N)");
		    System.out.print("      "+"If (N) then you will be redirected to the account opening webpage"+"\n"+u);
		    String b=sc.next();
		    if(b.equalsIgnoreCase("Y"))
		    {
		    	Menu();
		    }
		    else if(b.equalsIgnoreCase("N"))
		    {
		    	Accopen();
		    }
		    
		    else
		    {
                System.out.println(s+"WRONG INPUT, PLEASE TRY AGAIN!");
				rec();
		    }
		    System.exit(0);
	     }
			
		public void Accopen()
		{
			System.out.println(s+"I hope you would like to become a customer of our Bank, "); 
	    	System.out.println("       "+"Kindly choose any of the options you want to go for :"); 
	    	System.out.println("       "+"1.Savings Account "); 
	    	System.out.print("       "+"2.Current Account "+"\n"+u); 
	    	int aactype=sc.nextInt();
	    	
	    	if(aactype==1)
	    	{
	    	try {
				d.browse(new URI("https://sbi.co.in/web/personal-banking/accounts/saving-account/savings-bank-account"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (URISyntaxException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}}
	    	else if(aactype==2)
	    	{
	    		try {
				d.browse(new URI("https://sbi.co.in/web/personal-banking/accounts/current-accounts"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (URISyntaxException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    		
	    	}
	    	else {
	    		System.out.println(s+"WRONG INPUT, PLEASE TRY AGAIN!");
				Accopen();
	    	}
	    	System.out.println(s+"Glad to help you! BBYE");
			System.exit(0);
		}
		
		public void Menu() throws SQLException 
		{
			System.out.println(s+"Please choose any of the following services : ");    
			System.out.println("       "+"1.Check Account Information");    
			System.out.println("       "+"2.Apply for a Card");  
			System.out.println("       "+"3.Report a complaint");  
			System.out.print("       "+"4.Exit BOT"+"\n"+u);  
			int num=sc.nextInt();
			switch(num)
			{
			case 1 :
			{
				bankbal();
				break;
			}
			case 2 :
			{
				cardapp();
				break;
			}
			case 3 :
			{
			   complaint();
			   break;
			}
			case 4 : 
				System.out.println(s+"Glad to help you! BBYE");
				System.exit(0);
			
			default : 
			{
				System.out.println(s+"WRONG INPUT, TRY AGAIN!");
			    Menu();
			}
			}
			System.exit(0);
		}
		
		public void bankbal() throws SQLException
		{
			String url="jdbc:mysql://localhost:3306/jdbcdemo";
			String username="root";
			String password="";
			
				
		    Connection con = DriverManager.getConnection(url,username,password);
			Statement smt = con.createStatement();
		        System.out.print(s+"Enter your Account Number : "+"\n"+u);
		        int anum=sc.nextInt();
				String sql = " select * from Accounts where AccountID= "+anum;
				ResultSet rs = smt.executeQuery(sql);
				
				if(rs.next())
				{
					int accountid = rs.getInt("AccountID");
					String accountname = rs.getString("AccountName");
					int balance = rs.getInt("Balance");
					int number = rs.getInt("number");
					System.out.println("Account Number : "+accountid);
					System.out.println("Account Name : "+accountname);
					System.out.println("Account Balance : "+balance);
					System.out.println("Mobile Number : "+number);
				}
				else
				{
					System.out.println(s+"NO SUCH ENTRY FOUND IN THE DATABSE, TRY AGAIN ");
					bankbal();
				}
				con.close();
				
				System.out.print(s+"Want to go back to Main menu ? : (Y/N)"+"\n"+u);
				String back=sc.next();
				if(back.equalsIgnoreCase("y"))
				{
					Menu();
				}
				else
				{
					System.out.println(s+"Glad to help you! BBYE");
					System.exit(0);
				}
			}
			
		public void cardapp() throws SQLException
		{
			System.out.println(s+"Which card do you want to apply ? ");
			System.out.println("       "+"1.Debit Card (D)");
			System.out.print("       "+"2.Credit Card (C)"+"\n"+u);
			String card=sc.next();
			
			if(card.equalsIgnoreCase("D"))
			{
				try {
					d.browse(new URI("https://sbi.co.in/web/personal-banking/cards/debit-card"));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (URISyntaxException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			else if(card.equalsIgnoreCase("C"))
			{
				try {
					d.browse(new URI("https://www.sbicard.com/en/personal/credit-cards.page"));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (URISyntaxException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			else
			{
				System.out.println(s+"Wrong INPUT ! TRY AGAIN : ");
				cardapp();
			}
			System.out.print(s+"Want to go back to Main menu ? : (Y/N)"+"\n"+u);
			String back=sc.next();
			if(back.equalsIgnoreCase("y"))
			{
				Menu();
			}
			else
			{
				System.out.println(s+"Glad to help you! BBYE");
				System.exit(0);
			}
		}
		
		public void complaint() throws SQLException 
		{
			System.out.println(s+"What complaint do you want to register ? ");
			System.out.println("       "+"1.Register Complaint ");
			System.out.print("       "+"2.Register Complaint of Unauthorised Transaction "+"\n"+u);
			int com=sc.nextInt();
			
			if(com==1)
			{
				try {
					d.browse(new URI("https://crcf.sbi.co.in/ccf/"));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (URISyntaxException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			else if(com==2)
			{
				try {
					d.browse(new URI("https://crcf.sbi.co.in/ccf/home/UnAuth/"));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (URISyntaxException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			else
			{
				System.out.println(s+"Wrong INPUT ! TRY AGAIN : ");
				complaint();
			}
			System.out.print(s+"Want to go back to Main menu ? : (Y/N)"+"\n"+u);
			String back=sc.next();
			if(back.equalsIgnoreCase("y"))
			{
				Menu();
			}
			else
			{
				System.out.println(s+"Glad to help you! BBYE");
				System.exit(0);
			}
		}
		
		public static void main(String[] args) throws SQLException 
		{	
			Bot obj=new Bot();
			obj.chatbot();
			obj.rec();
			obj.Menu();
		    obj.bankbal();
			obj.cardapp();
			obj.complaint();
			obj.Accopen();
	    }
}
