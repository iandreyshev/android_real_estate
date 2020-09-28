package ru.iandreyshev.realestate.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.PagerSnapHelper
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import dev.chrisbanes.insetter.applySystemWindowInsetsToMargin
import kotlinx.android.synthetic.main.activity_main.*
import ru.iandreyshev.realestate.R
import ru.iandreyshev.realestate.domain.Apartment
import ru.iandreyshev.realestate.domain.ApartmentStorage
import ru.iandreyshev.realestate.extension.getWidth
import ru.iandreyshev.realestate.extension.setTranslucentBars


class MainActivity : AppCompatActivity() {

    private lateinit var mMap: GoogleMap

    override fun onCreate(savedInstanceState: Bundle?) {
        setTranslucentBars()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val mapFragment = supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(::onMapReady)

        zoomButtonsCard.applySystemWindowInsetsToMargin(top = true)
        zoomInButton.setOnClickListener { zoomIn() }
        zoomOutButton.setOnClickListener { zoomOut() }

        val snapHelper = PagerSnapHelper()
        snapHelper.attachToRecyclerView(apartments)

        val snapScrollListener = SnapOnScrollListener(snapHelper = snapHelper) { position ->
            val apartment = ApartmentStorage.apartments[position]
            onChangeMapTarget(apartment)
        }

        apartments.applySystemWindowInsetsToMargin(bottom = true)
        apartments.adapter = ApartmentsAdapter().apply {
            itemWidth = (getWidth() * 0.85).toInt()
            submitList(ApartmentStorage.apartments)
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

    private fun onMapReady(map: GoogleMap) {
        mMap = map

        val yola = LatLng(56.633331, 47.866669)
        mMap.addMarker(MarkerOptions().position(yola).title("Marker in Yoshkar-Ola"))
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(yola, 15f))
    }

    private fun zoomIn() {
        mMap.moveCamera(CameraUpdateFactory.zoomIn())
    }

    private fun zoomOut() {
        mMap.moveCamera(CameraUpdateFactory.zoomOut())
    }

    private fun onChangeMapTarget(target: Apartment) {
        val address = target.address
        val position = LatLng(address.lat, address.lng)
        mMap.moveCamera(CameraUpdateFactory.newLatLng(position))
    }

}
