package s6game.auth.domain;

import java.math.BigDecimal;

public class LobbyEvent {
    private String customerName;
    private BigDecimal amountInEuros;

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }
}


