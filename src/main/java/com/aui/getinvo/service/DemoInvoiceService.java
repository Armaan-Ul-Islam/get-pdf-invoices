package com.aui.getinvo.service;

import com.aui.getinvo.model.Invoice;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@Profile("dev")
public class DemoInvoiceService {

    private final InvoiceService invoiceService;

    @Value("${cdn.url}")
    private String cdnUrl;

    @Value("${demo.invoice.1.user_name}")
    private String userName1;

    @Value("${demo.invoice.1.amount}")
    private BigDecimal amount1;

    @Value("${demo.invoice.2.user_name}")
    private String userName2;

    @Value("${demo.invoice.2.amount}")
    private BigDecimal amount2;

    @Value("${demo.invoice.3.user_name}")
    private String userName3;

    @Value("${demo.invoice.3.amount}")
    private BigDecimal amount3;

    public DemoInvoiceService(InvoiceService invoiceService) {
        this.invoiceService = invoiceService;
    }

    @PostConstruct
    private void loadDemoInvoices() {
        List<Invoice> invoiceList = List.of(
                new Invoice(userName1, amount1, cdnUrl),
                new Invoice(userName2, amount2, cdnUrl),
                new Invoice(userName3, amount3, cdnUrl)
        );
        invoiceService.storeInvoices(invoiceList);
    }
}
