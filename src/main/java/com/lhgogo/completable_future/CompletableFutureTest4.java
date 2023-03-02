package com.lhgogo.completable_future;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

public class CompletableFutureTest4 {
    public static void main(String[] args) {
        CompletableFuture<String> first = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "1号选手";
        });
        CompletableFuture<String> second = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "2号选手";
        });
        CompletableFuture<String> result = first.applyToEither(second, r -> r + "is winner");
        CompletableFuture<String> res = first.thenCombine(second, (x, y) -> x + y);
        System.out.println(result.join());
        new Thread().start();
        System.out.println(res.join());
    }
}