package org.example.tp.demo1;

import lombok.Data;

/**
 * Created by xiedong
 * Date: 2022/9/2 20:33
 */
@Data
public class Response<T> {
    private boolean success;
    private String msg;
    private T data;
}
