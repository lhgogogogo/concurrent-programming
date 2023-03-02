package com.lhgogo.completable_future;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class CompletableFutureTest2 {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        CompletableFuture<Integer> completableFuture = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return 6;
        },executorService).handle((r,e)-> {
            int i=2/0;
            return r * 5;
        }).handle((r,e)-> {
            System.out.println(r);
            return r - 2;
        }).whenComplete((v, e) -> {
            System.out.println("计算结果："+v);
        }).exceptionally(e -> {
            System.out.println(e.getMessage());
            System.out.println(e);
            return null;
        });
        System.out.println("============主线程==========");
        executorService.shutdown();
    }
}