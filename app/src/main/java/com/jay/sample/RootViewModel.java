package com.jay.sample;

import android.content.Context;
import android.net.ConnectivityManager;
import android.widget.Toast;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.jay.sample.dataModels.CategoryItem;
import com.jay.sample.dataModels.ProductItem;
import com.jay.sample.networkModules.GetDataResult;
import com.jay.sample.networkModules.NetworkManagerInstance;

import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/***
 * View model for handling core operations
 */
class RootViewModel extends ViewModel {
    private static RootViewModel rootViewModel;
    private static Context context;
    private MutableLiveData<List<CategoryItem>> mutableLiveData = new MutableLiveData<>();

    /***
     * Maintaining single instance for the model object
     * @param context
     * @return
     */
    static synchronized RootViewModel getInstance(Context context) {
        RootViewModel.context = context;
        if (rootViewModel == null) {
            rootViewModel = new RootViewModel();
        }
        return rootViewModel;
    }

    /***
     * Fetch data from server
     * @return results entries
     */
    LiveData<List<CategoryItem>> getDataResults() {
        return getMutableLiveData();
    }

    private MutableLiveData<List<CategoryItem>> getMutableLiveData() {

        if (!isNetworkAvailable(context)) {
            Toast.makeText(context, context.getResources().getString(R.string.network_alert),
                    Toast.LENGTH_SHORT).show();
        }

        if (mutableLiveData.getValue() != null)
            mutableLiveData.getValue().clear();

        GetDataResult service =
                NetworkManagerInstance.getNetworkManagerInstance().create(GetDataResult.class);
        HashMap<String, String> params = new HashMap<>();
        Call<List<CategoryItem>> call = service.getDataResults(params);
        call.enqueue(new Callback<List<CategoryItem>>() {
            @Override
            public void onResponse(Call<List<CategoryItem>> call,
                                   Response<List<CategoryItem>> response) {
                List<CategoryItem> core_items = response.body();
                mutableLiveData.setValue(core_items);
            }

            @Override
            public void onFailure(Call<List<CategoryItem>> call, Throwable t) {
                Toast.makeText(context, context.getResources().getString(R.string.service_error),
                        Toast.LENGTH_SHORT).show();
            }
        });
        return mutableLiveData;
    }

    /***
     * Check for network connection status
     * @param context
     * @return true if connection is active
     */
    boolean isNetworkAvailable(Context context) {
        ConnectivityManager connectivityManager =
                ((ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE));
        return connectivityManager.getActiveNetworkInfo() != null && connectivityManager.getActiveNetworkInfo().isConnected();
    }

    /***
     * Search product entry based on provided "id"
     * @param id
     * @return product model
     */
    ProductItem getProductBeanFromID(String id) {
        for (CategoryItem categoryItem : mutableLiveData.getValue()) {
            for (ProductItem productItem : categoryItem.getExpandingItems()) {
                if (productItem.getId().equalsIgnoreCase(id)) {
                    return productItem;
                }
            }
        }
        return null;
    }
}
