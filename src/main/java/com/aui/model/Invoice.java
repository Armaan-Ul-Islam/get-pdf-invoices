package com.aui.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.math.BigDecimal;
import java.util.UUID;

@JsonPropertyOrder({ "id", "user_name", "amount", "pdf_url" })
public final class Invoice {
    private final String id;
    @JsonProperty("user_name")
    private final String userName;

    private final BigDecimal amount;

    @JsonProperty("pdf_url")
    private final String pdfUrl;

    public Invoice(String userName, BigDecimal amount, String cdnUrl) {
        this.id = UUID.randomUUID().toString();
        this.userName = userName;
        this.amount = amount;
        this.pdfUrl = cdnUrl + id;
    }

    public String getId() {
        return id;
    }

    public String getPdfUrl() {
        return pdfUrl;
    }

    public String getUserName() {
        return userName;
    }

    public BigDecimal getAmount() {
        return amount;
    }
}