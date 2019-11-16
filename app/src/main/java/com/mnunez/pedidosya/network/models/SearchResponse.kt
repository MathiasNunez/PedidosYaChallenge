package com.mnunez.pedidosya.network.models

import com.google.gson.annotations.SerializedName


data class SearchResponse(
    @SerializedName("count") var count: Double?,
    @SerializedName("data") var `data`: ArrayList<Restaurant>?,
    @SerializedName("info") var info: Info?,
    @SerializedName("max") var max: Int?,
    @SerializedName("offset") var offset: Int?,
    @SerializedName("sort") var sort: String?,
    @SerializedName("total") var total: Int?
)

data class Restaurant(
    @SerializedName("acceptsPreOrder") var acceptsPreOrder: Boolean?,
    @SerializedName("address") var address: String?,
    @SerializedName("affectedByPorygonEvents") var affectedByPorygonEvents: Boolean?,
    @SerializedName("allCategories") var allCategories: String?,
    @SerializedName("area") var area: String?,
    @SerializedName("businessCategoryId") var businessCategoryId: Double?,
    @SerializedName("businessType") var businessType: String?,
    @SerializedName("categories") var categories: List<Category?>?,
    @SerializedName("channels") var channels: List<Double?>?,
    @SerializedName("cityName") var cityName: String?,
    @SerializedName("coordinates") var coordinates: String?,
    @SerializedName("delivers") var delivers: Boolean?,
    @SerializedName("deliveryAreas") var deliveryAreas: String?,
    @SerializedName("deliveryTime") var deliveryTime: String?,
    @SerializedName("deliveryTimeId") var deliveryTimeId: Double?,
    @SerializedName("deliveryTimeMaxMinutes") var deliveryTimeMaxMinutes: String?,
    @SerializedName("deliveryTimeMinMinutes") var deliveryTimeMinMinutes: String?,
    @SerializedName("deliveryTimeOrder") var deliveryTimeOrder: Double?,
    @SerializedName("deliveryType") var deliveryType: String?,
    @SerializedName("deliveryZoneId") var deliveryZoneId: Double?,
    @SerializedName("discount") var discount: Double?,
    @SerializedName("distance") var distance: Double?,
    @SerializedName("doorNumber") var doorNumber: String?,
    @SerializedName("favoriteByOrders") var favoriteByOrders: Boolean?,
    @SerializedName("favoriteByUser") var favoriteByUser: Boolean?,
    @SerializedName("favoritesCount") var favoritesCount: Double?,
    @SerializedName("food") var food: Double?,
    @SerializedName("generalScore") var generalScore: Double?,
    @SerializedName("hasOnlinePaymentMethods") var hasOnlinePaymentMethods: Boolean?,
    @SerializedName("hasZone") var hasZone: Boolean?,
    @SerializedName("headerImage") var headerImage: String?,
    @SerializedName("homeVip") var homeVip: Boolean?,
    @SerializedName("id") var id: Double?,
    @SerializedName("index") var index: Double?,
    @SerializedName("isGoldVip") var isGoldVip: Boolean?,
    @SerializedName("isNew") var isNew: Boolean?,
    @SerializedName("link") var link: String?,
    @SerializedName("logo") var logo: String?,
    @SerializedName("mandatoryPaymentAmount") var mandatoryPaymentAmount: Boolean?,
    @SerializedName("maxShippingAmount") var maxShippingAmount: Double?,
    @SerializedName("minDeliveryAmount") var minDeliveryAmount: Double?,
    @SerializedName("name") var name: String?,
    @SerializedName("nextHour") var nextHour: String?,
    @SerializedName("nextHourClose") var nextHourClose: String?,
    @SerializedName("noIndex") var noIndex: Boolean?,
    @SerializedName("opened") var opened: Double?,
    @SerializedName("paymentMethods") var paymentMethods: String?,
    @SerializedName("paymentMethodsList") var paymentMethodsList: List<PaymentMethods?>?,
    @SerializedName("rating") var rating: String?,
    @SerializedName("ratingScore") var ratingScore: String?,
    @SerializedName("restaurantRegisteredDate") var restaurantRegisteredDate: String?,
    @SerializedName("restaurantTypeId") var restaurantTypeId: Double?,
    @SerializedName("service") var service: Double?,
    @SerializedName("shippingAmount") var shippingAmount: Double?,
    @SerializedName("shippingAmountIsPercentage") var shippingAmountIsPercentage: Boolean?,
    @SerializedName("shrinkingTags") var shrinkingTags: String?,
    @SerializedName("sortingCategory") var sortingCategory: Double?,
    @SerializedName("sortingConfirmationTime") var sortingConfirmationTime: Double?,
    @SerializedName("sortingDistance") var sortingDistance: Double?,
    @SerializedName("sortingGroupOrderCount") var sortingGroupOrderCount: Double?,
    @SerializedName("sortingLogistics") var sortingLogistics: Double?,
    @SerializedName("sortingNew") var sortingNew: Double?,
    @SerializedName("sortingOnlinePayment") var sortingOnlinePayment: Double?,
    @SerializedName("sortingOrderCount") var sortingOrderCount: Double?,
    @SerializedName("sortingRejectionRate") var sortingRejectionRate: Double?,
    @SerializedName("sortingReviews") var sortingReviews: Double?,
    @SerializedName("sortingTalent") var sortingTalent: Double?,
    @SerializedName("sortingVip") var sortingVip: Double?,
    @SerializedName("speed") var speed: Double?,
    @SerializedName("stateId") var stateId: Double?,
    @SerializedName("topCategories") var topCategories: String?,
    @SerializedName("validReviewsCount") var validReviewsCount: Double?,
    @SerializedName("variableShippingFee") var variableShippingFee: Boolean?,
    @SerializedName("weighing") var weighing: Double?,
    @SerializedName("withLogistics") var withLogistics: Boolean?
)

data class Category(
    @SerializedName("id") var id: Double?,
    @SerializedName("image") var image: String?,
    @SerializedName("manuallySorted") var manuallySorted: Boolean?,
    @SerializedName("name") var name: String?,
    @SerializedName("percentage") var percentage: Double?,
    @SerializedName("quantity") var quantity: Double?,
    @SerializedName("sortingIndex") var sortingIndex: Double?,
    @SerializedName("state") var state: Double?,
    @SerializedName("visible") var visible: Boolean?
)

data class PaymentMethods(
    @SerializedName("descriptionES") var descriptionES: String?,
    @SerializedName("descriptionPT") var descriptionPT: String?,
    @SerializedName("id") var id: String?,
    @SerializedName("online") var online: Boolean?
)

data class Info(
    @SerializedName("advertisingAreaId") var advertisingAreaId: String?,
    @SerializedName("advertisingAreaType") var advertisingAreaType: Double?,
    @SerializedName("areaId") var areaId: Double?,
    @SerializedName("flags") var flags: List<Flag?>?
)

data class Flag(
    @SerializedName("name") var name: String?,
    @SerializedName("value") var value: String?
)