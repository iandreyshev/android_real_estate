package ru.iandreyshev.realestate.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import ru.iandreyshev.realestate.R
import ru.iandreyshev.realestate.extension.setTranslucentBars

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        setTranslucentBars()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

}
