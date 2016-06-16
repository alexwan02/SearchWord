package org.alexwan.searchword.model;

/**
 * Response
 * Created by alexwan on 16/6/14.
 */
public class Response<T> {
    private int status_code;
    private String msg;
    private T data;
    public int getStatusCode() {
        return status_code;
    }

    public void setStatusCode(int status_code) {
        this.status_code = status_code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
