package ru.iandreyshev.realestate.ui.map

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.observe
import androidx.recyclerview.widget.PagerSnapHelper
import com.bumptech.glide.Glide
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import com.google.maps.android.ui.IconGenerator
import dev.chrisbanes.insetter.applySystemWindowInsetsToMargin
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.view_apartment_marker.view.*
import ru.iandreyshev.realestate.R
import ru.iandreyshev.realestate.domain.ApartmentId
import ru.iandreyshev.realestate.domain.Position
import ru.iandreyshev.realestate.extension.*
import ru.iandreyshev.realestate.presentation.MapViewModel
import ru.iandreyshev.realestate.ui.apartment.MarkerHolder
import ru.iandreyshev.realestate.ui.apartment.ApartmentActivity

class MapActivity : AppCompatActivity(R.layout.activity_main) {

    private val mViewModel: MapViewModel by viewModels()
    private lateinit var mMap: GoogleMap
    private val mApartmentMarkers = mutableMapOf<ApartmentId, MarkerHolder>()
    private var mUserMarker: Marker? = null
    private val mMarkerGenerator by uiLazy {
        IconGenerator(this)
            .apply { setBackground(null) }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        initTranslucentBars()
        super.onCreate(savedInstanceState)

        initMap()
        initZoomButtons()
        initApartmentList()

        mViewModel.showTargetEvent.consume(this, ::moveMapToTarget)
        mViewModel.openApartment.consume(this) { apartmentId ->
            val intent = ApartmentActivity.newIntent(this, apartmentId)
            startActivity(intent)
        }
    }

    private fun initMap() {
        val mapFragment = supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync { map ->
            mMap = map
            mViewModel.onMapReady()
            mViewModel.apartmentMarkers.observe(this, ::renderApartmentMarkers)
            mViewModel.userMarker.observe(this, ::renderUserMarker)
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

    private fun moveMapToTarget(position: Position) {
        val animation = CameraUpdateFactory.newLatLngZoom(position.latLng(), APARTMENT_ZOOM)
        mMap.animateCamera(animation)
    }

    private fun renderApartmentMarkers(markers: List<ApartmentMarker>) {
        markers.forEach { newMarker ->
            val markerHolder = mApartmentMarkers[newMarker.id]
            val oldMarker = markerHolder?.appMarker ?: kotlin.run {
                val newMapMarker = addMarker(newMarker)

                mApartmentMarkers[newMarker.id] = MarkerHolder(
                    appMarker = newMarker,
                    mapMarker = newMapMarker
                )

                return@forEach
            }

            if (oldMarker != newMarker) {
                markerHolder.mapMarker.remove()
                mApartmentMarkers[oldMarker.id] = markerHolder.copy(
                    appMarker = newMarker,
                    mapMarker = addMarker(newMarker)
                )
            }
        }
    }

    private fun addMarker(appMarker: ApartmentMarker): Marker {
        val view = LayoutInflater.from(this)
            .inflate(R.layout.view_apartment_marker, null, false)

        view.cost.setBackgroundResource(
            when (appMarker.isActive) {
                true -> R.drawable.background_marker_body_active
                else -> R.drawable.background_marker_body_passive
            }
        )
        view.cost.setTextColor(
            when (appMarker.isActive) {
                true -> Color.WHITE
                else -> Color.BLACK
            }
        )
        view.cost.text = "${rubSymbol()} ${appMarker.cost}"
        view.pointer.isActive = appMarker.isActive

        mMarkerGenerator.setContentView(view)

        val bitmap = mMarkerGenerator.makeIcon()
        val marker = MarkerOptions()
            .position(appMarker.position.latLng())
            .icon(BitmapDescriptorFactory.fromBitmap(bitmap))

        return mMap.addMarker(marker)
    }

    private fun renderUserMarker(position: Position) {
        mUserMarker ?: kotlin.run {
            val view = LayoutInflater.from(this)
                .inflate(R.layout.view_user_location_marker, null, false)

            mMarkerGenerator.setContentView(view)

            val bitmap = mMarkerGenerator.makeIcon()
            val markerOptions = MarkerOptions()
                .position(position.latLng())
                .icon(BitmapDescriptorFactory.fromBitmap(bitmap))

            mUserMarker = mMap.addMarker(markerOptions)
        }
        mUserMarker?.position = position.latLng()
    }

    companion object {
        private const val APARTMENT_ZOOM = 15f
    }

}
