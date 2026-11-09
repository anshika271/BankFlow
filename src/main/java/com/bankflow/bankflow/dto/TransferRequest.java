
package com.bankflow.bankflow.dto;

import java.math.BigDecimal;

public class TransferRequest {

    private String senderWalletNumber;
    private String receiverWalletNumber;
    private BigDecimal amount;

    public TransferRequest() {
    }

    public String getSenderWalletNumber() {
        return senderWalletNumber;
    }

    public void setSenderWalletNumber(String senderWalletNumber) {
        this.senderWalletNumber = senderWalletNumber;
    }

    public String getReceiverWalletNumber() {
        return receiverWalletNumber;
    }

    public void setReceiverWalletNumber(String receiverWalletNumber) {
        this.receiverWalletNumber = receiverWalletNumber;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
}
