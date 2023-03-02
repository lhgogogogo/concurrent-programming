package com.lhgogo.completable_future;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * @author ：linhui
 * @description ：
 * @date ：2023-02-22 11:25
 * @version:
 */

public class CompletableFutureTest {

    public static void main(String[] args) throws ExecutionException, InterruptedException, TimeoutException {
        CompletableFuture<String> completableFuture = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "hello CompletableFuture";
        });

        System.out.println(completableFuture.getNow("心急吃不了热豆腐"));
        System.out.println(completableFuture.get());
        System.out.println(completableFuture.get(1500, TimeUnit.MILLISECONDS));
        System.out.println(completableFuture.join());
        System.out.println(completableFuture.complete("未雨绸缪")+"\t"+completableFuture.join());

    }
}
