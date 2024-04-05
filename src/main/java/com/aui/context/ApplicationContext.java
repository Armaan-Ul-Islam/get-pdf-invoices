package com.aui.context;

import com.aui.service.InvoiceService;
import com.aui.service.UserService;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ApplicationContext {

    public static final ObjectMapper objectMapper =
            new ObjectMapper()
                    .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    public static final UserService userService = new UserService();
    public static final InvoiceService invoiceService = new InvoiceService(userService);

}