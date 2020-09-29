package ru.iandreyshev.realestate.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.text.buildSpannedString
import androidx.core.text.color
import androidx.core.text.underline
import androidx.core.view.isVisible
import androidx.lifecycle.observe
import com.bumptech.glide.Glide
import dev.chrisbanes.insetter.applySystemWindowInsetsToPadding
import kotlinx.android.synthetic.main.activity_apartment.*
import ru.iandreyshev.realestate.R
import ru.iandreyshev.realestate.domain.Amenity
import ru.iandreyshev.realestate.domain.Apartment
import ru.iandreyshev.realestate.domain.ApartmentId
import ru.iandreyshev.realestate.extension.ApartmentViewModelFactory
import ru.iandreyshev.realestate.extension.getWidth
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

        mViewModel.apartment.observe(this) { render(it) }
    }

    private fun initToolbar() {
        backButton.setOnClickListener { finish() }
    }

    private fun initContentView() {
        contentView.applySystemWindowInsetsToPadding(top = true, bottom = true)
    }

    private fun initAmenityList() {
        val margin = resources.getDimensionPixelSize(R.dimen.grid_step_3)
        mAmenityAdapter.screenWidth = getWidth()
        mAmenityAdapter.margin = margin
        val gutter = resources.getDimensionPixelSize(R.dimen.grid_step_2)
        mAmenityAdapter.gutter = gutter
        amenityList.adapter = mAmenityAdapter
        amenityList.addItemDecoration(
            AmenityItemDecoration(
                top = resources.getDimensionPixelSize(R.dimen.grid_step_4),
                bottom = resources.getDimensionPixelSize(R.dimen.grid_step_6),
                margin = margin,
                gutter = gutter
            )
        )
    }

    private fun render(apartment: Apartment) {
        mGlide.load(apartment.photo)
            .centerCrop()
            .into(photo)

        name.text = apartment.name

        owner.text = apartment.owner.name
        superOwnerStatus.isVisible = apartment.owner.isSuper
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

        mAmenityAdapter.submitList(Amenity.values().toList())
    }

    companion object {
        private const val EXTRA_ID_KEY = "extra_id_key"

        fun newIntent(context: Context, apartmentId: ApartmentId) =
            Intent(context, ApartmentActivity::class.java).apply {
                putExtra(EXTRA_ID_KEY, apartmentId)
            }
    }

}
