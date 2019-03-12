package com.liarstudio.vp2_sample

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
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

        main_pager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                val isLastPage = position == items.lastIndex

                if (isLastPage && !isPageLoading) {
                    isPageLoading = true
                    // this method is placed in the end of message queue
                    // because we can't update recyclerView's contents during scroll
                    main_pager.post { renderItems() }
                    main_pager.postDelayed(::handleNewItems, 2000L)
                }
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
