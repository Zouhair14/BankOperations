import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class AccountTest {
    private Account account;
    private String balance;
    private BigDecimal balanceDecimal;
    @BeforeEach
    void setUp() {
        account = new Account(new BigDecimal("2000"),"Zouhair");
        balance = "1000";
        balanceDecimal = new BigDecimal(balance);
    }
    @Test
    public void depositTest(){
        account.deposit(balance);
        assertEquals(new BigDecimal("3000"),account.getBalance());
        assertEquals(balanceDecimal,account.getOperations().stream().findFirst().get().getBalance());
        assertEquals(OperationEnum.DEPOSIT,account.getOperations().stream().findFirst().get().getOperationEnum());
    }

    @Test
    public void withdrawalTest() throws Exception {
        account.withdrawal(balance);
        assertEquals(new BigDecimal("1000"),account.getBalance());
        assertEquals(balanceDecimal,account.getOperations().stream().findFirst().get().getBalance());
        assertEquals(OperationEnum.WITHDRAWAL,account.getOperations().stream().findFirst().get().getOperationEnum());
    }

    @Test
    public void withdrawalTestException(){

        assertThrows(Exception.class,()->{
            account.withdrawal("3000");
        });
    }

    @Test
    public void withdrawalAll(){
        account.withdrawalAll();
        assertEquals(BigDecimal.ZERO,account.getBalance());
        assertEquals(new BigDecimal("2000"),account.getOperations().stream().findFirst().get().getBalance());
        assertEquals(OperationEnum.WITHDRAWAL_ALL,account.getOperations().stream().findFirst().get().getOperationEnum());
    }

    @Test
    public void operationsTest() throws Exception {
        account.withdrawalAll();
        account.deposit(balance);
        account.deposit(balance);
        account.withdrawal(balance);
        account.deposit(balance);
        account.deposit(balance);
        account.withdrawal(balance);

        assertEquals(7,account.getOperations().size());
        assertEquals(new BigDecimal("2000"),account.getBalance());
    }
}