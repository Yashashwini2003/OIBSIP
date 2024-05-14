import java.util.Scanner;

class BankAccount
{
    String name;
    String userName;
    String password;
    String accountNo;
    float balance=100000f;
    int transactions=0;
    String transactionHistory="";
    
    public void register(){
        Scanner sc=new Scanner(System.in);
        System.out.print("\nEnter your name :");
        this.name=sc.nextLine();
        System.out.print("\nEnter your username :");
        this.userName=sc.nextLine();
        System.out.print("\nEnter your password :");
        this.password=sc.nextLine();
        System.out.print("\nEnter your Account Number :");
        this.accountNo=sc.nextLine();
        System.out.println("\nRegistration completed..kindly login");
    }
    public boolean login(){
        boolean isLogin=false;
        Scanner sc=new Scanner(System.in);
        while(!isLogin){
            System.out.print("\nEnter your username :");
            String Username=sc.nextLine();
            if(Username.equals(userName)){
                while(!isLogin){
                    System.out.print("\nEnter your password :");
                    String Password=sc.nextLine();
                    if(Password.equals(password)){
                        System.out.print("\nLogin successful");
                        isLogin=true;
                    }
                    else{
                        System.out.print("\nIncorrect Password");
                    }
                }
            }
            else{
                System.out.println("\nUsername not found");
            }
        }
        return isLogin;
    }
    public void withdraw()
    {
        System.out.print("\nEnter amount to withdraw :");
        Scanner sc=new Scanner(System.in);
        float amount=sc.nextFloat();
        try{
            if(balance>=amount){
                transactions++;
                balance-=amount;
                System.out.println("\nWithdraw successful");
                String str=amount+"Rs Withdrawed\n";
                transactionHistory=transactionHistory.concat(str);
            }
            else{
                System.out.println("\nInsufficient balance");
            }
        }
        catch(Exception e){
        }

    }
    public void deposit(){
        System.out.print("\nEnter amount to deposit :");
        Scanner sc=new Scanner(System.in);
        float amount=sc.nextFloat();
        try{
            if(amount<=100000f){
                transactions++;
                balance += amount;
                System.out.println("\nSuccessfully Deposited");
                String str=amount+"Rs Deposited\n";
                transactionHistory=transactionHistory.concat(str);
            }
            else{
                System.out.println("\nSorry...Limit is 100000.00");
            }
        }
        catch(Exception e){
        }
    }
    public void transfer(){
        Scanner sc=new Scanner(System.in);
        System.out.print("\nEnter Receipent's Name :");
        String receipent=sc.nextLine();
        System.out.print("\nEnter amount to transfer :");
        float amount=sc.nextFloat();
        try{
            if(balance>=amount){
                if(amount<= 50000f){
                    transactions++;
                    balance -= amount;
                    System.out.println("\nSuccessfully Transfered to "+ receipent);
                    String str=amount+"Rs transfered to"+receipent+"\n";
                    transactionHistory=transactionHistory.concat(str);
                }
                else{
                    System.out.println("\nSorry...Limit is 50000.00");
                }
            }
            else{
                System.out.println("\nInsufficient balance");
            }
        }
        catch(Exception e){
        }
    }
    public void checkBalance(){
        System.out.println("\n"+balance +" Rs");
    }
    public void transactionHistory(){
        if(transactions==0)
        {
            System.out.println("\nEmpty");
        }
        else{
            System.out.println("\n"+ transactionHistory);
        }
    }
}
public class ATMInterface
{
    public static int takeIntegerInput(int limit){
        int input=0;
        boolean flag=false;
        while(!flag){
            try{
                Scanner sc=new Scanner(System.in);
                input =sc.nextInt();
                flag=true;
                if(flag && input>limit || input <1)
                {
                    System.out.println("Choose the number between 1 to "+limit);
                    flag=false;
                }
            }
            catch(Exception e){
                System.out.println("Enter only integer value");
                flag=false;
            }
        };
        return input;
    }
    public static void main(String[] args){
        System.out.println("\n*****WELCOME TO SBI ATM SYSTEM*****\n");
        System.out.println("1.Register \n2.Exit");
        System.out.print("Enter your Choice :");
        int choice=takeIntegerInput(2);
        if(choice==1)
        {
            BankAccount b=new BankAccount();
            b.register();
            while(true){
                System.out.println("\n1.Login \n2.Exit");
                System.out.print("Enter your choice :");
                int ch=takeIntegerInput(2);
                if(ch==1){
                    if(b.login()){
                        System.out.println("\n\n*****WELCOME BACK "+b.name+"*****\n");
                        boolean isFinished=false;
                        while(!isFinished){
                            System.out.println("\n1.Withdraw \n2.Deposit \n3.Transfer \n4.Check Balance \n5.Transaction History \n6.Exit");
                            System.out.print("Enter your choice :");
                            int c=takeIntegerInput(6);
                            switch(c){
                                case 1:
                                b.withdraw();
                                break;
                                case 2:
                                b.deposit();
                                break;
                                case 3:
                                b.transfer();
                                break;
                                case 4:
                                b.checkBalance();
                                break;
                                case 5:
                                b.transactionHistory();
                                break;
                                case 6:
                                isFinished=true;
                                break;
                            }
                        }
                    }
                }
                else{
                    System.exit(0);
                }
            }
        }
        else{
            System.exit(0);
        }
    }
}