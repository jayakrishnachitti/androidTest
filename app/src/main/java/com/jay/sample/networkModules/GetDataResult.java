package com.jay.sample.networkModules;

import com.jay.sample.dataModels.CategoryItem;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

/***
 * Interface for handling service connections
 */
public interface GetDataResult {
    @GET(".")
    Call<List<CategoryItem>> getDataResults(@QueryMap Map<String, String> params);
}
