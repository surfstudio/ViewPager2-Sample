package com.liarstudio.vp2_sample

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.liarstudio.vp2_sample.callback.OnPageStateChangeCallback
import com.liarstudio.vp2_sample.controller.PositionController
import com.liarstudio.vp2_sample.controller.LoaderItemController
import kotlinx.android.synthetic.main.activity_main.*
import ru.surfstudio.android.easyadapter.EasyAdapter
import ru.surfstudio.android.easyadapter.ItemList

/**
 * Main project Activity
 */
class MainActivity : AppCompatActivity() {

    private val positionController = PositionController()
    private val loadingController = LoaderItemController()

    private val adapter = EasyAdapter()
        .apply { setFirstInvisibleItemEnabled(false) }

    private val items = mutableListOf("0", "1", "2")
    private var isPageLoading = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        main_pager.adapter = adapter
        renderItems()

        main_pager.registerOnPageChangeCallback(OnPageStateChangeCallback { position, state ->
            val isIdle = state == RecyclerView.SCROLL_STATE_IDLE
            val isLastPage = position == items.lastIndex

            if (isIdle && isLastPage && !isPageLoading) {
                isPageLoading = true
                renderItems()
                main_pager.postDelayed(::handleNewItems, 2000L)
            }
        })
    }

    private fun renderItems() {
        val itemList = ItemList.create()
            .addAll(items, positionController)
            .addIf(isPageLoading, loadingController)
        adapter.setItems(itemList)
    }

    private fun handleNewItems() {
        isPageLoading = false
        repeat(5) { items.add(items.size.toString()) }
        renderItems()
    }
}
