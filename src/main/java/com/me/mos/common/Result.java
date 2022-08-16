package com.me.mos.common;

/**
 * 返回前台的数据
 *
 * @Author Maple
 * @Date 2022/8/12 15:46
 * @Version 1.0
 */
public class Result<T> {
    private String code;
    private String message;
    private T data;

    public Result() {
    }

    public Result(T data) {
        this.data = data;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * 成功但不返回数据体
     * @return
     */
    public static Result success() {
        Result result = new Result<>();
        result.setCode("0");
        result.setMessage("success");
        return result;
    }

    /**
     * 成功并返回数据体
     * @param data 数据体
     * @param <T> 具体的类型
     * @return
     */
    public static <T> Result<T> success(T data) {
        Result<T> result = new Result<>();
        result.setCode("0");
        result.setMessage("success");
        result.setData(data);
        return result;
    }

    /**
     * 失败返回的信息
     * @param code
     * @param msg
     * @return
     */
    public static Result error(String code,String msg){
        Result result=new Result<>();
        result.setCode(code);
        result.setMessage(msg);
        return result;
    }


}
