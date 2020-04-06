package com.example.nextdoormvvm.buyer.ui.cart

import android.os.Parcelable
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.parcel.Parcelize


enum class UpdateType {
    ADD, REMOVE
}

object ShoppingCart {

    private lateinit var cartViewModel: CartViewModel
    private lateinit var activity: FragmentActivity

    fun addToCart(cartItem: CartItem) {
        StoreHouse.instance.cartItemList.add(cartItem)
        setToLiveData(activity)
    }

    fun setToLiveData(activity: FragmentActivity) {
        ShoppingCart.activity = activity
        cartViewModel = ViewModelProvider(activity).get(CartViewModel::class.java)
        cartViewModel.setCartDetail(getCartDetail())
    }

    fun updateCartQuantity(dishId: Int, updateType: UpdateType) {
        val matchingItem: CartItem? =
            StoreHouse.instance.cartItemList.find { c -> c.dishItem.dishId == dishId }

        matchingItem?.let {
            val index = StoreHouse.instance.cartItemList.indexOf(matchingItem)

            when (updateType) {
                UpdateType.ADD -> matchingItem.dishItem.quantity =
                    matchingItem.dishItem.quantity + 1
                UpdateType.REMOVE -> matchingItem.dishItem.quantity =
                    matchingItem.dishItem.quantity - 1
            }

            StoreHouse.instance.cartItemList[index] = matchingItem
        }
        setToLiveData(activity)
    }

    fun getCartDetail(): CartDetail {

        val cartItems = StoreHouse.instance.cartItemList
        val cartSummery = CartSummery()

        cartSummery.itemCount = cartItems.size
        cartSummery.QuantityCount = cartItems.map { it.dishItem.quantity }.sum()
        cartSummery.ItemTotal = cartItems.map { it.dishItem.unitPrice * it.dishItem.quantity }.sum()
        cartSummery.DeliveryCharge = cartItems.map { it.dishItem.deliveryCharge }.sum()
        cartSummery.PackingCharge = cartItems.map { it.dishItem.packingCharge }.sum()
        cartSummery.CartTotal =
            (cartSummery.ItemTotal + cartSummery.DeliveryCharge + cartSummery.PackingCharge)

        return CartDetail(cartItems, cartSummery)
    }

    fun removeFromCart(dishId: Int) {
        val matchingItem: CartItem? =
            StoreHouse.instance.cartItemList.find { c -> c.dishItem.dishId == dishId }
        matchingItem?.let {
            StoreHouse.instance.cartItemList.remove(matchingItem)
        }
        setToLiveData(activity)
    }

    fun clearCart() {
        StoreHouse.instance.cartItemList.clear()
        cartViewModel.receiveCartDetail().removeObservers(activity)
    }
}

private class StoreHouse private constructor() {
    internal var cartItemList: ArrayList<CartItem> = arrayListOf()

    init {
        println("Instance Created")
    }

    companion object {
        val instance: StoreHouse by lazy { StoreHouse() }
    }

}


// represents a single shopping cart item
data class CartItem(var dishItem: DishItem)

// represents a single DishItem in the cartitem
@Parcelize
data class DishItem(
    var dishId: Int=-1,
    var dishName: String="",
    var chefId: Int=-1,  // Keeping chef id for future, so that we can support order from different chef.
    var sellerId: Int=-1,
    var quantity: Int=-1,
    var unitPrice: Int=-1,
    var deliveryStartTime: String="",
    var deliveryEndTime: String="",
    var packingOptions: Int=-1,
    var packingCharge: Int=-1,
    var deliveryOptions: Int=-1,
    var deliveryCharge: Int=-1
): Parcelable

data class CartDetail(val cartItems: ArrayList<CartItem>, val cartSummery: CartSummery)
data class CartSummery(
    var itemCount: Int = 0,
    var QuantityCount: Int = 0,
    var ItemTotal: Int = 0,
    var DeliveryCharge: Int = 0,
    var PackingCharge: Int = 0,
    var CartTotal: Int = 0
)