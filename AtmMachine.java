import java.util.Scanner;
class InvalidPinException extends RuntimeException{
    InvalidPinException()
    {

        super("You have entered incorrect pin:");
    }
}
class InsufficientBalanceException extends RuntimeException{
    InsufficientBalanceException(){

        super("Insufficient Balance:");
    }
}
public class AtmMachine {
    public static void main(String[] args) throws InterruptedException {
        Scanner in = new Scanner(System.in);
        int balance = 725000;
        int pass=3248;
        int pin;
        int amount;
        while(true){
            System.out.println();
            System.out.println("Welcome to ATM Project");
            Thread.sleep(1000);
            System.out.println("Please insert your Debit Card:");
            Thread.sleep(5000);
            System.out.println("Card inserted successfully");
            Thread.sleep(1000);
            System.out.println("Enter your 4 digit pin to continue:");
            pin=in.nextInt();
            if(pin!=pass){
                throw new InvalidPinException();
            }
            else{
                System.out.println("Success!");
            }
            System.out.println("Please choose one of the following services:");
            System.out.println("Type 1 for Cash Withdrawal: Maximum Cash is limited to 20000 per transaction");
            System.out.println("Type 2 for Balance Check:");
            System.out.println("Type 3 for Pin Change:");
            System.out.println("Type 4 for Cash Deposit:");
            int type=in.nextInt();
            switch (type) {
                case 1 -> {
                    System.out.println("Enter Amount:");
                    amount = in.nextInt();
                    balance =Withdrawal(amount, balance);
                }
                case 2 -> Balcheck(balance);
                case 3 ->  pass= Change(pin);
                case 4 -> balance=deposit(balance);

                default -> throw new IllegalStateException("Unexpected value: " + type);
            }
        }
        }

    static int Withdrawal(int amt,int bal) throws InterruptedException  {
        if(amt>bal){
            throw new InsufficientBalanceException();
        }

        else if(amt>20000){
            System.out.println("Sorry! Cash limit is 20000 per transaction:");
            return bal;
        }
        else if(amt%500==0){
            System.out.println("Please collect your cash");
            Thread.sleep(5000);
            System.out.println("The available balance is: " + (bal-amt));
            System.out.println(" Thank you, Will see you soon!!!");
            return (bal-amt);
        }
        else{
            System.out.println("Please enter amount in multiples of 500");
            return bal;
        }
    }
    static void Balcheck(int bal){

        System.out.println(" The available Balance in your Savings Account is: "+ bal);
    }
    static int Change(int password){
        Scanner in = new Scanner(System.in);
        System.out.println("Please enter your current pin: ");
        int x=in.nextInt();
        if(x!=password){
            throw new InvalidPinException();
        }
        else{
            System.out.println("Enter new Pin: ");
             int y = in.nextInt();
            System.out.println("Pin changed Successfully:");
            return y;
        }

    }
    static int deposit(int balance) {
        Scanner in = new Scanner(System.in);
        System.out.println("Please deposit the Cash:");
        int cash = in.nextInt();
        System.out.println("Cash deposited successfully");
        return balance + cash;
    }
}
