package org.example.tp.set;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * Created by xiedong
 * Date: 2022/9/29 20:48
 */
public class UserHandler {
//    private Set<Long> userIds = new HashSet<>();
    private Set<Long> userIds = new LinkedHashSet<>();

    public void init(){
        this.userIds.add(123456789L);
        this.userIds.add(1234568L);
        this.userIds.add(1234569L);
        this.userIds.add(1234560L);
    }


    public static void main(String[] args) {

        UserHandler userHandler = new UserHandler();
        userHandler.init();;
        boolean contains = userHandler.userIds.contains(123456789L);
        System.out.println();
    }
}
