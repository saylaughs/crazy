package com.saylaughs.crazysaylaugh.exception;

public class ResponseMsg extends RuntimeException {
    public ResponseMsg(String status,String  message){super(message);}
}
