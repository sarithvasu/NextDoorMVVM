package com.example.nextdoormvvm.buyer.ui.home.checkout

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Parcelable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.nextdoormvvm.R
import com.example.nextdoormvvm.buyer.network.apiservice.BuyerApiService
import com.example.nextdoormvvm.buyer.network.datasource.BuyerDataSourceImpl
import com.example.nextdoormvvm.buyer.network.injection.BuyerModelGenerator
import com.example.nextdoormvvm.buyer.network.injection.BuyerViewModelTypeEnum
import com.example.nextdoormvvm.buyer.network.post.OnlineOrder
import com.example.nextdoormvvm.buyer.network.post.Order
import com.example.nextdoormvvm.buyer.network.post.PostPurchaseOrder
import com.example.nextdoormvvm.buyer.network.post.UPIPayment
import com.example.nextdoormvvm.buyer.network.response.CartItem
import com.example.nextdoormvvm.buyer.repository.BuyerRepositoryImpl
import com.example.nextdoormvvm.buyer.ui.home.adapter.CheckoutAdapter
import com.example.nextdoormvvm.buyer.ui.cart.CartViewModel
import com.example.nextdoormvvm.buyer.ui.cart.ShoppingCart
import com.example.nextdoormvvm.internal.*
import com.example.nextdoormvvm.internal.interceptor.ConnectivityInterceptorImpl
import kotlinx.android.synthetic.main.check_out_fragment.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException
import java.util.*
import kotlin.collections.ArrayList
import kotlin.math.ceil


@Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS", "SameParameterValue")
class CheckOutFragment : ScopedFragment() {

    companion object {
        fun newInstance() =
            CheckOutFragment()

        private const val UPI_PAYMENT = 0
         const val  CONSTANT_HUNDRED = 100
         const val  CONSTANT_FIFTY = 50
    }

