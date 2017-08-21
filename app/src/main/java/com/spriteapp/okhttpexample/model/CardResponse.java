package com.spriteapp.okhttpexample.model;

/**
 * Created by kuangxiaoguo on 2017/8/20.
 */

public class CardResponse {

    private String msg;
    private int retCode;
    private CardModel result;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getRetCode() {
        return retCode;
    }

    public void setRetCode(int retCode) {
        this.retCode = retCode;
    }

    public CardModel getResult() {
        return result;
    }

    public void setResult(CardModel result) {
        this.result = result;
    }
}
