package com.example.momopay.entity;

import com.example.momopay.Config.Environment;
import com.example.momopay.Config.PartnerInfo;
import com.example.momopay.share.Execute;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public abstract class AbstractProcess<T, V> {
    protected PartnerInfo partnerInfo;
    protected Environment environment;
    protected Execute execute = new Execute();

    public AbstractProcess(Environment environment) {
        this.environment = environment;
        this.partnerInfo = environment.getPartnerInfo();
    }

    public static Gson getGson() {
        return new GsonBuilder()
                .disableHtmlEscaping()
                .create();
    }

    public abstract PaymentResponse execute(PaymentRequest request);
}
