package com.google.appinventor.web.controllers.project;

public class RpcResult {
    public static final int SUCCESS = 0;

    private int result;
    private String output;
    private String error;
    private String extra;

    public RpcResult(int result, String output, String error) {
        this.result = result;
        this.output = output;
        this.error = error;
    }


    public RpcResult(boolean isSuccess, String output, String error) {
        this.result = isSuccess ? SUCCESS : ~SUCCESS;
        this.output = output;
        this.error = error;
    }

    public RpcResult() {}

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public String getOutput() {
        return output;
    }

    public void setOutput(String output) {
        this.output = output;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getExtra() {
        return extra;
    }

    public void setExtra(String extra) {
        this.extra = extra;
    }
}
