package com.operantic.demo.model;

/**
 * @author Christian Amani on 04/12/2018.
 */
public class Currency {

    private String code;

    private String name;

    private String symbol;

    public Currency() {
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }
}
