package io.turq.turq.service.interfaces;

import io.turq.turq.model.payments.PaymentRequest;
import io.turq.turq.model.payments.PaymentResponse;

import java.util.List;

public interface IPaymentsService {
    PaymentResponse processPayment(PaymentRequest req, String token, long contestId);
    PaymentResponse processPayment(PaymentRequest req, String token);
}
