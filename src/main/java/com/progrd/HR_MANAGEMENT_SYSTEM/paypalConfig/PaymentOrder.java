package com.progrd.HR_MANAGEMENT_SYSTEM.paypalConfig;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@NoArgsConstructor
@Getter
@Setter
public class PaymentOrder {

    private String status;
    private String orderId;
    private String redirectUrl;

    public PaymentOrder(String status, String orderId) {
        this.status = status;
        this.orderId = orderId;
    }

    public PaymentOrder(String status) {
        this.status = status;
    }

    public PaymentOrder(String status, String orderId, String redirectUrl) {
        this.status = status;
        this.orderId = orderId;
        this.redirectUrl = redirectUrl;
    }
}
