package com.trofimenko.for_metric.rest;

import io.micrometer.core.instrument.MeterRegistry;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ExampleController2 extends Animal {

    private static final String CONTEXT = "/api/v2/example";
    private static final String STREAMING_SS = "1000";

    private final MeterRegistry registry;


    @GetMapping(value = CONTEXT)
    public String greeting(@RequestParam(defaultValue = "Unknown") String name) {

        createOwnerMetric(name, STREAMING_SS);


        return "Example content for " + name;
    }


}