package com.phooper.yammynyammy.ui.global.adapters

import com.livermor.delegateadapter.delegate.KDelegateAdapter
import com.phooper.yammynyammy.R
import com.phooper.yammynyammy.entities.CartProduct
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_order_product.*
import javax.inject.Inject

class OrderProductDelegateAdapter @Inject constructor(private val picasso: Picasso) :
    KDelegateAdapter<CartProduct>() {

    override fun isForViewType(item: Any): Boolean = item is CartProduct

    override fun KViewHolder.onBind(item: CartProduct) {
        picasso.load(item.product.imageURL).into(product_image)
        product_description.text = item.product.desc
        product_title.text = item.product.title
        product_count.text = item.count.toString()
        product_price.text = item.totalPrice.toString()
    }

    override fun getLayoutId(): Int = R.layout.item_order_product
}