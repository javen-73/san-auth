package com.javen.sanauth.commons.returnUtils;

import lombok.Data;

@Data
public class Result {

    private int code;
    private String msg;
    private Object result;

    public Result(int code, String msg, Object result) {
        this.code = code;
        this.msg = msg;
        this.result = result;
    }

    public static Result buildResult(int code,String msg,Object result){
        return new Result(code,msg,result);
    }

    public static Result buildResultSuccess(String msg,Object result){
        return new Result(200,msg,result);
    }
    public static Result buildResultFail(int code,String msg){
        return new Result(code,msg,null);
    }
}
