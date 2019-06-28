package com.tww.test.jdk.completableFuture;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class CompletableFutureTest {
    static DelayQueue delayQueue = new DelayQueue();
    public static void main(String[] args) {
        test1();
    }

    /**
     * thenCompose flatmap
     * thenApply map
     * 使用当前线程执行  Async 相对于当前线使用别的线程(ForkJoinPool)调用
     **/
    private static void test1() {
        List<Integer> ids = List.of(1,2,3,4,5,6,7,8,9,0);
        CompletableFuture<List<User>> future = CompletableFuture.completedFuture(ids).thenComposeAsync(list -> {
            System.out.println("sleep ====");
            sleep(5);
            List<CompletableFuture<User>> userFutureList = list.stream().map(id -> {
                CompletableFuture<String> nameFuture = getNameFuture(id).exceptionally(throwable -> {
                    System.out.println("name throw Exception");
                    return "error";
                });
                CompletableFuture<Integer> stateFuture = CompletableFuture.supplyAsync(() -> getState(id));
                return nameFuture.thenCombineAsync(stateFuture, (name, state) -> {
                    User user = new User();
                    user.setId(id);
                    user.setName(name);
                    user.setState(state);
                    return user;
                });
            }).collect(Collectors.toList());
            CompletableFuture<User>[] completableFuture =
                    userFutureList.toArray(new CompletableFuture[userFutureList.size()]);
            CompletableFuture<Void> allDone = CompletableFuture.allOf(completableFuture);
            System.out.println("wait allDone");
            if (false) {
                throw new RuntimeException("dsdf");
            }
            return allDone.thenApply(
                    all -> userFutureList.stream().map(CompletableFuture::join).collect(Collectors.toList()));
        }).handleAsync((users, throwable) -> {
            if (Objects.nonNull(throwable)) {
                throwable.printStackTrace();
                return null;
            } else {
                return users;
            }
        });
        System.out.println("end======");
        List<User> users = future.join();
        System.out.println(users);
    }

    private static void test3() {
        List<Integer> ids = List.of(1,2,3,4,5,6,7,8,9,0);
        Flux<Integer> flux = Flux.fromIterable(ids);
        flux.map(id -> {
            Mono<String> name = Mono.fromFuture(getNameFuture(id));
            Mono<Integer> state = Mono.justOrEmpty(getState(id));
            Flux.combineLatest(name,state,)

        })
    }

    private static CompletableFuture<String> getNameFuture(Integer id) {
        return CompletableFuture.supplyAsync(() -> {
            System.out.println("name in :" + id + "  ,size:" + ForkJoinPool.commonPool().getPoolSize() + "====thread:" + Thread.currentThread());
            sleep(5);
            int i = 1 / 0;
            return "name" + id;
        }, Executors.newFixedThreadPool(10));
    }

    private static Integer getState(Integer id) {
        System.out.println("******state in :" + id + "  ,size:" + ForkJoinPool.commonPool().getPoolSize() + "====thread:" + Thread.currentThread());
        sleep(5);
        return 0 - id;
    }

    private static void sleep(Integer time) {
        try {
            delayQueue.poll(time,TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


}


class User {
    private Integer id;
    private String name;
    private Integer state;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("User{");
        sb.append("id=").append(id);
        sb.append(", name='").append(name).append('\'');
        sb.append(", state=").append(state);
        sb.append('}');
        return sb.toString();
    }
}
