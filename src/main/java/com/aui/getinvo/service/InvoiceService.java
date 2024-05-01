package com.aui.getinvo.service;

import com.aui.getinvo.model.Invoice;
import com.aui.getinvo.model.InvoiceCreationPostRequest;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class InvoiceService {

    private final UserService userService;

    public InvoiceService(UserService userService) {
        this.userService = userService;
    }

    @Value("${cdn.url}")
    private String cdnUrl;

    private final ConcurrentHashMap<String, Invoice> invoices = new ConcurrentHashMap<>();

    public Invoice craftInvoiceFromRequest(InvoiceCreationPostRequest postRequest){
        if (!userService.userExists(postRequest.getUserName())) {
            throw new IllegalStateException("User doesn't exist");
        }
        return new Invoice(postRequest.getUserName(), postRequest.getAmount(), cdnUrl);
    }

    public void storeInvoice(Invoice invoice){
        invoices.put(invoice.getId(), invoice);
    }

    public void storeInvoices(List<Invoice> invoiceList) {
        invoiceList.forEach( invoice ->
            invoices.put(invoice.getId(), invoice)
        );
    }

    public List<Invoice> findAll(){
        return new ArrayList<>(invoices.values());
    }

    @PostConstruct
    public void init(){
        System.out.println("Establishing connection with CDN...");
    }

    @PreDestroy
    public void shutdown(){
        System.out.println("Gracefully terminating CDN connection...");
    }
}
