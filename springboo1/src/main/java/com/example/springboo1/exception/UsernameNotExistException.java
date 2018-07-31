package com.example.springboo1.exception;

public class UsernameNotExistException extends RuntimeException{

    public UsernameNotExistException() {
        super("用户不存在");
    }
}
