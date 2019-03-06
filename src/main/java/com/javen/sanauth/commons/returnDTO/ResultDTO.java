package com.javen.sanauth.commons.returnDTO;

import lombok.Data;

@Data
public class ResultDTO {

    private int code;
    private String msg;
    private Object result;

    public ResultDTO(int code, String msg, Object result) {
        this.code = code;
        this.msg = msg;
        this.result = result;
    }

    public static ResultDTO buildResult(int code, String msg, Object result){
        return new ResultDTO(code,msg,result);
    }

    public static ResultDTO buildResultSuccess(String msg, Object result){
        return new ResultDTO(200,msg,result);
    }
    public static ResultDTO buildResultFail(int code, String msg){
        return new ResultDTO(code,msg,null);
    }
}
