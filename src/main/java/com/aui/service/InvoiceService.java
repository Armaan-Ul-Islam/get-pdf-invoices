package com.aui.service;

import com.aui.context.ApplicationContext;
import com.aui.model.Invoice;
import com.aui.model.InvoiceCreationPostRequest;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

public class InvoiceService {
    private final UserService userService;

    public InvoiceService(UserService userService) {
        this.userService = userService;
    }

    private final ConcurrentHashMap<String, Invoice> invoices = new ConcurrentHashMap<>();

    public Invoice craftInvoiceFromRequest(InvoiceCreationPostRequest postRequest){
        if (!userService.userExists(postRequest.getUserName())) {
            throw new IllegalStateException("User doesn't exist");
        }
        return new Invoice(postRequest.getUserName(), postRequest.getAmount());
    }

    public void storeInvoice(Invoice invoice){
        invoices.put(invoice.getId(), invoice);
    }

    public List<Invoice> findAll(){
        return new ArrayList<>(invoices.values());
    }
}