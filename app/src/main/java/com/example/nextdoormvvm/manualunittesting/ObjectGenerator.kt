package com.example.nextdoormvvm.manualunittesting

import com.example.nextdoormvvm.buyer.network.post.*
import com.example.nextdoormvvm.buyer.network.response.CartItem
import com.example.nextdoormvvm.common.network.post.PostDishViewed
import com.example.nextdoormvvm.internal.BusinessObject
import com.example.nextdoormvvm.seller.network.post.ActiveDish
import com.example.nextdoormvvm.seller.network.post.Dish
import com.example.nextdoormvvm.user.network.post.*
import com.example.nextdoormvvm.user.network.put.Address
import com.example.nextdoormvvm.user.network.upsert.ChefBio
import com.example.nextdoormvvm.user.network.upsert.ChefDetail
import com.example.nextdoormvvm.user.network.upsert.DocumentProof

class ObjectGenerator {

    companion object {

        fun generateNewApartmentPost(): PostNewApartment {
            val newApartmentPost = PostNewApartment(
                "Ajmera Nucleus",
                "Next to Mahindra Tech Park, 424c, Shanthi Pura",
                "Electronic City Phase II",
                "560100",
                "Soumen Roy",
                "9844955348" )

            return newApartmentPost
        }


        fun generateUpdateAddressObjectForBuyer(): Address {
            val address = Address(
                2,
                10137,
                1,
                "A5-000",
                null)
            return address
        }

        fun generateUpdateAddressObjectForSeller(): Address {
            val address = Address(
                2,
                91,
                2,
                "ZZ-206",
                "http;/new...///")

            return address
        }



        fun generatePostChefFollowerObject(): ChefFollower {
            return ChefFollower(
                1,
                89,
                51)
        }

        fun generateUpdateChefFollowerObject(): ChefFollower {
            return ChefFollower(
                1,
                89,
                51)
        }

        fun generatePostTestimonialObject(): Testimonial {
            return Testimonial(
                51,
                86,
                89,
                "Just Test note")
        }


        fun generateUpsertChefDetailObject(): ChefDetail {

           val chefBio = ChefBio(
               10078, // PAss -1 for New Chef
               90,
               "NEW...I am Manjunath, Good Chef",
               6
           ) // 6 means Veg & NonVeg

            val documentProof =
                DocumentProof(
                    1,
                    "J5-206",
                    "Manjunath Reddy",
                    "https://5.imimg.com/data5/WX/LM/MY-53146131/pancard-500x500.jpg",
                    "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSGFnKDY8hw1K7vJw33pxMKXLP_d5VaLkGSmNanYMrtz0RtYz8"
                )

            return ChefDetail(
                chefBio,
                documentProof
            )
        }


        fun generateNewBuyerPost(): PostBuyer {

            val newBuyerPost = PostBuyer(
                1,
                1,
                "Rakhi Maity",
                "H3-407",
                "919838376553",
                true,
                "rakhi.maity.ckt.com",
                false,
                "Female",
                "https://www.youtube.com/results?search_query=sqldataadapter+selectcommand+in+c%23")

            return newBuyerPost
        }


        fun generateDishViewedPost(): ArrayList<BusinessObject> {
            //val dishViewedList = ArrayList<DishViewedPost>()
            val dishViewedList = ArrayList<BusinessObject>()
            val dishViewedPost = PostDishViewed(
                89,
                51,
                241,
                "2020-01-30T15:45:57",
                "2020-01-30T15:47:52")

            dishViewedList.add(dishViewedPost)

            return dishViewedList
        }


        fun generateSharedDishDetailPost(): PostSharedDishDetail {

            val sharedDishDetailPost = PostSharedDishDetail(
                89,
                241,
                "com.whatsapp/.ContactPicker"
                )
            return sharedDishDetailPost
        }


        fun generateRatingAndReviewPopupPost(): ArrayList<BusinessObject> {
            val ratingAndReviewList = ArrayList<BusinessObject>()
            val ratingAndReviewPopupPost = PostRatingAndReviewPopup(
                20161,
                51,
                249,
                85,
                5,
                "My Note 14",
                5)

            ratingAndReviewList.add(ratingAndReviewPopupPost)
            return ratingAndReviewList
        }


        fun generateOnlineOrder(): OnlineOrder {

            val purchaseOrders = ArrayList<PurchaseOrder>()

            val purchaseOrder = PurchaseOrder(
                BuyerId = 85,
                ChefId = 51,
                DeliveryCharge = 0,
                DeliveryEndTime = "2020-02-05T20:35:00",
                DeliveryOptions = 2,
                DeliveryStartTime = "2020-02-05T20:05:00",
                Discount = 0,
                DishId = 10282,
                ItemTotal = 130,
                OrderQuantity = 1,
                OrderTotal = 130,
                PackingCharge = 0,
                PackingOptions = 2,
                PaymentMode = "UPI",
                RejectNote = "",
                SellerUserId = 86,
                ServiceCharge = 7,
                TotalDeliveryCharge = 0,
                TotalPackingCharge = 0,
                RequestStatus = null
            )
            val upiPaymentDetail = UpiPaymentDetail(
                ApprovalReferenceNumberBeneficiary = "",
                BuyerId = 85,
                ChefId = 51,
                CurrencyCode = "INR",
                ResponseCode = "UP00",
                SellerId = 86,
                TransactionAmount = 1,
                TransactionId = "SBI9b7298fac3844c5f8775a4b10d581fc3",
                TransactionNote = "Sarith",
                TransactionReferenceId = "",
                TransactionStatus = "success"
            )
            purchaseOrders.add(purchaseOrder)

            return  OnlineOrder(purchaseOrders, upiPaymentDetail)
        }



        fun generateNewDishPost() = Dish(
            CategoryId = 1,
            ChefId = 51,
            DishDescription = "chilli chiken with semi gravi",
            DishId = -1,
            DishImageUrl = "cropped3238043437182573339.jpg",
            DishName = "chicken chilli",
            DishType = "Non-veg",
            EarningAfterServiceCharges = 117,
            IsSignatureDish = false,
            MealType = "Lunch",
            ServingsPerPlate = 1,
            SubCategoryId = 1,
            UserId = 86,
            UnitPrice = 120
        )

        /*{"ChefId":51,"DeliveryCharge":12,"DeliveryOptions":6,"DishAvailableEndTime":"",
        "DishAvailableStartTime":"","DishId":10282,"PackingCharge":14,"PackingOptions":6,
        "QuantityPreparing":20,"TimeSlotInterval":30,"UserId":86}*/

        fun generateActiveDishPost() = ActiveDish(
            DishId = 10282,
            ChefId = 51,
            UserId = 86,
            DishAvailableEndTime = "2020-02-05T23:38:33",
            DishAvailableStartTime = "2020-02-05T15:41:33",
            DeliveryOptions = 6,
            DeliveryCharge = 12,
            PackingCharge = 14,
            PackingOptions = 6,
            TimeSlotInterval = 30,
            QuantityPreparing = 20

        )

        fun generateCheckoutByUPI(): PostPurchaseOrder {
            val checkoutByCODs = ArrayList<Order>()

            val checkoutByCOD = Order(
                BuyerId = 85,
                ChefId = 51,
                DeliveryCharge = 0,
                DeliveryEndTime = "2020-03-17T20:30:30",
                DeliveryOptions = 2,
                DeliveryStartTime = "2020-03-17T20:15:10",
                Discount = 0,
                DishId = 10282,
                ItemTotal = 99,
                OrderQuantity = 1,
                OrderTotal = 99,
                PackingCharge = 0,
                PackingOptions = 2,
                PaymentMode = "UPI",
                RejectNote = null,
                SellerUserId = 86,
                ServiceCharge = 0,
                TotalDeliveryCharge = 0,
                TotalPackingCharge = 0,
                RequestStatus = null
            )
            val payment = UPIPayment(
                ApprovalReferenceNumberBeneficiary = "",
                BuyerId = 85,
                ChefId = 51,
                CurrencyCode = "INR",
                ResponseCode = "UP00",
                SellerId = 86,
                TransactionAmount = 1,
                TransactionId = "SBI9b7298fac3844c5f8775a4b10d581fc3",
                TransactionNote = "Sarith",
                TransactionReferenceId = "",
                TransactionStatus = "success"
            )
            checkoutByCODs.add(checkoutByCOD)
            return  PostPurchaseOrder(order = checkoutByCODs, Payment = payment)
        }


        fun generateCheckoutByCOD(): PostPurchaseOrder {
            val checkoutByCODs = ArrayList<Order>()

            val checkoutByCOD = Order(
                BuyerId = 85,
                ChefId = 51,
                SellerUserId = 86,
                DeliveryStartTime = "2020-02-06T11:25:00",
                DeliveryEndTime = "2020-02-06T11:55:00",
                DeliveryCharge = 0,
                DishId = 249,
                PackingCharge = 0,
                PackingOptions = 2,
                DeliveryOptions = 2,
                Discount = 0,
                ItemTotal = 230,
                OrderQuantity = 1,
                TotalDeliveryCharge = 0,/* cart.dishItem.quantity,*/
                TotalPackingCharge = 0,
                OrderTotal = 230,
                PaymentMode = "COD",
                ServiceCharge = 15,
                RequestStatus = null,
                RejectNote = ""
            )
            checkoutByCODs.add(checkoutByCOD)
            return PostPurchaseOrder(order = checkoutByCODs, Payment = null)

        }

        fun generateCheckoutByRequest(): Order {
            return Order(
                BuyerId = 85,
                ChefId = 51,
                DeliveryCharge = 0,
                DeliveryEndTime = "2020-02-08T16:12:33",
                DeliveryOptions = 0,
                DeliveryStartTime = "2020-02-08T15:57:33",
                Discount = 0,
                DishId = 243,
                ItemTotal = 140,
                OrderQuantity = 1,
                OrderTotal = 140,
                PackingCharge = 0,
                PackingOptions = 0,
                PaymentMode = "COD",
                RejectNote = "",
                RequestStatus = "Acceptance Pending",
                SellerUserId = 86,
                ServiceCharge = -5,
                TotalDeliveryCharge = 0,
                TotalPackingCharge = 0
            )
        }


        fun generateInStock(): ArrayList<BusinessObject> {

            val cartList = ArrayList<BusinessObject>()


            val cartItem = CartItem(
                240,
                1,
                "2020-02-06T19:43:00"
            )

            cartList.add(cartItem)


            return cartList
        }

    }


}