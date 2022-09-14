import java.math.BigDecimal;
import java.time.LocalDate;

public class Operation {
    private final OperationEnum operationEnum;
    private LocalDate date;
    private BigDecimal balance;

    public Operation(OperationEnum operationEnum, LocalDate date, BigDecimal balance) {
        this.operationEnum = operationEnum;
        this.date = date;
        this.balance = balance;
    }

    public OperationEnum getOperationEnum() {
        return operationEnum;
    }

    public BigDecimal getBalance() {
        return balance;
    }
}
