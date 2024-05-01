package com.aui.getinvo.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

public final class InvoiceCreationPostRequest {
    @JsonProperty("user_name")
    private final String userName;

    private final BigDecimal amount;

    @JsonCreator
    public InvoiceCreationPostRequest(
            @JsonProperty("user_name") String userName
            , @JsonProperty("amount") BigDecimal amount
    ) {
        if (userName == null || amount == null) {
            throw new IllegalArgumentException("userName and amount must not be null");
        }
        this.userName = userName;
        this.amount = amount;
    }

    public String getUserName() {
        return userName;
    }

    public BigDecimal getAmount() {
        return amount;
    }
}
