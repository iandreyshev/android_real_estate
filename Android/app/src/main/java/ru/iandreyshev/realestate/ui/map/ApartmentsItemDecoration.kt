package ru.iandreyshev.realestate.ui.map

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class ApartmentsItemDecoration(
    private val vertical: Int,
    private val margin: Int,
    private val gutter: Int
) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        super.getItemOffsets(outRect, view, parent, state)

        val position = parent.getChildAdapterPosition(view)
        val itemCount = parent.adapter?.itemCount ?: return

        if (position == RecyclerView.NO_POSITION) {
            return
        }

        when (position) {
            0 -> {
                outRect.left = margin
                outRect.right = when (itemCount) {
                    0 -> margin
                    else -> gutter
                }
            }
            itemCount - 1 -> {
                outRect.right = margin
            }
            else -> {
                outRect.right = gutter
            }
        }

        outRect.top = vertical
        outRect.bottom = vertical
    }

}