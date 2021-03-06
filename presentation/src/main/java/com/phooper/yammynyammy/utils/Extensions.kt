package com.phooper.yammynyammy.utils

import android.app.Activity
import android.content.Context
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.ph00.domain.models.*
import com.phooper.yammynyammy.R
import com.phooper.yammynyammy.entities.*
import kotlinx.coroutines.Job
import java.text.DateFormat
import java.util.*
import java.util.regex.Pattern

fun TextInputEditText.setHideLayoutErrorOnTextChangedListener(textInputLayout: TextInputLayout) {
    addTextChangedListener(object : TextWatcher {
        override fun afterTextChanged(s: Editable?) {
            textInputLayout.error = null
        }

        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
    })
}

fun Context.hideKeyboard(view: View) {
    (getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager)
        .hideSoftInputFromWindow(view.windowToken, 0)
}

fun Fragment.hideKeyboard() {
    view?.let { activity?.hideKeyboard(it) }
}

fun Fragment.setAppBarTitle(title: String) {
    (requireActivity() as AppCompatActivity).supportActionBar?.title = title
}

fun Activity.showMessage(msgRes: Int) =
    Snackbar.make(findViewById(android.R.id.content), msgRes, Snackbar.LENGTH_SHORT).show()

fun Fragment.showMessage(msgRes: Int) =
    requireActivity().showMessage(msgRes)

fun Fragment.showMessageAboveBottomNav(msgRes: Int) =
    Snackbar.make(
        requireActivity().findViewById(R.id.coordinator_layout),
        msgRes,
        Snackbar.LENGTH_SHORT
    ).show()

fun Date?.formatToString(): String = if (this == null) "" else
    DateFormat.getDateTimeInstance(
        DateFormat.MEDIUM,
        DateFormat.SHORT,
        Locale("en")
    ).format(this)

fun String.isEmailValid() =
    Pattern.compile(
        "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" +
                "\\@" +
                "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +
                "(" +
                "\\." +
                "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" +
                ")+"
    ).matcher(this).matches()

fun AddressModel.toPresentation() =
    Address(id = uid, street = street, houseNum = houseNum, apartNum = apartNum)

fun ProductModel.toPresentation(): Product =
    Product(id = id, imageURL = imageURL, title = title, desc = desc, price = price)

fun CartProductModel.toPresentation(): CartProduct =
    CartProduct(totalPrice = totalPrice, count = count, product = product.toPresentation())

fun UserModel.toPresentation(): User = User(phoneNum = phoneNum, name = name)

fun OrderAddressAndStatusModel.toPresentation(): OrderAddressAndStatus =
    OrderAddressAndStatus(address = address, status = status)

fun OrderModel.toPresentation(): Order =
    Order(
        id = uid,
        timestamp = timestamp,
        addressAndStatus = OrderAddressAndStatus(
            address = addressAndStatus.address,
            status = addressAndStatus.status
        ),
        products = products.map { it.toPresentation() },
        deliveryPrice = deliveryPrice,
        totalPrice = totalPrice
    )

fun formatAddress(houseNum: String, apartNum: String, street: String) =
    "Apt. $apartNum $houseNum $street"

fun Job?.cancelIfActive() {
    if (this?.isActive == true) {
        cancel()
    }
}