package com.sdsu;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class FallbackController {

    @RequestMapping("/orderFallback")
    public Mono<String> orderServiceFallBack() {
        return Mono.just("Order service is down or taking too long to respond. Try again later!");
    }

    @RequestMapping("/productFallback")
    public Mono<String> productServiceFallBack() {
        return Mono.just("Product service is down or taking too long to respond. Try again later!");
    }

    @RequestMapping("/accountFallback")
    public Mono<String> accountServiceFallBack() {
        return Mono.just("Account service is down or taking too long to respond. Try again later!");
    }
}
