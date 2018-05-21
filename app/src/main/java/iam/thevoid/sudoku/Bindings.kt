package iam.thevoid.sudoku

import android.databinding.BindingAdapter
import android.databinding.BindingConversion
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.TextView
import iam.thevoid.sudoku.widget.CellGameWrapper
import io.reactivex.functions.Action
import me.tatarka.bindingcollectionadapter2.BindingCollectionAdapter

/**
 * Created by iam on 16/09/2017.
 */

/**
 * VIEW BINDING
 */

@BindingAdapter("click")
fun setOnClickListener(view: View, listener: View.OnClickListener) {
    view.setOnClickListener(listener)
}

@BindingAdapter("clickAction")
fun setOnClickListener(view: View, listener: Action) {
    view.setOnClickListener(View.OnClickListener { listener.run() })
}

@BindingAdapter("visibile", "type", requireAll = false)
fun setOnClickListener(view: View, boolean: Boolean, type: Int) {
    if (!boolean && type != View.VISIBLE) {
        view.visibility = type
    } else {
        view.visibility = if (boolean) View.VISIBLE else View.GONE
    }
}

/**
 * TEXT VIEW BINDING
 */
@BindingAdapter("intText")
fun setIntText(view: TextView, text: Int) {
    view.text = text.toString()
}

@BindingAdapter("hour_time")
fun setIntText(view: TextView, time: Long) {
    val seclen = time / 1000
    val hour = seclen / 3600
    val min = (seclen - hour * 3600) / 60
    val sec = (seclen - hour * 3600 - min * 60)
    view.text = String.format("%02d:%02d:%02d", hour, min, sec)
}


/**
 * RECYCLER VIEW BINDING
 */
@BindingAdapter("decoration")
fun setIntText(view: RecyclerView, decoration: RecyclerView.ItemDecoration) {
    view.addItemDecoration(decoration)
}

@BindingAdapter("itemClick")
fun <T> setItemClickListener(view: RecyclerView, listener: ItemClickSupport.OnItemClick<T>) {
    ItemClickSupport.addTo(view).setOnlyClickable(true).setOnItemClickListener(itemClick(listener))
}

@BindingConversion
fun <T> itemClick(listener: ItemClickSupport.OnItemClick<T>): ItemClickSupport.OnItemClickListener {
    return object : ItemClickSupport.OnItemClickListener {
        override fun onItemClicked(recyclerView: RecyclerView, itemView: View, position: Int) {
            if (position >= 0 && position < recyclerView.adapter.itemCount) {
                val adapterItem = (recyclerView.adapter as BindingCollectionAdapter<*>).getAdapterItem(position) as T
                listener.onItemClicked(recyclerView, itemView, position, adapterItem)
            }
        }
    }
}


class ItemClickSupport private constructor(private val mRecyclerView: RecyclerView) {
    private var mOnlyClickable: Boolean = false
    private var mOnItemClickListener: OnItemClickListener? = null
    private var mOnItemLongClickListener: OnItemLongClickListener? = null
    private val mOnClickListener = View.OnClickListener { v ->
        if (mOnItemClickListener != null) {
            val holder = mRecyclerView.getChildViewHolder(v)
            mOnItemClickListener!!.onItemClicked(mRecyclerView, v, holder.adapterPosition)
        }
    }
    private val mOnLongClickListener = View.OnLongClickListener { v ->
        if (mOnItemLongClickListener != null) {
            val holder = mRecyclerView.getChildViewHolder(v)
            return@OnLongClickListener mOnItemLongClickListener!!.onItemLongClicked(mRecyclerView, v, holder.adapterPosition)
        }
        false
    }
    private val mAttachListener = object : RecyclerView.OnChildAttachStateChangeListener {
        override fun onChildViewAttachedToWindow(view: View) {
            if (mOnItemClickListener != null && (!mOnlyClickable || view.isClickable)) {
                view.setOnClickListener(mOnClickListener)
            } else {
                view.setOnClickListener(null)
            }
            if (mOnItemLongClickListener != null && (!mOnlyClickable || view.isClickable)) {
                view.setOnLongClickListener(mOnLongClickListener)
            } else {
                view.setOnLongClickListener(null)
            }
        }

        override fun onChildViewDetachedFromWindow(view: View) {

        }
    }

    init {
        mRecyclerView.setTag(R.id.item_click_support, this)
        mRecyclerView.addOnChildAttachStateChangeListener(mAttachListener)
    }

    fun setOnlyClickable(onlyClickable: Boolean): ItemClickSupport {
        if (mOnlyClickable != onlyClickable) {
            this.mOnlyClickable = onlyClickable
            refreshAllChildren()
        }
        return this
    }

    fun setOnItemClickListener(listener: OnItemClickListener?): ItemClickSupport {
        if (mOnItemClickListener == null && listener != null) {
            mOnItemClickListener = listener
            refreshAllChildren()
        } else if (mOnItemClickListener != null && listener == null) {
            mOnItemClickListener = null
            refreshAllChildren()
        } else {
            mOnItemClickListener = listener
        }
        return this
    }

    fun setOnItemLongClickListener(listener: OnItemLongClickListener?): ItemClickSupport {
        if (mOnItemLongClickListener == null && listener != null) {
            mOnItemLongClickListener = listener
            refreshAllChildren()
        } else if (mOnItemLongClickListener != null && listener == null) {
            mOnItemLongClickListener = null
            refreshAllChildren()
        } else {
            mOnItemLongClickListener = listener
        }
        return this
    }

    private fun refreshAllChildren() {
        (0 until mRecyclerView.childCount)
                .map { mRecyclerView.getChildAt(it) }
                .forEach { mAttachListener.onChildViewAttachedToWindow(it) }
    }

    private fun detach(view: RecyclerView) {
        view.removeOnChildAttachStateChangeListener(mAttachListener)
        view.setTag(R.id.item_click_support, null)
    }

    interface OnItemClickListener {

        fun onItemClicked(recyclerView: RecyclerView, itemView: View, position: Int)
    }

    interface OnItemClick<T> {
        fun onItemClicked(recyclerView: RecyclerView, itemView: View, position: Int, item: T)
    }

    interface OnItemLongClickListener {
        fun onItemLongClicked(recyclerView: RecyclerView, itemView: View, position: Int): Boolean
    }

    interface OnItemLongClick<T> {
        fun onItemLongClicked(recyclerView: RecyclerView, itemView: View, position: Int, item: T): Boolean
    }

    companion object {

        fun addTo(view: RecyclerView): ItemClickSupport {
            var support: ItemClickSupport? = view.getTag(R.id.item_click_support) as? ItemClickSupport
            if (support == null) {
                support = ItemClickSupport(view)
            }
            return support
        }

        fun removeFrom(view: RecyclerView): ItemClickSupport? {
            val support = view.getTag(R.id.item_click_support) as ItemClickSupport
            support.detach(view)
            return support
        }
    }
}