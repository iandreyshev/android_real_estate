package ru.iandreyshev.realestate.ui

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
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import com.google.maps.android.ui.IconGenerator
import dev.chrisbanes.insetter.applySystemWindowInsetsToMargin
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.view_marker.view.*
import ru.iandreyshev.realestate.R
import ru.iandreyshev.realestate.domain.Address
import ru.iandreyshev.realestate.domain.ApartmentId
import ru.iandreyshev.realestate.extension.getWidth
import ru.iandreyshev.realestate.extension.initTranslucentBars
import ru.iandreyshev.realestate.extension.rubSymbol
import ru.iandreyshev.realestate.extension.uiLazy
import ru.iandreyshev.realestate.presentation.MapViewModel

class MapActivity : AppCompatActivity(R.layout.activity_main) {

    private val mViewModel: MapViewModel by viewModels()
    private lateinit var mMap: GoogleMap
    private val mMarkers = mutableMapOf<ApartmentId, MarkerHolder>()
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
            mViewModel.apartmentMarkers.observe(this, ::renderMarkers)
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
        val animation = CameraUpdateFactory.newLatLngZoom(position, APARTMENT_ZOOM)
        mMap.animateCamera(animation)
    }

    private fun renderMarkers(markers: List<ApartmentMarker>) {
        markers.forEach { newMarker ->
            val markerHolder = mMarkers[newMarker.id]
            val oldMarker = markerHolder?.appMarker ?: kotlin.run {
                val newMapMarker = addMarker(newMarker)

                mMarkers[newMarker.id] = MarkerHolder(
                    appMarker = newMarker,
                    mapMarker = newMapMarker
                )

                return@forEach
            }

            if (oldMarker != newMarker) {
                markerHolder.mapMarker.remove()
                mMarkers[oldMarker.id] = markerHolder.copy(
                    appMarker = newMarker,
                    mapMarker = addMarker(newMarker)
                )
            }
        }
    }

    private fun addMarker(appMarker: ApartmentMarker): Marker {
        val view = LayoutInflater.from(this)
            .inflate(R.layout.view_marker, null, false)

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
            .position(LatLng(appMarker.address.lat, appMarker.address.lng))
            .icon(BitmapDescriptorFactory.fromBitmap(bitmap))

        return mMap.addMarker(marker)
    }

    companion object {
        private const val APARTMENT_ZOOM = 15f
    }

}
