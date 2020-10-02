package ru.iandreyshev.realestate.ui.map

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.text.bold
import androidx.core.text.buildSpannedString
import androidx.core.text.color
import androidx.core.view.isVisible
import androidx.core.view.updateLayoutParams
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import kotlinx.android.synthetic.main.item_apartment.view.*
import ru.iandreyshev.realestate.R
import ru.iandreyshev.realestate.domain.Apartment
import ru.iandreyshev.realestate.domain.ApartmentId
import ru.iandreyshev.realestate.extension.rubSymbol

class ApartmentsAdapter(
    private val glide: RequestManager,
    private val onClickListener: (position: Int) -> Unit
) : ListAdapter<Apartment, ApartmentViewHolder>(DiffCallback) {

    var itemWidth = 0
    val viewHolders = mutableSetOf<ApartmentViewHolder>()

    override fun onViewAttachedToWindow(holder: ApartmentViewHolder) {
        super.onViewAttachedToWindow(holder)
        viewHolders.add(holder)
    }

    override fun onViewDetachedFromWindow(holder: ApartmentViewHolder) {
        super.onViewDetachedFromWindow(holder)
        viewHolders.remove(holder)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        LayoutInflater.from(parent.context)
            .inflate(R.layout.item_apartment, parent, false)
            .let { view -> ApartmentViewHolder(view, onClickListener) }

    override fun onBindViewHolder(holder: ApartmentViewHolder, position: Int) {
        val apartment = getItem(position)

        holder.id = apartment.id
        holder.itemView.apply {
            updateLayoutParams {
                width = itemWidth
            }
            glide.load(apartment.photo)
                .centerCrop()
                .into(photo)
            name.text = apartment.name
            superOwnerStatus.isVisible = apartment.owner.isSuper
            cost.text = buildSpannedString {
                bold { append("${rubSymbol()} ") }
                append("${apartment.cost} per night")
            }
            rating.text = buildSpannedString {
                color(ContextCompat.getColor(context, R.color.black_0)) {
                    append("${apartment.rating.average}")
                }
                color(ContextCompat.getColor(context, R.color.gray_2)) {
                    append(" (${apartment.rating.ratesCount})")
                }
            }
        }
    }

}

class ApartmentViewHolder(
    view: View,
    onClickListener: (position: Int) -> Unit
) : RecyclerView.ViewHolder(view) {

    init {
        view.contentView.setOnClickListener { onClickListener(adapterPosition) }
    }

    var id: ApartmentId? = null

}

private object DiffCallback : DiffUtil.ItemCallback<Apartment>() {

    override fun areItemsTheSame(oldItem: Apartment, newItem: Apartment) = true
    override fun areContentsTheSame(oldItem: Apartment, newItem: Apartment) = oldItem == newItem

}
