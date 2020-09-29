package ru.iandreyshev.realestate.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.observe
import androidx.recyclerview.widget.PagerSnapHelper
import com.bumptech.glide.Glide
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import dev.chrisbanes.insetter.applySystemWindowInsetsToMargin
import kotlinx.android.synthetic.main.activity_main.*
import ru.iandreyshev.realestate.R
import ru.iandreyshev.realestate.domain.Address
import ru.iandreyshev.realestate.extension.getWidth
import ru.iandreyshev.realestate.extension.initTranslucentBars
import ru.iandreyshev.realestate.presentation.MapViewModel

class MapActivity : AppCompatActivity(R.layout.activity_main) {

    private val mViewModel: MapViewModel by viewModels()
    private lateinit var mMap: GoogleMap

    override fun onCreate(savedInstanceState: Bundle?) {
        initTranslucentBars()
        super.onCreate(savedInstanceState)

        initMap()
        initZoomButtons()
        initApartmentList()

        mViewModel.showTargetEvent.observe(this) { event ->
            event.consume { address -> moveMapToTarget(address) }
        }
        mViewModel.openApartment.observe(this) { event ->
            event.consume { apartmentId ->
                val intent = ApartmentActivity.newIntent(this, apartmentId)
                startActivity(intent)
            }
        }
    }

    private fun initMap() {
        val mapFragment = supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync { map ->
            mMap = map
            mViewModel.onMapReady()
        }
    }

    private fun initZoomButtons() {
        zoomButtonsCard.applySystemWindowInsetsToMargin(top = true)
        zoomInButton.setOnClickListener { mMap.animateCamera(CameraUpdateFactory.zoomIn()) }
        zoomOutButton.setOnClickListener { mMap.animateCamera(CameraUpdateFactory.zoomOut()) }
    }

    private fun initApartmentList() {
        val snapHelper = PagerSnapHelper()
        snapHelper.attachToRecyclerView(apartments)

        val snapScrollListener = SnapOnScrollListener(snapHelper, mViewModel::onSelectApartmentAt)

        apartments.applySystemWindowInsetsToMargin(bottom = true)
        apartments.adapter = ApartmentsAdapter(
            glide = Glide.with(this),
            onClickListener = { position ->
                apartments.smoothScrollToPosition(position)
                mViewModel.onOpenApartmentAt(position)
            }
        ).apply {
            itemWidth = (getWidth() * 0.85).toInt()
            submitList(mViewModel.apartments)
        }
        apartments.addItemDecoration(
            ApartmentsItemDecoration(
                vertical = resources.getDimensionPixelSize(R.dimen.grid_step_3),
                margin = resources.getDimensionPixelSize(R.dimen.grid_step_4),
                gutter = resources.getDimensionPixelSize(R.dimen.grid_step_2)
            )
        )
        apartments.addOnScrollListener(snapScrollListener)
    }

    private fun moveMapToTarget(address: Address) {
        val position = LatLng(address.lat, address.lng)
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(position, 15f))
    }

}
