package com.mnunez.pedidosya.activities

import android.Manifest
import android.content.Context
import android.content.Intent
import android.location.Geocoder
import android.location.Location
import android.location.LocationManager
import android.os.Bundle
import android.provider.Settings
import android.view.View
import android.view.animation.AnimationUtils
import androidx.core.widget.NestedScrollView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.mnunez.core.extensions.hide
import com.mnunez.core.extensions.show
import com.mnunez.core.permissions.PermissionsCallback
import com.mnunez.core.utils.AlertUtils
import com.mnunez.pedidosya.R
import com.mnunez.pedidosya.adapters.RestaurantsAdapter
import com.mnunez.pedidosya.base.PYBaseActivity
import com.mnunez.pedidosya.network.models.Restaurant
import com.mnunez.pedidosya.network.models.SearchResponse
import com.mnunez.pedidosya.presenters.RestaurantsPresenter
import kotlinx.android.synthetic.main.activity_restaurants.*
import org.jetbrains.anko.toast
import java.util.*


class RestaurantsActivity : PYBaseActivity<RestaurantsActivity, RestaurantsPresenter>(),
    PermissionsCallback, RestaurantsAdapter.RestaurantsListener {


    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private var location: Location? = null
    private lateinit var restaurantsAdapter: RestaurantsAdapter
    private var isLoadingMoreItems = false
    private var itemsSize: Int = 0
    private var totalResults = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_restaurants)
    }

    override fun onResume() {
        super.onResume()
        val locationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager
        if (!locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            AlertUtils.showTwoButtonsAlert(
                context = this,
                title = getString(R.string.app_name),
                message = getString(R.string.location_off_message),
                onNegativeClicked = {
                    onBackPressed()
                },
                onPositiveClicked = {
                    startActivity(Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS))
                },
                btnPositive = getString(R.string.general_yes),
                btnNegative = getString(R.string.general_no)
            )
        } else {
            search_result_recycler.layoutManager =
                LinearLayoutManager(this, RecyclerView.VERTICAL, false)
            requestPermission(arrayOf(Manifest.permission.ACCESS_COARSE_LOCATION), this)

            parent_scroll_view.setOnScrollChangeListener(NestedScrollView.OnScrollChangeListener { v, _, scrollY, _, _ ->
                if (scrollY == v.getChildAt(0).measuredHeight - v.measuredHeight) {
                    if (!isLoadingMoreItems && itemsSize < totalResults) {
                        isLoadingMoreItems = true
                        mPresenter.loadMoreItems()
                    }
                }
            })
        }
    }

    override fun granted() {
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
        fusedLocationClient.lastLocation.addOnSuccessListener {
            location = it
            val geocoder = Geocoder(this, Locale.getDefault())
            val address =
                geocoder.getFromLocation(location?.latitude!!, location?.longitude!!, 1)?.first()
            address?.let { ad ->
                val currentAddress = ad.thoroughfare + " " + ad.subThoroughfare
                address_text?.text = currentAddress
            }
            mPresenter.searchRestaurants(location?.latitude?.toString() + "," + location?.longitude?.toString())
        }.addOnFailureListener {
            print(it)
        }
    }

    fun onSearchResult(response: SearchResponse) {
        val controller =
            AnimationUtils.loadLayoutAnimation(this, R.anim.layout_animation_fall)
        search_result_recycler?.layoutAnimation = controller

        restaurantsAdapter = RestaurantsAdapter(response.data!!, this)
        search_result_recycler.adapter = restaurantsAdapter
        search_result_recycler?.scheduleLayoutAnimation()

        itemsSize = response.data?.size ?: 0
        totalResults = response.total ?: 0
        total_results_text.text = getString(R.string.restaurants_total_results, totalResults)
        items_displayed_text.text =
            getString(R.string.restaurants_displaying_items, itemsSize, totalResults)
    }

    fun onLoadMoreItemsResult(items: ArrayList<Restaurant>, totalResults: Int?) {
        isLoadingMoreItems = false
        itemsSize += items.size
        items_displayed_text.text =
            getString(R.string.restaurants_displaying_items, itemsSize, totalResults)
        restaurantsAdapter.addItems(items)
    }

    fun showPagingLoading() {
        paging_progress_bar?.show()
    }

    fun hidePagingLoading() {
        paging_progress_bar.hide()
    }

    override fun onItemSelected(item: Restaurant) {
        toast(item.name + " clicked!")
    }

    override fun denied(denied: Int) {
        AlertUtils.showTwoButtonsAlert(
            context = this,
            message = getString(R.string.restaurants_permission_denied),
            btnNegative = getString(R.string.general_no),
            btnPositive = getString(R.string.general_yes),
            title = getString(R.string.app_name),
            onNegativeClicked = {},
            onPositiveClicked = {
                requestPermission(arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), this)
            })
    }

    override fun requestRationale(permission: MutableList<String>) {
        AlertUtils.showOneButtonAlert(
            context = this,
            message = getString(R.string.restaurants_permission_denied_rationale),
            title = getString(R.string.app_name),
            onPositiveClicked = {
                onBackPressed()
            }
        )
    }


    override fun getFullscreenLoadingView(): View? {
        return full_screen_progress_bar
    }

    override fun buildPresenter() {
        mPresenter = RestaurantsPresenter()
    }


}