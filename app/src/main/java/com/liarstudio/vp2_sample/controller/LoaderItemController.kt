package com.liarstudio.vp2_sample.controller

import android.view.ViewGroup
import com.liarstudio.vp2_sample.R
import ru.surfstudio.android.easyadapter.controller.NoDataItemController
import ru.surfstudio.android.easyadapter.holder.BaseViewHolder

/**
 * Controller for stub element to display progress bar during the loading of new page
 */
class LoaderItemController : NoDataItemController<LoaderItemController.Holder>() {

    override fun createViewHolder(parent: ViewGroup) = Holder(parent)

    inner class Holder(parent: ViewGroup) : BaseViewHolder(parent, R.layout.layout_loader)
}