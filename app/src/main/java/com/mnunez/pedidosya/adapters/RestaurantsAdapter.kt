package com.mnunez.pedidosya.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mnunez.core.extensions.hide
import com.mnunez.core.extensions.roundDecimal
import com.mnunez.core.extensions.show
import com.mnunez.pedidosya.BuildConfig
import com.mnunez.pedidosya.R
import com.mnunez.pedidosya.extensions.loadImage
import com.mnunez.pedidosya.network.models.Restaurant
import kotlinx.android.synthetic.main.layout_search_result_item.view.*

class RestaurantsAdapter(
    private var items: ArrayList<Restaurant> = arrayListOf(),
    private val listener: RestaurantsListener?
) :
    RecyclerView.Adapter<RestaurantsAdapter.RestaurantsHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RestaurantsHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.layout_search_result_item, parent, false)
        return RestaurantsHolder(view as ViewGroup)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: RestaurantsHolder, position: Int) =
        holder.bind(items[position])

    fun addItems(_items: ArrayList<Restaurant>) {
        items.addAll(_items)
        notifyDataSetChanged()
    }

    inner class RestaurantsHolder(itemView: ViewGroup) : RecyclerView.ViewHolder(itemView) {

        fun bind(item: Restaurant) {
            itemView.item_name.text = item.name
            itemView.item_rating.text = item.generalScore?.roundDecimal().toString()
            itemView.item_thumb.loadImage(BuildConfig.IMAGE_BASE_URL + item.logo)
            itemView.item_payment.text = item.paymentMethods?.replace(",", ", ")
            var shipment = item.deliveryTime
            item.shippingAmount?.let {
                shipment += if (it > 0) {
                    " " + itemView.context.getString(R.string.restaurants_shipment, it.toInt())
                } else {
                    " " + itemView.context.getString(R.string.restaurants_shipment_free)
                }
            }
            itemView.item_delivery_time.text = shipment
            item.discount?.let {
                if (it > 0) {
                    itemView.item_discount.show()
                    itemView.item_discount.text =
                        itemView.context.getString(R.string.restaurants_discount, it.toInt())
                } else {
                    itemView.item_discount.hide()
                }
            }
        }
    }

    interface RestaurantsListener {
        fun onItemSelected(item: Restaurant)
    }
}

