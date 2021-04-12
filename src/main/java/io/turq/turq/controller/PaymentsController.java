package io.turq.turq.controller;

import com.stripe.model.PaymentIntent;
import io.turq.turq.contstants.UrlConstants;
import io.turq.turq.model.payments.PaymentRequest;
import io.turq.turq.model.payments.PaymentResponse;
import io.turq.turq.service.interfaces.IPaymentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class PaymentsController {

  @Autowired
  private IPaymentsService paymentsService;


  @PostMapping(path = UrlConstants.PAYMENTS_URL + "/contest/{contestId}", consumes = "application/json", produces = "application/json")
  public ResponseEntity getPayments(@RequestBody PaymentRequest req, @RequestHeader(name="Authorization") String token, @PathVariable(value="contestId") Long contestId) {
      PaymentResponse res = paymentsService.processPayment(req, token, contestId);
      return ResponseEntity.ok(res);
  }
}
