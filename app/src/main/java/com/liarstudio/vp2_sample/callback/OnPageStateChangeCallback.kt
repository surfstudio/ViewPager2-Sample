package com.liarstudio.vp2_sample.callback

import androidx.viewpager2.widget.ViewPager2

/**
 * Callback that reacts on the state changes of last page, selected in [ViewPager2]
 */
class OnPageStateChangeCallback(
    private val onPageStateChanged: (position: Int, state: Int) -> Unit
) : ViewPager2.OnPageChangeCallback() {

    private var lastPagePosition: Int = 0

    override fun onPageScrollStateChanged(state: Int) {
        onPageStateChanged(lastPagePosition, state)
    }

    override fun onPageSelected(position: Int) {
        lastPagePosition = position
    }
}
