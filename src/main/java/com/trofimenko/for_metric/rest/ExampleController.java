package com.trofimenko.for_metric.rest;

import io.micrometer.core.instrument.LongTaskTimer;
import lombok.RequiredArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ExampleController extends Animal {
    private static final String CONTEXT = "/api/v1/example";
    private static final String APPLIED_JOURNAL_RIS_SS = "1425";




    @GetMapping(value = CONTEXT)
    public String greeting(@RequestParam(defaultValue = "Unknown") String name) throws InterruptedException {
        LongTaskTimer.Sample timerMetric = createTimerMetric(name);


        stepOne(name);
        timerMetric.stop();






        createOwnerMetric(name, APPLIED_JOURNAL_RIS_SS);

        return "Example content for " + name;
    }

    private void stepOne(String s) throws InterruptedException {
         long l = Long.valueOf(s).longValue();
        System.out.println("stepOne");
        Thread.sleep(l);
    }
}