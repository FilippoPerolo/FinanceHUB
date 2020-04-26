package com.example.meetfinance;

import android.os.Parcel;
import android.os.Parcelable;

public class Symbol implements Parcelable {
    private String symbol;
    private String name;
    private float price;
    private String exchange;

    protected Symbol(Parcel in) {
        symbol = in.readString();
        name = in.readString();
        price = in.readFloat();
        exchange = in.readString();
    }

    public static final Creator<Symbol> CREATOR = new Creator<Symbol>() {
        @Override
        public Symbol createFromParcel(Parcel in) {
            return new Symbol(in);
        }

        @Override
        public Symbol[] newArray(int size) {
            return new Symbol[size];
        }
    };

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(symbol);
        dest.writeString(name);
        dest.writeFloat(price);
        dest.writeString(exchange);
    }
}
