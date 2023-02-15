package com.example.momopay;

import com.example.momopay.Config.Environment;
import com.example.momopay.entity.PaymentRequest;
import com.example.momopay.entity.PaymentResponse;
import com.example.momopay.enums.RequestType;
import com.example.momopay.process.CreateOrderMoMo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;

@Controller
public class MomoController {
    @GetMapping
    public ModelAndView testMomo() {
        ModelAndView modelAndView = new ModelAndView("index");
        modelAndView.addObject("payment", new PaymentRequest());
        return modelAndView;
    }
    @PostMapping("/pay")
    public String payToMomo(PaymentRequest paymentRequest) throws Exception {
        String returnURL = "https://google.com.vn";
        String notifyURL = "https://google.com.vn";
        String requestId = String.valueOf(System.currentTimeMillis());
        String orderId = String.valueOf(System.currentTimeMillis());
        Environment environment = Environment.selectEnv();
        PaymentResponse captureWalletMoMoResponse = CreateOrderMoMo.process(environment,
                orderId, requestId, Long.toString(paymentRequest.getAmount()), paymentRequest.getOrderInfo(),
                returnURL, notifyURL, "", RequestType.CAPTURE_WALLET, Boolean.TRUE);
        captureWalletMoMoResponse.getPayUrl();
        return "redirect:" + captureWalletMoMoResponse.getPayUrl();
    }
}
