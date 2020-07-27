package com.jay.sample.dataModels;

import com.google.gson.annotations.SerializedName;

public class ProductItem {

    @SerializedName("id")
    private String id;

    @SerializedName("name")
    private String name;

    @SerializedName("url")
    private String url;

    @SerializedName("salePrice")
    private Price price;

    public String getPrice() {
        return price.amount + price.currency;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getUrl() {
        return url;
    }
}

class Price {

    @SerializedName("amount")
    String amount;

    @SerializedName("currency")
    String currency;

}
