package com.progrd.HR_MANAGEMENT_SYSTEM.paypalConfig;

import com.paypal.core.PayPalHttpClient;
import com.paypal.orders.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.http.HttpResponse;
import java.util.List;
import com.paypal.orders.Order;

@Slf4j
@Service
public class PaypalService {

    @Autowired
    private PayPalHttpClient payPalHttpClient;

    public PaymentOrder createPayment(BigDecimal fee) {
        OrderRequest orderRequest = new OrderRequest();
        orderRequest.checkoutPaymentIntent("CAPTURE");
        AmountWithBreakdown amountBreakdown = new AmountWithBreakdown().currencyCode("USD").value(fee.toString());
        PurchaseUnitRequest purchaseUnitRequest = new PurchaseUnitRequest().amountWithBreakdown(amountBreakdown);
        orderRequest.purchaseUnits(List.of(purchaseUnitRequest));

        // Remove unnecessary URLs for backend-only testing
        orderRequest.applicationContext(new ApplicationContext());

        OrdersCreateRequest ordersCreateRequest = new OrdersCreateRequest().requestBody(orderRequest);

        try {
            HttpResponse<Order> orderHttpResponse = (HttpResponse<Order>) payPalHttpClient.execute(ordersCreateRequest);
            Order order = orderHttpResponse.body();

            // Return only order ID for Postman testing
            return new PaymentOrder("success", order.id());
        } catch (IOException e) {
            log.error(e.getMessage());
            return new PaymentOrder("Error");
        }
    }
}
