package ru.iandreyshev.realestate.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.updateLayoutParams
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_apartment.view.*
import ru.iandreyshev.realestate.R
import ru.iandreyshev.realestate.domain.Apartment

class ApartmentsAdapter(
    private val onClickListener: (position: Int) -> Unit
) : ListAdapter<Apartment, ViewHolder>(DiffCallback) {

    var itemWidth = 0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        LayoutInflater.from(parent.context)
            .inflate(R.layout.item_apartment, parent, false)
            .let { view -> ViewHolder(view, onClickListener) }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val apartment = getItem(position)

        holder.itemView.apply {
            updateLayoutParams {
                width = itemWidth
            }
            name.text = apartment.name
            rating.text = "${apartment.rating.average} (${apartment.rating.ratesCount})"
        }
    }

}

class ViewHolder(
    view: View,
    onClickListener: (position: Int) -> Unit
) : RecyclerView.ViewHolder(view) {

    init {
        view.contentView.setOnClickListener { onClickListener(adapterPosition) }
    }

}

private object DiffCallback : DiffUtil.ItemCallback<Apartment>() {

    override fun areItemsTheSame(oldItem: Apartment, newItem: Apartment) = true
    override fun areContentsTheSame(oldItem: Apartment, newItem: Apartment) = oldItem == newItem

}
