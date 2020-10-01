package ru.iandreyshev.realestate.ui.apartment

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.text.buildSpannedString
import androidx.core.text.color
import androidx.core.text.underline
import androidx.core.view.isInvisible
import androidx.lifecycle.observe
import com.bumptech.glide.Glide
import dev.chrisbanes.insetter.applySystemWindowInsetsToPadding
import kotlinx.android.synthetic.main.activity_apartment.*
import kotlinx.android.synthetic.main.activity_apartment.contentView
import kotlinx.android.synthetic.main.activity_apartment.name
import kotlinx.android.synthetic.main.activity_apartment.photo
import kotlinx.android.synthetic.main.activity_apartment.rating
import kotlinx.android.synthetic.main.activity_apartment.superOwnerStatus
import kotlinx.android.synthetic.main.amenity_list.*
import ru.iandreyshev.realestate.R
import ru.iandreyshev.realestate.domain.Amenity
import ru.iandreyshev.realestate.domain.Apartment
import ru.iandreyshev.realestate.domain.ApartmentId
import ru.iandreyshev.realestate.extension.ApartmentViewModelFactory
import ru.iandreyshev.realestate.extension.initTranslucentBars
import ru.iandreyshev.realestate.extension.uiLazy
import ru.iandreyshev.realestate.presentation.ApartmentViewModel

class ApartmentActivity : AppCompatActivity(R.layout.activity_apartment) {

    private val mViewModel: ApartmentViewModel by viewModels {
        val id = intent?.extras?.get(EXTRA_ID_KEY) as ApartmentId
        ApartmentViewModelFactory(id)
    }
    private val mGlide by uiLazy { Glide.with(this) }
    private val mAmenityAdapter by uiLazy { AmenitiesAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        initTranslucentBars()
        super.onCreate(savedInstanceState)

        initToolbar()
        initContentView()
        initAmenityList()

        likeButton.setOnClickListener { mViewModel.onToggleLike() }

        mViewModel.apartment.observe(this) { render(it) }
    }

    private fun initToolbar() {
        backButton.setOnClickListener { finish() }
    }

    private fun initContentView() {
        contentView.applySystemWindowInsetsToPadding(top = true, bottom = true)
    }

    private fun initAmenityList() {
        amenityList.adapter = mAmenityAdapter
        amenityList.addItemDecoration(
            AmenityItemDecoration(
                top = resources.getDimensionPixelSize(R.dimen.amenity_top),
                bottom = resources.getDimensionPixelSize(R.dimen.amenity_bottom),
                margin = resources.getDimensionPixelSize(R.dimen.amenity_margin),
                gutter = resources.getDimensionPixelSize(R.dimen.grid_step_2)
            )
        )
    }

    private fun render(apartment: Apartment) {
        mGlide.load(apartment.photo)
            .centerCrop()
            .into(photo)

        name.text = apartment.name

        owner.text = apartment.owner.name
        superOwnerStatus.isInvisible = !apartment.owner.isSuper
        mGlide.load(apartment.owner.photo)
            .centerCrop()
            .circleCrop()
            .into(ownerAvatar)

        rating.text = buildSpannedString {
            color(ContextCompat.getColor(this@ApartmentActivity, R.color.black_0)) {
                append("${apartment.rating.average}")
            }
            color(ContextCompat.getColor(this@ApartmentActivity, R.color.gray_2)) {
                append(" (${apartment.rating.ratesCount})")
            }
        }
        location.text = buildSpannedString {
            underline { append(apartment.address.description) }
        }

        likeButton.setImageDrawable(
            ContextCompat.getDrawable(
                this,
                when (apartment.isLiked) {
                    true -> R.drawable.ic_apartment_like_active
                    else -> R.drawable.ic_apartment_like_passive
                }
            )
        )

        mAmenityAdapter.submitList(Amenity.values().toList())

        details.text = apartment.details
    }

    companion object {
        private const val EXTRA_ID_KEY = "extra_id_key"

        fun newIntent(context: Context, apartmentId: ApartmentId) =
            Intent(context, ApartmentActivity::class.java).apply {
                putExtra(EXTRA_ID_KEY, apartmentId)
            }
    }

}
