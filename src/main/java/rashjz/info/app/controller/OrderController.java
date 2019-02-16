package rashjz.info.app.controller;

import lombok.var;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyEmitter;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;
import rashjz.info.app.service.OrderService;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadLocalRandom;

import static org.springframework.web.servlet.mvc.method.annotation.SseEmitter.event;

@RestController
public class OrderController {
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/orders")
    public ResponseBodyEmitter orders() {
        var emitter = new ResponseBodyEmitter();
        var executor = Executors.newSingleThreadExecutor();
        executor.execute(() -> {
            var orders = orderService.findAll();
            try {
                for (var order : orders) {
                    randomDelay();
                    emitter.send(order);
                }
                emitter.complete();
            } catch (IOException e) {
                emitter.completeWithError(e);
            }
        });
        executor.shutdown();
        return emitter;
    }

    /*
    Server-Sent-Events

    there is a delay in sending each item to the client; just so you can see the
    different events coming in you wouldn’t do this in real code.
     */
    @GetMapping("/orders-sse")
    public SseEmitter ordersSSE() {
        SseEmitter emitter = new SseEmitter();
        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.execute(() -> {
            var orders = orderService.findAll();
            try {
                for (var order : orders) {
                    randomDelay();
                    var eventBuilder = event();
                    emitter.send(
                            eventBuilder
                                    .data(order)
                                    .name("order-created")
                                    .id(String.valueOf(order.hashCode())));
                }
                emitter.complete();
            } catch (IOException e) {
                emitter.completeWithError(e);
            }
        });
        executor.shutdown();
        return emitter;
    }

    private void randomDelay() {
        try {
            Thread.sleep(ThreadLocalRandom.current().nextInt(150));
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}