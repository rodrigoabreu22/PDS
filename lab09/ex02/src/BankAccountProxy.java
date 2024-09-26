package lab09.ex02.src;
/**
 * The BankAccountProxy class is a proxy class that provides controlled access to a real BankAccount object.
 * It implements the BankAccount interface and delegates method calls to the real BankAccount object.
 * Access to the methods is restricted based on the user type defined in the Company class.
 */
public class BankAccountProxy implements BankAccount {
    private BankAccount realBankAccount;
    
    /**
     * Constructs a BankAccountProxy object with the specified real BankAccount object.
     * 
     * @param realBankAccount the real BankAccount object to be proxied
     */
    public BankAccountProxy(BankAccount realBankAccount) {
        this.realBankAccount = realBankAccount;
    }
    
    @Override
    public void deposit(double amount) {
        realBankAccount.deposit(amount);
    }
    
    @Override
    public boolean withdraw(double amount) {
        if (Company.user == User.OWNER) {
            return realBankAccount.withdraw(amount);
        }
        System.out.println("Access denied.");
        return false;
    }
    
    @Override
    public double balance() {
        if (Company.user == User.OWNER) {
            return realBankAccount.balance();
        }
        System.out.println("Access denied.");
        return 0;
    }
}