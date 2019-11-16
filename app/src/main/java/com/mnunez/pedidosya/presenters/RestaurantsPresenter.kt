package com.mnunez.pedidosya.presenters

import com.mnunez.pedidosya.activities.RestaurantsActivity
import com.mnunez.pedidosya.base.PYBasePresenter
import com.mnunez.pedidosya.network.ApiManager

class RestaurantsPresenter : PYBasePresenter<RestaurantsActivity>() {

    private var location: String = ""
    private var offset: Int = 0

    fun searchRestaurants(_location: String) {
        location = _location
        val disposable = ApiManager.getInstance().searchRestaurants(
            location = location,
            offset = offset
        ).subscribe({
            view?.stopLoading()
            view?.onSearchResult(it)
        }, {
            handleApiError(it, retryAction = { searchRestaurants(location) })
        })

        addDisposableToComposition(disposable)

    }

    fun loadMoreItems() {
        view?.showPagingLoading()
        offset += 20
        val disposable = ApiManager.getInstance().searchRestaurants(location = location, offset = offset).subscribe({
            view?.onLoadMoreItemsResult(it.data ?: arrayListOf(), it.total ?: 0)
            view?.hidePagingLoading()
        }, {
            handleApiError(it, retryAction = { loadMoreItems() })
        })

        addDisposableToComposition(disposable)
    }
}