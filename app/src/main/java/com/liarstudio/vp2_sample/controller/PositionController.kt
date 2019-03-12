package com.liarstudio.vp2_sample.controller

import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.liarstudio.vp2_sample.R
import ru.surfstudio.android.easyadapter.controller.BindableItemController
import ru.surfstudio.android.easyadapter.holder.BindableViewHolder

/**
 * Controller for String element with position content
 */
class PositionController : BindableItemController<String, PositionController.Holder>() {
    override fun getItemId(value: String) = value.hashCode().toString()

    override fun createViewHolder(parent: ViewGroup) = Holder(parent)

    inner class Holder(parent: ViewGroup) : BindableViewHolder<String>(parent, R.layout.layout_position) {

        override fun bind(value: String) {
            val title = "Element $value"
            (itemView as TextView).text = title
            val bgColorRes = if (adapterPosition % 2 == 0) R.color.colorAccent else R.color.colorPrimary
            itemView.setBackgroundColor(ContextCompat.getColor(itemView.context, bgColorRes))
        }
    }
}