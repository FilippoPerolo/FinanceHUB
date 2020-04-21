package com.example.meetfinance;

import java.io.Serializable;

public class Symbol implements Serializable {
    private String symbol;
    private String name;
    private float price;
    private String exchange;

    public String getSymbol() {
        return symbol;
    }

    public String getName() {
        return name;
    }

    public float getPrice() {
        return price;
    }

    public String getExchange() {
        return exchange;
    }
}
