package ru.iandreyshev.realestate.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import ru.iandreyshev.realestate.R
import ru.iandreyshev.realestate.extension.setTranslucentBars

class ApartmentActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        setTranslucentBars()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_apartment)
    }

}
