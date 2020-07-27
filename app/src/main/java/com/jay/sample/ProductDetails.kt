package com.jay.sample

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.jay.sample.networkModules.NetworkManagerInstance
import kotlinx.android.synthetic.main.product_details.*

class ProductDetails : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.product_details)

        val singletonNameViewModelFactory = SingletonViewModelFactory(RootViewModel.getInstance(this))
        val rootViewModel = ViewModelProviders.of(this, singletonNameViewModelFactory).get(RootViewModel::class.java)
        val id = intent.getStringExtra(resources.getString(R.string.product_id_key))

        var productItem = rootViewModel.getProductBeanFromID(id)
        product_title.text = productItem.name
        product_price.text = productItem.price
        Glide.with(this)
                .setDefaultRequestOptions(RequestOptions()
                        .circleCrop())
                .load(NetworkManagerInstance.BASE_URL + productItem.url)
                .placeholder(R.drawable.ic_launcher_background)
                .into(product_img)
    }
}