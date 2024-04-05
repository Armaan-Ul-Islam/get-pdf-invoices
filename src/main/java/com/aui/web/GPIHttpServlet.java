package com.aui.web;

import com.aui.context.ApplicationContext;
import com.aui.model.Invoice;
import com.aui.model.InvoiceCreationPostRequest;
import com.aui.service.InvoiceService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

public class GPIHttpServlet extends HttpServlet {
    private final ObjectMapper objectMapper = ApplicationContext.objectMapper;
    private final InvoiceService invoiceService = ApplicationContext.invoiceService;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        final String invoiceGetAllEndpoint = request.getContextPath() + "/invoice/all";

        if (request.getRequestURI().equalsIgnoreCase(invoiceGetAllEndpoint)) {
            response.setContentType("application/json; charset=UTF-8");
            List<Invoice> invoiceList = invoiceService.findAll();
            String invoiceListAsString = objectMapper.writeValueAsString(invoiceList);
            response.getWriter().println(invoiceListAsString);
        } else {
            response.setContentType("text/HTML; charset=UTF-8");
            response.getWriter().write(
                    """
                            <html>
                            <body>
                            <h1>Bismillah</h1>
                            <p>This is my very first, embedded Tomcat, HTML Page!</p>
                            </body>
                            </html>
                            """
            );
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        final String invoicePostEndpoint = request.getContextPath() + "/invoice";
        response.setContentType("application/json; charset=UTF-8");

        if (request.getRequestURI().equalsIgnoreCase(invoicePostEndpoint)) {
            try {
                InvoiceCreationPostRequest postRequest = objectMapper.readValue(request.getInputStream(), InvoiceCreationPostRequest.class);
                Invoice invoice = invoiceService.craftInvoiceFromRequest(postRequest);
                invoiceService.storeInvoice(invoice);

                System.out.println("Created invoice:" + invoice.toString());
                response.setStatus(HttpServletResponse.SC_CREATED);
                response.getWriter().println("Saved invoice successfully, generated id:" + invoice.getId());
            } catch (JsonProcessingException jsonProcessingException) {
                System.out.println("jsonProcessingException:" + jsonProcessingException);
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                response.getWriter().println("Invalid JSON format");
            } catch (Exception e) {
                System.out.println("Exception:" + e);
                response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                response.getWriter().println("An unexpected error occurred");
            }
        } else {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        }
    }
}