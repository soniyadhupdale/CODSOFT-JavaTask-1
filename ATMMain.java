import java.util.Scanner;

class BankAccount 
{
    private double balance;

    public BankAccount(double initialBalance) 
    {
        balance = initialBalance;
    }

    public double getBalance() 
    {
        return balance;
    }

    public void deposit(double amount) 
    {
        balance += amount;
        System.out.println("Amount deposited: Rs" + amount);
    }

    public void withdraw(double amount) 
    {
        if (amount <= balance) 
        {
            balance -= amount;
            System.out.println("Amount withdrawn: Rs" + amount);
        } 
        else 
        {
            System.out.println("Insufficient balance....");
        }
    }
}

class ATM 
{
    private BankAccount account;

    public ATM(BankAccount account) 
    {
        this.account = account;
    }

    public void displayMenu() 
    {
        System.out.println("ATM Menu:");
        System.out.println("1. Withdraw");
        System.out.println("2. Deposit");
        System.out.println("3. Check Balance");
        System.out.println("4. Exit");
    }

    public void performTransaction() 
    {
        Scanner scanner = new Scanner(System.in);

        while (true) 
        {
            displayMenu();
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

            switch (choice) 
            {
                case 1:
                    System.out.print("Enter amount to withdraw: ");
                    double withdrawAmount = scanner.nextDouble();
                    account.withdraw(withdrawAmount);
                    break;
                case 2:
                    System.out.print("Enter amount to deposit: ");
                    double depositAmount = scanner.nextDouble();
                    account.deposit(depositAmount);
                    break;
                case 3:
                    System.out.println("Your current balance is: Rs" + account.getBalance());
                    break;
                case 4:
                    System.out.println("Thank you for using the ATM.");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice. Please try again....");
            }
        }
    }
}

public class ATMMain 
{
    public static void main(String[] args) 
    {
        BankAccount account = new BankAccount(1000.0);
        ATM atm = new ATM(account);
        atm.performTransaction();
    }
}
