import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Account {

    private BigDecimal balance;
    private String name;
    private List<Operation> operations;

    public Account(BigDecimal balance, String name) {
        this.balance = balance;
        this.name = name;
        this.operations = new ArrayList<>();
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void deposit(String balance) {
        BigDecimal balanceDecimal = new BigDecimal(balance);
        Operation deposit = new Operation(OperationEnum.DEPOSIT, LocalDate.now(),balanceDecimal);
        this.balance = balanceDecimal.add(this.balance);
        operations.add(deposit);
        System.out.println("new balance = "+this.balance);
    }

    public void withdrawal(String balance) throws Exception {
        BigDecimal balanceDecimal = new BigDecimal(balance);
        if (this.balance.compareTo(balanceDecimal) == 1){
            Operation withdrawal = new Operation(OperationEnum.WITHDRAWAL, LocalDate.now(),balanceDecimal);
            this.balance = this.balance.subtract(balanceDecimal);
            operations.add(withdrawal);
            System.out.println("new balance = "+this.balance);
        }else {
            System.out.println("maximum allowed = "+ this.balance);
            throw new Exception("failed withdrawal");
        }
    }

    public void withdrawalAll() {
        Operation withdrawalAll = new Operation(OperationEnum.WITHDRAWAL_ALL, LocalDate.now(),this.balance);
        this.balance = BigDecimal.ZERO;
        operations.add(withdrawalAll);
        System.out.println("new balance = "+this.balance);
    }

    public List<Operation> getOperations() {
        return operations;
    }
}
