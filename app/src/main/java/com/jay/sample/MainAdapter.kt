package com.jay.sample

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.jay.sample.dataModels.CategoryItem
import com.jay.sample.dataModels.ProductItem
import com.jay.sample.networkModules.NetworkManagerInstance.BASE_URL
import kotlinx.android.synthetic.main.child_row.*
import kotlinx.android.synthetic.main.parent_row.*

class MainAdapter(categories: List<CategoryItem>) :
        ExpandableRecyclerViewAdapter<ProductItem, CategoryItem, MainAdapter.CategoryViewHolder, MainAdapter.ProductsViewHolder>(
                categories as ArrayList<CategoryItem>, ExpandingDirection.VERTICAL
        ) {

    override fun onCreateParentViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        return CategoryViewHolder(
                LayoutInflater.from(parent.context).inflate(
                        R.layout.parent_row,
                        parent,
                        false
                )
        )
    }

    override fun onCreateChildViewHolder(child: ViewGroup, viewType: Int): ProductsViewHolder {
        return ProductsViewHolder(
                LayoutInflater.from(child.context).inflate(
                        R.layout.child_row,
                        child,
                        false
                )
        )
    }

    override fun onBindParentViewHolder(
            parentViewHolder: CategoryViewHolder,
            catergoryItem: CategoryItem,
            position: Int
    ) {
        parentViewHolder.parent_title.text = catergoryItem.name
    }

    override fun onBindChildViewHolder(
            childViewHolder: ProductsViewHolder,
            productItem: ProductItem,
            catergoryItem: CategoryItem,
            position: Int
    ) {
        childViewHolder.product_title.text = productItem.name
        Glide.with(childViewHolder.preview_img.context)
                .setDefaultRequestOptions(RequestOptions()
                        .circleCrop())
                .load(BASE_URL + productItem.url)
                .placeholder(R.drawable.ic_launcher_background)
                .into(childViewHolder.preview_img)

        childViewHolder.itemView.setOnClickListener {
            val intent = Intent(childViewHolder.itemView.context, ProductDetails::class.java)
            intent.putExtra(childViewHolder.itemView.context.resources.getString(R.string.product_id_key), productItem.id)
            childViewHolder.itemView.context.startActivity(intent)
        }
    }

    override fun onExpandedClick(
            categoryViewHolder: CategoryViewHolder,
            productsViewHolder: ProductsViewHolder,
            productItem: ProductItem,
            catergoryItem: CategoryItem
    ) {
    }

    override fun onExpandableClick(
            categoryViewHolder: CategoryViewHolder,
            catergoryItem: CategoryItem
    ) {
    }

    class CategoryViewHolder(v: View) : ExpandableRecyclerViewAdapter.ExpandableViewHolder(v)
    class ProductsViewHolder(v: View) : ExpandableRecyclerViewAdapter.ExpandedViewHolder(v)
}