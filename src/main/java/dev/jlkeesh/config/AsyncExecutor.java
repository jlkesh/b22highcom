package dev.jlkeesh.config;

import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;

@Component
public class AsyncExecutor {
    public void run(Runnable runnable) {
        CompletableFuture.runAsync(runnable);
    }
}
