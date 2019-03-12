package com.liarstudio.vp2_sample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.viewpager2.widget.ViewPager2
import kotlinx.android.synthetic.main.activity_main.*

/**
 * Main activity for a project
 */
class MainActivity : AppCompatActivity() {

    private lateinit var adapter: FragmentAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        adapter = FragmentAdapter(supportFragmentManager)
                .apply { items = listOf(0, 1, 2) }

        main_pager.adapter = adapter

        main_pager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                Log.d("OnPageChangeCallback", "Page selected: $position")
            }
        })

        add_btn.setOnClickListener {
            addAfterCurrent()
            showItemsCount()
        }

        delete_btn.setOnClickListener {
            deleteCurrent()
            showItemsCount()
        }

        showItemsCount()

    }

    private fun addAfterCurrent() {
        val position = main_pager.currentItem
        adapter.addAfter(position)
    }

    private fun deleteCurrent() {
        if (adapter.items.isNotEmpty()) {
            val position = main_pager.currentItem
            adapter.deleteAt(position)
            if (position > 0) {
                //set current item manually because notify callback doesn't trigger it
                main_pager.setCurrentItem(if (position == adapter.items.size) position - 1 else position, false)
            }
        }
    }

    private fun showItemsCount() {
        val itemsCount = "Items count: ${adapter.items.size}"
        counter_tv.text = itemsCount
    }
}
