package pro.jianbing.aboutme.common.dto;

import lombok.Data;

@Data
public class BaseResult {
    private boolean result;
    private String message;
    private Object data;

    private static final String SYSTEM_ERROR = "系统异常！";

    public static BaseResult createBaseResult(boolean result, String message, Object data){
        BaseResult baseResult = new BaseResult();
        baseResult.setResult(result);
        baseResult.setMessage(message);
        baseResult.setData(data);
        return baseResult;
    }

    public static BaseResult success(String message, Object data){
        return createBaseResult(true,message,data);
    }

    public static BaseResult success(String message){
        return createBaseResult(true,message,null);
    }

    public static BaseResult success(Object data){
        return createBaseResult(true,null,data);
    }

    public static BaseResult success(){
        return createBaseResult(true,null,null);
    }

    public static BaseResult fail(String message, Object data){
        return createBaseResult(false,message,data);
    }

    public static BaseResult fail(String message){
        return createBaseResult(false,message,null);
    }

    public static BaseResult fail(Object data){
        return createBaseResult(false,null,data);
    }

    public static BaseResult fail(){
        return createBaseResult(false,null,null);
    }

    public static BaseResult systemError(){
        return createBaseResult(false,SYSTEM_ERROR,null);
    }
}
