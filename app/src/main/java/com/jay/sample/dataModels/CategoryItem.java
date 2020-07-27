package com.jay.sample.dataModels;

import com.google.gson.annotations.SerializedName;
import com.jay.sample.ExpandableRecyclerViewAdapter;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class CategoryItem extends ExpandableRecyclerViewAdapter.ExpandableGroup<ProductItem> {
    @SerializedName("id")
    private String id;

    @SerializedName("name")
    private String name;

    @SerializedName("products")
    private List<ProductItem> products;

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @NotNull
    @Override
    public List<ProductItem> getExpandingItems() {
        return products;
    }
}

