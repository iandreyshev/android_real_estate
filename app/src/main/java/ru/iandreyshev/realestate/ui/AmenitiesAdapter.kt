package ru.iandreyshev.realestate.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.updateLayoutParams
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_amenity.view.*
import ru.iandreyshev.realestate.R
import ru.iandreyshev.realestate.domain.Amenity

class AmenitiesAdapter : ListAdapter<Amenity, AmenityViewHolder>(AmenityDiffCallback) {

    var screenWidth: Int = 0
    var margin: Int = 0
    var gutter: Int = 0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        LayoutInflater.from(parent.context)
            .inflate(R.layout.item_amenity, parent, false)
            .let { AmenityViewHolder(it) }

    override fun onBindViewHolder(holder: AmenityViewHolder, position: Int) {
        val amenity = getItem(position)

        holder.itemView.apply {
            updateLayoutParams {
                val guttersCount = (VISIBLE_ITEMS_COUNT - 1).coerceAtLeast(0)
                val sumWidth = screenWidth - 2 * margin - guttersCount * gutter
                val itemWidth = sumWidth / VISIBLE_ITEMS_COUNT
                width = itemWidth
                height = itemWidth
            }
            name.text = amenity.getName()
        }
    }

    companion object {
        private const val VISIBLE_ITEMS_COUNT = 4
    }

}

class AmenityViewHolder(view: View) : RecyclerView.ViewHolder(view)

private object AmenityDiffCallback : DiffUtil.ItemCallback<Amenity>() {
    override fun areItemsTheSame(oldItem: Amenity, newItem: Amenity) = true
    override fun areContentsTheSame(oldItem: Amenity, newItem: Amenity) = oldItem == newItem
}

private fun Amenity.getName() = when (this) {
    Amenity.ELEVATOR -> "Elevator"
    Amenity.WIFI -> "WI-FI"
    Amenity.ESSENTIALS -> "Essentials"
    Amenity.WASHER -> "Washer"
    Amenity.HAIR_DRYER -> "Hair dryer"
    Amenity.HEATING -> "Heating"
    Amenity.FREE_PARKING -> "Free parking"
    Amenity.TV -> "TV"
    Amenity.IRON -> "Iron"
}
