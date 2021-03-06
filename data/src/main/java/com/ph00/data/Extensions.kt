package com.ph00.data

import com.ph00.data.Constants.Companion.FLOW_RETRY_DELAY
import com.ph00.data.entities.AddressEntity
import com.ph00.data.entities.OrderEntity
import com.ph00.data.entities.ProductEntity
import com.ph00.data.entities.ProductIdAndCountEntity
import com.ph00.domain.models.AddressModel
import com.ph00.domain.models.OrderModel
import com.ph00.domain.models.ProductIdAndCountModel
import com.ph00.domain.models.ProductModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.retry

fun ProductIdAndCountEntity.toModel(): ProductIdAndCountModel =
    ProductIdAndCountModel(productId = productId, count = count)

fun ProductIdAndCountModel.toEntity(): ProductIdAndCountEntity =
    ProductIdAndCountEntity(productId = productId, count = count)

fun ProductEntity.toModel(): ProductModel =
    ProductModel(id = id, title = title, price = price, desc = desc, imageURL = imageURL)

fun AddressModel.toEntity(): AddressEntity =
    AddressEntity(uid = uid, street = street, houseNum = houseNum, apartNum = apartNum)

fun AddressEntity.toModel(): AddressModel =
    AddressModel(uid = uid, street = street, houseNum = houseNum, apartNum = apartNum)

fun OrderModel.toEntity(): OrderEntity =
    OrderEntity(
        uid = uid,
        timestamp = timestamp,
        addressAndStatus = addressAndStatus,
        products = products,
        deliveryPrice = deliveryPrice,
        totalPrice = totalPrice
    )

fun OrderEntity.toModel(): OrderModel =
    OrderModel(
        uid = uid,
        timestamp = timestamp,
        addressAndStatus = addressAndStatus,
        products = products,
        deliveryPrice = deliveryPrice,
        totalPrice = totalPrice
    )


@ExperimentalCoroutinesApi
fun <T> kotlinx.coroutines.flow.Flow<T>.applyTwoRetriesOnError() =
    retry(2) { e -> (e is Exception).also { if (it) delay(FLOW_RETRY_DELAY) } }
