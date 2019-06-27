package com.tww.test.jdk.completableFuture;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class CompletableFutureTest {
    public static void main(String[] args) {
        test1();
    }

    // thenCompose flatmap
    // thenApplay map
    private static void test1() {
        List<Integer> ids = new ArrayList<>();
        ids.add(5);
        ids.add(8);
        ids.add(10);
        ids.add(13);
        CompletableFuture<List<User>> future = CompletableFuture.completedFuture(ids).thenCompose(list -> {
            List<CompletableFuture<User>> userFutureList = list.stream().map(id -> {
                CompletableFuture<String> nameFuture = getNameFuture(id);
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
        }).exceptionally(th -> {
            th.printStackTrace();
            return new ArrayList<>();
        });
        List<User> users = future.join();
        System.out.println(users);
    }

    private static CompletableFuture<String> getNameFuture(Integer id) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            int i = 1 / 0;
            return "name" + id;
        });
    }

    private static Integer getState(Integer id) {
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return 0 - id;
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
