package com.phooper.yammynyammy.ui.global.adapters

import com.livermor.delegateadapter.delegate.KDelegateAdapter
import com.phooper.yammynyammy.R
import com.phooper.yammynyammy.entities.Address
import com.phooper.yammynyammy.utils.formatAddress
import kotlinx.android.synthetic.main.item_address.*

class AddressDelegateAdapter :
    KDelegateAdapter<Address>() {

    var onItemClickListener: ((String) -> Unit)? = null
    var onEditBtnClickListener: ((String) -> Unit)? = null

    override fun getLayoutId() = R.layout.item_address

    override fun KViewHolder.onBind(item: Address) {
        address.text = formatAddress(item.houseNum, item.apartNum, item.street)
        item_layout.setOnClickListener { onItemClickListener?.invoke(item.id) }
        edit_btn.setOnClickListener { onEditBtnClickListener?.invoke(item.id) }
    }

    override fun isForViewType(item: Any): Boolean = item is Address
}

