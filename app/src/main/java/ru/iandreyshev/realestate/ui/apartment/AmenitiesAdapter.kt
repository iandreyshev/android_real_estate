package ru.iandreyshev.realestate.ui.apartment

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_amenity.view.*
import ru.iandreyshev.realestate.R
import ru.iandreyshev.realestate.domain.Amenity

class AmenitiesAdapter : ListAdapter<Amenity, AmenityViewHolder>(AmenityDiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        LayoutInflater.from(parent.context)
            .inflate(R.layout.item_amenity, parent, false)
            .let { AmenityViewHolder(it) }

    override fun onBindViewHolder(holder: AmenityViewHolder, position: Int) {
        val amenity = getItem(position)

        holder.itemView.apply {
            icon.setImageResource(amenity.getIcon())
            name.text = amenity.getName()
            clickableArea.setOnClickListener { }
        }
    }

}

class AmenityViewHolder(view: View) : RecyclerView.ViewHolder(view)

private object AmenityDiffCallback : DiffUtil.ItemCallback<Amenity>() {
    override fun areItemsTheSame(oldItem: Amenity, newItem: Amenity) = true
    override fun areContentsTheSame(oldItem: Amenity, newItem: Amenity) = oldItem == newItem
}

private fun Amenity.getIcon() = when (this) {
    Amenity.WIFI -> R.drawable.ic_amenity_wifi
    Amenity.GYM -> R.drawable.ic_amenity_gym
    Amenity.FOOD -> R.drawable.ic_amenity_food
    Amenity.HEATER -> R.drawable.ic_amenity_heater
    else -> R.drawable.ic_amenity_food
}


private fun Amenity.getName() = when (this) {
    Amenity.WIFI -> "WI-FI"
    Amenity.GYM -> "Gym"
    Amenity.FOOD -> "Food"
    Amenity.HEATER -> "Heater"
    Amenity.HAIR_DRYER -> "Hair dryer"
    Amenity.HEATING -> "Heating"
    Amenity.FREE_PARKING -> "Free parking"
    Amenity.TV -> "TV"
    Amenity.IRON -> "Iron"
}