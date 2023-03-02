package com.lhgogo.completable_future;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
class Student{
    private Integer id;
    private String name;
    private Integer age;
    private File file;
}

public class CompletableFutureTest3 {
    public static void main(String[] args) {

        long start = System.currentTimeMillis();
        List<Student> studies = new ArrayList<>();
        List<CompletableFuture<Student>> resultFuture = new ArrayList<>();
        for (int i = 0; i < 10000; i++) {
            int finalI = i;
            CompletableFuture<Student> stu = CompletableFuture
                    .supplyAsync(() -> {
                        System.out.println(Thread.currentThread().getName() +"\t"+ finalI);
                        Student student = new Student();
                        student.setId(finalI);
                        student.setName(finalI+"");
                        student.setAge(finalI);
                        System.out.println(Thread.currentThread().getName()+"\t"+student);
                        if(finalI % 5 == 0){
                            int count = 10/0;
                        }
                        return student;
                    }).exceptionally(e ->{
                        e.getMessage();
                        System.out.println(e.getMessage());
                        System.out.println(e);
                        return null;
                    });
            resultFuture.add(stu);
        }
        try { TimeUnit.MILLISECONDS.sleep(500); } catch (InterruptedException e) { e.printStackTrace();}

        resultFuture.forEach(future->{
            Student student;
            try {
                student = future.get();
                student.setFile(new File("E:\\file.txt"));
                if (student != null) {
                    studies.add(student);
                }
            } catch (Exception e){
               e.getMessage();
            }
        });
        System.out.println(studies.size());
        long end = System.currentTimeMillis();
        System.out.println(end - start +"毫秒");
    }
}