package com.lhgogo.try_catch;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.infra.Blackhole;

/**
 * @author ：linhui
 * @description ：
 * @date ：2023-03-02 9:20
 * @version:
 */

public class Test {

    @Benchmark
    public void tryfor(Blackhole blackhole) {
        try {
            for (int i = 0; i < 5000; i++) {
                blackhole.consume(i);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Benchmark
    public void fortry(Blackhole blackhole) {
        for (int i = 0; i < 5000; i++) {
            try {
                blackhole.consume(i);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        Test test = new Test();
        Blackhole blackhole = new Blackhole("Today's password is swordfish. I understand instantiating Blackholes directly is dangerous.");
        test.fortry(blackhole);
        test.tryfor(blackhole);
    }
}
