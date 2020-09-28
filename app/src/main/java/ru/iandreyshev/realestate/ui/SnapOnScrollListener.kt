package ru.iandreyshev.realestate.ui

import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.SnapHelper

class SnapOnScrollListener(
    private val snapHelper: SnapHelper,
    private val onSnapListener: (position: Int) -> Unit
) : RecyclerView.OnScrollListener() {

    private var mPrevSnapPosition = RecyclerView.NO_POSITION

    override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
        if (newState != RecyclerView.SCROLL_STATE_IDLE) {
            return
        }

        val snapPosition = snapHelper.getSnapPosition(recyclerView)

        if (this.mPrevSnapPosition != snapPosition) {
            onSnapListener.invoke(snapPosition)
            this.mPrevSnapPosition = snapPosition
        }
    }

    private fun SnapHelper.getSnapPosition(recyclerView: RecyclerView): Int {
        val layoutManager = recyclerView.layoutManager ?: return RecyclerView.NO_POSITION
        val snapView = findSnapView(layoutManager) ?: return RecyclerView.NO_POSITION

        return layoutManager.getPosition(snapView)
    }

}