    private var txt: String=""
    private  var payment =  UPIPayment()
    private lateinit var cartModel: CartViewModel
    private lateinit var viewModel: CheckOutViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.check_out_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
     /*   val checkOutCOD = generateCheckoutByCOD()
        viewModel = BuyerModelGenerator.getModel(
            this,
            BuyerViewModelTypeEnum.CheckOutViewModel,
            checkOutCOD
        ) as CheckOutViewModel*/
        setControls()
        configureCheckoutRecycleView()
        setBillSummery()
        //  checkInstock()
        tv_place_order_checkout.setOnClickListener {

            if (tv_payment_cash_checkout.isChecked) {
                postCheckOutCOD()

            } else {

                 checkInstock()
                //postCheckOutUPI()

            }
            // payUsingUPI(ShoppingCart.getTotalCartAmount().toString(),"Sarith","Nextdoor Payment","sarithvasu@oksbi")
            // startActivity(Intent(this, OrderConfirmationActivity::class.java))
        }
    }



    @SuppressLint("SetTextI18n")
    private fun setBillSummery() {
        cartModel= ViewModelProvider(activity!! ).get(CartViewModel::class.java)
        cartModel.receiveCartDetail().observe(activity!!, Observer {
            tv_item_total_value.text= "₹ ${it.cartSummery.ItemTotal}"
            tv_total_delivery_charges_value.text="₹ ${it.cartSummery.DeliveryCharge}"
            tv_totalpacking_charges_value.text="₹ ${it.cartSummery.PackingCharge}"
            tv_cart_total_value.text="₹ ${it.cartSummery.CartTotal}"
        })


    }




    private fun configureCheckoutRecycleView() {
        val layoutManager1 = androidx.recyclerview.widget.LinearLayoutManager(context)
        layoutManager1.orientation = RecyclerView.VERTICAL
        recyclerView_checkout.layoutManager = layoutManager1
        recyclerView_checkout.apply {
            recyclerView_checkout.adapter = CheckoutAdapter()
        }
    }
    /* working code. pls call from appropriate method*/
    private fun checkInstock(){
        //busy_bar.visibility= View.VISIBLE
        //val cartItemList = ObjectGenerator.generateInStock()
        val cartItemList = createInStock()
        val viewModel = BuyerModelGenerator.getModel(
            this,
            BuyerViewModelTypeEnum.CheckOutStockViewModel,
            cartItemList
        ) as CheckOutStockViewModel

        GlobalScope.launch(Dispatchers.Main) {
            try {
                val newDishResponse = viewModel.fetchStock.await()

                newDishResponse.observe(viewLifecycleOwner, Observer {
                    //busy_bar.visibility= View.GONE
                    if (it == null) return@Observer
                    if(it.isEmpty()){
                        payUsingUPIApp( "Sarith",
                            "1",
                            "aswini.72693@okhdfcbank",
                            "Nextdoor Payment")
                    }
                    /*   else if(it[0].StockValue.toIntOrNull()!=null) {
                         *//*  Utility.createCustomDialog(
                            this@CheckoutActivity,
                            "Only ${verficationList!![0].StockValue} is/are avalable",true*//*
                        //)
                    }*//*else{
                        *//*Utility.createCustomDialog(
                            this@CheckoutActivity,
                            "avail upto   ${verficationList!![0].StockValue}",true
                        )*//*
                    }*/
                })
            } catch (e: HttpException) {
                //busy_bar.visibility= View.GONE
                //   val gson = Gson()
                // textView.text =e.response().errorBody()?.string()
            } catch (e: IOException) {
                // busy_bar.visibility= View.GONE
                // textView.text = e.toString()
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode) {
            UPI_PAYMENT -> {
                //(findViewById(R.id.result) as TextView).text = "request code"
                // result.setText("request code$resultCode")
                if (AppCompatActivity.RESULT_OK == resultCode) if (data != null) {
                    txt = data.getStringExtra("response")
                    //(findViewById(R.id.result) as TextView).setText(txt)
                    Log.e("UPI", "" + txt)
                    val datalist = java.util.ArrayList<String>()
                    datalist.add(txt)
                    //(findViewById(R.id.result) as TextView).text = "data not null"
                    upiPaymentOpration(datalist)
                } else {

                    Log.e("UPI", "data is null")
                    val datalist = java.util.ArrayList<String>()
                    datalist.add("Nothing")
                    upiPaymentOpration(datalist)
                }/* else {

                   *//* Log.e("UPI", "data is null")
                    val datalist = java.util.ArrayList<String>()
                    datalist.add("Nothing")
                    upiPaymentOpration(datalist)*//*
                }*/
            }
        }


    }

    private fun upiPaymentOpration(datalist: java.util.ArrayList<String>) {

        var str: String? = datalist[0]
        // ((TextView)findViewById(R.id.result)).setText("data is "+datalist.get(0));

        if (str == null) str = "discard"
        var status: String
        var approveRef: String
        val response = str.split("&".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
        val append = StringBuilder()
        for (i in response.indices) {
            val equlStr =
                response[i].split("=".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
            append.append(response[i] + "\n")

            if (equlStr.size >= 2) {
                if (equlStr[0].toLowerCase(Locale.getDefault()) == "Status".toLowerCase(Locale.getDefault())) {
                    status = equlStr[1].toLowerCase(Locale.getDefault())
                    //  ((TextView)findViewById(R.id.result)).setText("status is "+status);
                    if (status == "success") {
                        //result.setText("status"+status);
                        Log.e("UPI", "" + status)
                        payment.TransactionStatus = status
                        Log.e("UPI SUCCESS 11111", "" + status)

                        postCheckOutUPI()
                        /* Utility.showToast(this, "success")*/
                    } /*else {
                           *//* Utility.createCustomDialog(this,  "PAYMENT FAILED",true)*//*
                        }*/
                } else if (equlStr[0].toLowerCase(Locale.getDefault()) == "approval Ref".toLowerCase(
                        Locale.getDefault()
                    ) || equlStr[0].toLowerCase(Locale.getDefault()) == "txnRef".toLowerCase(
                        Locale.getDefault()
                    )
                ) {
                    approveRef = equlStr[1].toLowerCase(Locale.getDefault())
                    Log.e("UPI", "" + approveRef)
                    payment.ApprovalReferenceNumberBeneficiary = equlStr[1]
                    Log.e("UPI SUCCESS 22222", "" + payment.TransactionReferenceId)
                } else if (equlStr[0].toLowerCase(Locale.getDefault()) == "txnId".toLowerCase(Locale.getDefault())) {
                    payment.TransactionId = equlStr[1]
                } else if (equlStr[0].toLowerCase(Locale.getDefault()) == "responseCode".toLowerCase(
                        Locale.getDefault()
                    )
                ) {
                    payment.ResponseCode = equlStr[1]
                }
            }

        }
        // result.setText("" + append.toString() + "\n" + txt)

    }

    private fun createInStock(): ArrayList<BusinessObject> {

        val verifyCheckOuts: ArrayList<BusinessObject> = ArrayList()
        val cartItems= ShoppingCart.getCartDetail().cartItems
        for (cart in cartItems) {
            val verifyCheckOut  = CartItem(
                DishAvailableEndTime = cart.dishItem.deliveryEndTime,
                DishId = cart.dishItem.dishId,
                Quantity = cart.dishItem.quantity
            )
            verifyCheckOuts.add(verifyCheckOut)

        }

        return verifyCheckOuts

    }


    /* working code. pls call from appropriate method*/
    private fun postCheckOutCOD() {

        val checkOutCOD = createPurchaseOrderByCOD()
        viewModel = BuyerModelGenerator.getModel(
            this,
            BuyerViewModelTypeEnum.CheckOutViewModel,
            checkOutCOD
        ) as CheckOutViewModel
        GlobalScope.launch(Dispatchers.Main) {
            val newDishResponse = viewModel.postPurchaseOrderByCOD.await()
            val bundle = bundleOf("orderId" to newDishResponse.Id)
            findNavController().navigate(  R.id.action_checkOutFragment_to_orderConfirmationFragment, bundle)

        }

    }
    private fun createPurchaseOrderByCOD(): PostPurchaseOrder {
        val checkoutByCODs = createOrderList()
        return PostPurchaseOrder(order = checkoutByCODs, Payment = null)

    }

    private fun createOrderList(): ArrayList<Order> {
        val checkoutByCODs = ArrayList<Order>()
        val cartItems = ShoppingCart.getCartDetail().cartItems
        for (cart in cartItems) {
            val checkoutByCOD = Order(
                BuyerId = Preference.getUserId(),
                ChefId = cart.dishItem.chefId,
                SellerUserId = cart.dishItem.sellerId,
                DeliveryStartTime = cart.dishItem.deliveryStartTime,
                DeliveryEndTime = cart.dishItem.deliveryEndTime,
                DeliveryCharge = cart.dishItem.deliveryCharge,
                DishId = cart.dishItem.dishId,
                PackingCharge = cart.dishItem.deliveryCharge,
                PackingOptions = cart.dishItem.packingOptions,
                DeliveryOptions = cart.dishItem.deliveryOptions,
                Discount = 0,
                ItemTotal = cart.dishItem.unitPrice,
                OrderQuantity = cart.dishItem.quantity,
                TotalDeliveryCharge = cart.dishItem.deliveryCharge,/* cart.dishItem.quantity,*/
                TotalPackingCharge = cart.dishItem.quantity * cart.dishItem.packingCharge,
                OrderTotal = (cart.dishItem.quantity * cart.dishItem.unitPrice) + cart.dishItem.deliveryCharge + (cart.dishItem.quantity * cart.dishItem.packingCharge),
                PaymentMode = if (tv_payment_cash_checkout.isChecked) "COD" else "UPI",
                ServiceCharge = getServixceCharges((cart.dishItem.quantity * cart.dishItem.unitPrice) + cart.dishItem.deliveryCharge + (cart.dishItem.quantity * cart.dishItem.packingCharge)),
                RequestStatus = null,
                RejectNote = ""
            )
            payment.BuyerId = Preference.getUserId()
            payment.ChefId = cart.dishItem.chefId
            payment.SellerId =cart.dishItem.sellerId
            checkoutByCODs.add(checkoutByCOD)
        }
        return checkoutByCODs
    }

    private fun getServixceCharges(cartTotal: Int): Int {
        val serviceCharge: Int

        val slabRange = 4

        val result = (cartTotal - CONSTANT_HUNDRED).toFloat()/ CONSTANT_FIFTY.toFloat()
        val roundedUp = ceil(result.toDouble()) .toInt()
        serviceCharge = roundedUp * slabRange + 3

        // Return
        // Return
        return serviceCharge
    }


    /* working code. pls call from appropriate method*/
    private fun postCheckOutUPI() {
        if(Utility.isOnline()) {
            val checkOutUPI = createCheckoutByUPI()
            /* val gson = Gson()
        val s= gson.toJson(checkOutUPI)*/
            val viewModel1 = BuyerModelGenerator.getModel(
                this,
                BuyerViewModelTypeEnum.CheckOutViewModel,
                checkOutUPI
            ) as CheckOutViewModel

            GlobalScope.launch(Dispatchers.Main) {
                val newDishResponse = viewModel1.postPurchaseOrderByUPI.await()
                val bundle = bundleOf("orderId" to newDishResponse.Id)
                findNavController().navigate(
                    R.id.action_checkOutFragment_to_orderConfirmationFragment,
                    bundle
                )

            }
        }
        else{
            val apiService = RetrofitBuilder(
                ConnectivityInterceptorImpl(),
                BuyerApiService::class.java
            )
            val buyerDataSource = BuyerDataSourceImpl(apiService)
            val repository = BuyerRepositoryImpl(buyerDataSource)
            launch {
                repository.persistOnlineOrder(createCheckoutByUPIOnline())
            }

        }
    }

    private fun createCheckoutByUPIOnline(): OnlineOrder {
        val checkoutByCODs = createOrderList()
        return OnlineOrder(purchaseOrders = null, upiPaymentDetail = null)
    }


    private fun createCheckoutByUPI(): PostPurchaseOrder {
        val checkoutByCODs = createOrderList()
        return PostPurchaseOrder(order = checkoutByCODs, Payment = payment)

    }

    @SuppressLint("SetTextI18n")
    private fun setControls() {

        /*GlobalScope.launch(Dispatchers.Main) {
            val buyerInfo = viewModel.buyerInfoFromDB.await()
            tv_buyer_name_checkout.text = buyerInfo.FullName + ", "
            tv_buyer_address_with_flat_number_checkout.text = buyerInfo.FlatNumber + ", "

            tv_buyer_phone_number_checkout.text = buyerInfo.MobileNumber
        }*/


    }

    private fun payUsingUPIApp(namesText: String, amountTxt: String, upiTxt: String, notetTxt: String) {

        val uri = Uri.parse("upi://pay").buildUpon().appendQueryParameter("pa", upiTxt)
            .appendQueryParameter("pn", namesText)
            .appendQueryParameter("tn", notetTxt)
            .appendQueryParameter("am", amountTxt)
            .appendQueryParameter("cu", "INR")
            .build()



        payment.CurrencyCode = "INR"
        payment.TransactionAmount = amountTxt.toInt()
        payment.TransactionNote = namesText


        val targets = ArrayList<Intent>()
        val template = Intent(Intent.ACTION_VIEW)
        template.data = uri
        val candidates = activity?.packageManager?.queryIntentActivities(template, 0)
        // filter package here
        for (candidate in candidates!!) {
            val packageName = candidate.activityInfo.packageName
            if (packageName == "net.one97.paytm" || packageName == "com.google.android.apps.nbu.paisa.user" || packageName == "com.phonepe.app" || packageName == "in.org.npci.upiapp"
            ) {
                val target = Intent(Intent.ACTION_VIEW)
                target.data = uri
                target.setPackage(packageName)
                targets.add(target)
            }
        }
        if (targets.isNotEmpty()) {
            val chooser = Intent.createChooser(targets.removeAt(0), "Pay money with")
            chooser.putExtra(Intent.EXTRA_INITIAL_INTENTS, targets.toArray(arrayOf<Parcelable>()))
            startActivityForResult(chooser, UPI_PAYMENT)
        }
    }

}
