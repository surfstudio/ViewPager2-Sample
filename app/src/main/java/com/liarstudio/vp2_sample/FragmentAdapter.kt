package com.liarstudio.vp2_sample

import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.viewpager2.adapter.FragmentStateAdapter

/**
 * Adapter for fragments
 */
class FragmentAdapter(fm: FragmentManager) : FragmentStateAdapter(fm) {

    var items: List<Int>
        set(value) {
            mutableItems = value.toMutableList()
            notifyDataSetChanged()
        }
        get() = mutableItems

    private var mutableItems = mutableListOf<Int>()

    /**
     * @see FragmentStateAdapter.getItem
     */
    override fun getItem(position: Int): Fragment {
        return PositionFragment::class.java.newInstance()
                .apply { arguments = bundleOf(ARG_POSITION to items[position]) }
    }

    /**
     * Method override to support notify* methods
     *
     * @see FragmentStateAdapter.getItemId
     */
    override fun getItemId(position: Int): Long {
        return items[position].toLong()
    }

    /**
     * Method override to support notify* methods
     *
     * @see FragmentStateAdapter.containsItem
     */
    override fun containsItem(itemId: Long): Boolean {
        return items.contains(itemId.toInt())
    }

    /**
     * @see FragmentStateAdapter.getItemCount
     */
    override fun getItemCount(): Int = items.size

    /**
     * Add an item which equals to list size after certain position in list
     *
     * @param position position of item to add
     */
    fun addAfter(position: Int) {
        val size = mutableItems.size
        if (size == 0 || position == size) mutableItems.add(size) else mutableItems.add(position + 1, size)
        notifyItemInserted(position)
    }

    /**
     * Delete an item at certain position in list
     *
     * @param position position of item to remove
     */
    fun deleteAt(position: Int) {
        mutableItems.removeAt(position)
        notifyItemRemoved(position)
    }
}