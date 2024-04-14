package com.aui.service;

import com.aui.model.Invoice;
import com.aui.model.InvoiceCreationPostRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class InvoiceService {

    private UserService userService;

   /* public InvoiceService(UserService userService) {
        this.userService = userService;
    }*/

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

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }
}
