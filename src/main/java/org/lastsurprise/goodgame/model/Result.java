package org.lastsurprise.goodgame.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Result implements Serializable {
    private static final long serializableID=1L;
    private int code;
    private String message;
    private Object data;

    public static Result commonErrorResult(String message){
        return new Result(-1,message,null);
    }

    public static Result commonSuccessResult(){
        return new Result(1,null,null);
    }

    public static Result commonSuccessResult(String message){
        return new Result(1,message,null);
    }

    public static Result commonSuccessResult(Object data){
        return new Result(1,null,data);
    }

}
