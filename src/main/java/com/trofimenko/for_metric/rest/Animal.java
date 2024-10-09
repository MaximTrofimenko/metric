package com.trofimenko.for_metric.rest;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.LongTaskTimer;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.beans.factory.annotation.Autowired;


public abstract class Animal {

//    @Autowired
//    private  MeterRegistry registry;

    private MeterRegistry registry;


    //    @Autowired
//    protected Animal(MeterRegistry registry) {
//        this.registry = registry;
//    }
    @Autowired
    public final void setLogRepository(MeterRegistry registry) {
        this.registry = registry;
    }


    public void createOwnerMetric(String name, String ris) {
        String tag = switch (name.substring(0, 1)) {
            case "2" -> "2xx";
            case "4" -> "4xx";
            case "5" -> "5xx";
            default -> "name";
        };

        Counter.builder("paas.plsc.create.owner")
                .tag("identification.code", ris)
                .tag("code.response", tag)
                .register(registry)
                .increment();
    }

    public LongTaskTimer.Sample createTimerMetric(String ris){
        LongTaskTimer longTaskTimer = LongTaskTimer.builder("paas.plsc.timer")
                .tag("identification.code", "1000")
                .register(registry);
        final LongTaskTimer.Sample start = longTaskTimer.start();

        return start;

    }
}

