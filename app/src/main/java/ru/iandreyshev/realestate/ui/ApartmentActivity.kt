package ru.iandreyshev.realestate.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import dev.chrisbanes.insetter.applySystemWindowInsetsToPadding
import kotlinx.android.synthetic.main.activity_apartment.*
import ru.iandreyshev.realestate.R
import ru.iandreyshev.realestate.domain.ApartmentId
import ru.iandreyshev.realestate.extension.initTranslucentBars

class ApartmentActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        initTranslucentBars()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_apartment)

        initToolbar()
        initContentView()
    }

    private fun initToolbar() {
        backButton.setOnClickListener { finish() }
    }

    private fun initContentView() {
        contentView.applySystemWindowInsetsToPadding(top = true, bottom = true)
    }

    companion object {
        private const val EXTRA_ID_KEY = "extra_id_key"

        fun newIntent(context: Context, apartmentId: ApartmentId) =
            Intent(context, ApartmentActivity::class.java).apply {
                putExtra(EXTRA_ID_KEY, apartmentId)
            }
    }

}
