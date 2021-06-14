package io.turq.turq.service.impl;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;
import com.stripe.param.PaymentIntentCreateParams;
import io.turq.turq.entities.ContestEntity;
import io.turq.turq.entities.PaymentsEntity;
import io.turq.turq.entities.UserEntity;
import io.turq.turq.exceptions.UserBadRequestException;
import io.turq.turq.config.JwtTokenUtil;
import io.turq.turq.model.payments.PaymentRequest;
import io.turq.turq.repository.PaymentsRepository;
import io.turq.turq.model.payments.PaymentResponse;
import io.turq.turq.service.interfaces.IPaymentsService;
import io.turq.turq.service.interfaces.IContestService;
import io.turq.turq.service.interfaces.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class PaymentsService implements IPaymentsService {

    @Value("${stripe.key}")
    private String stripeKey;

    @Autowired
    private PaymentsRepository paymentsRepository;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private IUserService userService;

    @Autowired
    private IContestService contestService;

    public PaymentResponse processPayment(PaymentRequest req, String token, long contestId) {

        PaymentResponse res = new PaymentResponse();
        String authorEmail = jwtTokenUtil.getSubject(token);
        long amount = req.getAmount();

        try {
            UserEntity author = userService.findByEmail(authorEmail);
            ContestEntity contest = contestService.findById(contestId);
            Stripe.apiKey = stripeKey;

            PaymentIntentCreateParams params =
                    PaymentIntentCreateParams.builder()
                            .setAmount(amount)
                            // Only supporting USD for now
                            .setCurrency("usd")
                            // Only card payments today
                            .addPaymentMethodType("card")
                            .setReceiptEmail(authorEmail)
                            .build();

            PaymentIntent paymentIntent = PaymentIntent.create(params);
            paymentsRepository.save(new PaymentsEntity(amount, contest, author, 0));

            res.setSecret(paymentIntent.getClientSecret());

        } catch (StripeException e) {
            System.out.println("STRIPE ERROR: " + e);
            throw new UserBadRequestException("Stripe Failed");
        }

        return res;
    }

    public PaymentResponse processPayment(PaymentRequest req, String token) {

        PaymentResponse res = new PaymentResponse();
        String authorEmail = jwtTokenUtil.getSubject(token);
        long amount = req.getAmount();

        try {
            UserEntity author = userService.findByEmail(authorEmail);
            Stripe.apiKey = stripeKey;

            PaymentIntentCreateParams params =
                    PaymentIntentCreateParams.builder()
                            .setAmount(amount)
                            // Only supporting USD for now
                            .setCurrency("usd")
                            // Only card payments today
                            .addPaymentMethodType("card")
                            .setReceiptEmail(authorEmail)
                            .build();

            PaymentIntent paymentIntent = PaymentIntent.create(params);
            paymentsRepository.save(new PaymentsEntity(amount, author, 0));

            res.setSecret(paymentIntent.getClientSecret());

        } catch (StripeException e) {
            System.out.println("STRIPE ERROR: " + e);
            throw new UserBadRequestException("Stripe Failed");
        }

        return res;
    }
}
