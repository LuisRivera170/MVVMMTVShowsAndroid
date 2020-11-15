package com.lara.mvvmmtvshows.activities

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.text.HtmlCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.widget.ViewPager2
import com.lara.mvvmmtvshows.R
import com.lara.mvvmmtvshows.adapters.ImageSliderAdapter
import com.lara.mvvmmtvshows.databinding.ActivityTvShowDetailsBinding
import com.lara.mvvmmtvshows.models.TVShow
import com.lara.mvvmmtvshows.viewmodels.TVShowDetailsViewModel
import java.util.*

class TvShowDetailsActivity: AppCompatActivity() {

    private lateinit var activityTVShowDetailsBinding: ActivityTvShowDetailsBinding
    private lateinit var tvShowDetailsViewModel: TVShowDetailsViewModel
    private var tvShow: TVShow? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityTVShowDetailsBinding = DataBindingUtil.setContentView(this, R.layout.activity_tv_show_details)
        doInitialization()
    }

    private fun doInitialization() {
        tvShowDetailsViewModel = ViewModelProvider(this).get(TVShowDetailsViewModel::class.java)
        tvShow = intent.getParcelableExtra("tvShow")
        activityTVShowDetailsBinding.imageBack.setOnClickListener {
            onBackPressed()
        }
        getTvShowDetails()
    }

    private fun getTvShowDetails() {
        tvShow?.let {
            activityTVShowDetailsBinding.isLoading = true
            val tvShowId: String = it.id.toString()
            tvShowDetailsViewModel.getTvShowDetails(tvShowId).observe(this, { tvShowDetailResponse ->
                activityTVShowDetailsBinding.isLoading = false
                tvShowDetailResponse.tvShowDetails.let { tvShowDetails ->
                    tvShowDetails.pictures?.let { pictures ->
                        loadImageSlider(pictures)
                    }
                    activityTVShowDetailsBinding.tvShowImageURL = tvShowDetails.imagePath
                    activityTVShowDetailsBinding.imageTVShow.visibility = View.VISIBLE
                    activityTVShowDetailsBinding.description = HtmlCompat.fromHtml(tvShowDetails.description, HtmlCompat.FROM_HTML_MODE_LEGACY).toString()
                    activityTVShowDetailsBinding.textDescription.visibility = View.VISIBLE
                    activityTVShowDetailsBinding.textReadMore.visibility = View.VISIBLE
                    activityTVShowDetailsBinding.textReadMore.setOnClickListener {
                        if (activityTVShowDetailsBinding.textReadMore.text.toString() == "Read More") {
                            activityTVShowDetailsBinding.textDescription.maxLines = Integer.MAX_VALUE
                            activityTVShowDetailsBinding.textDescription.ellipsize = null
                            activityTVShowDetailsBinding.textReadMore.text = getString(R.string.read_less)
                        } else {
                            activityTVShowDetailsBinding.textDescription.maxLines = 4
                            activityTVShowDetailsBinding.textDescription.ellipsize = TextUtils.TruncateAt.END
                            activityTVShowDetailsBinding.textReadMore.text = getString(R.string.read_more)
                        }
                    }
                    activityTVShowDetailsBinding.rating = String.format(Locale.getDefault(), "%.2f", tvShowDetails.rating.toDouble())
                    tvShowDetails.genres?.let {
                        activityTVShowDetailsBinding.genre = tvShowDetails.genres[0]
                    } ?: run {
                        activityTVShowDetailsBinding.genre = "N/A"
                    }
                    activityTVShowDetailsBinding.runtime = "${tvShowDetails.runtime} Min"
                    activityTVShowDetailsBinding.viewDivider.visibility = View.VISIBLE
                    activityTVShowDetailsBinding.layoutMisc.visibility = View.VISIBLE
                    activityTVShowDetailsBinding.viewDivider2.visibility = View.VISIBLE
                    activityTVShowDetailsBinding.buttonWebsite.setOnClickListener {
                        val intent = Intent(Intent.ACTION_VIEW)
                        intent.data = Uri.parse(tvShowDetails.url)
                        startActivity(intent)
                    }
                    activityTVShowDetailsBinding.buttonWebsite.visibility = View.VISIBLE
                    activityTVShowDetailsBinding.buttonEpisodes.visibility = View.VISIBLE
                    loadBasicTVShowDetails()
                }
            })
        } ?: run {
            Toast.makeText(this, "Ocurrió un error, intentelo nuevamente", Toast.LENGTH_LONG).show()
            finish()
        }
    }

    private fun loadImageSlider(sliderImages: Array<String>) {
        activityTVShowDetailsBinding.sliderViewPager.offscreenPageLimit = 1
        activityTVShowDetailsBinding.sliderViewPager.adapter = ImageSliderAdapter(sliderImages)
        activityTVShowDetailsBinding.sliderViewPager.visibility = View.VISIBLE
        activityTVShowDetailsBinding.viewFadingEdge.visibility = View.VISIBLE
        setupSliderIndicators(sliderImages.count())
        activityTVShowDetailsBinding.sliderViewPager.registerOnPageChangeCallback(object: ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                setCurrentSliderIndicator(position)
            }
        })
    }

    private fun setupSliderIndicators(count: Int) {
        val indicators: Array<ImageView?> = arrayOfNulls(count)
        val layoutParams: LinearLayout.LayoutParams = LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        layoutParams.setMargins(8, 0, 8, 0)

        for (i in 0 until count) {
            indicators[i] = ImageView(applicationContext)
            indicators[i]?.setImageDrawable(ContextCompat.getDrawable(
                applicationContext,
                R.drawable.background_slider_indicator_inactive
            ))
            indicators[i]?.layoutParams = layoutParams
            activityTVShowDetailsBinding.layoutSliderIndicators.addView(indicators[i])
        }

        activityTVShowDetailsBinding.layoutSliderIndicators.visibility = View.VISIBLE
        setCurrentSliderIndicator(0)
    }

    private fun setCurrentSliderIndicator(position: Int) {
        val childCount = activityTVShowDetailsBinding.layoutSliderIndicators.childCount
        for (i in 0 until childCount) {
            val imageView = activityTVShowDetailsBinding.layoutSliderIndicators.getChildAt(i) as ImageView
            if (i == position) {
                imageView.setImageDrawable(ContextCompat.getDrawable(applicationContext, R.drawable.background_slider_indicator_active))
            } else {
                imageView.setImageDrawable(ContextCompat.getDrawable(applicationContext, R.drawable.background_slider_indicator_inactive))
            }
        }
    }

    private fun loadBasicTVShowDetails() {
        activityTVShowDetailsBinding.tvShowName = tvShow?.name
        activityTVShowDetailsBinding.networkCountry = tvShow?.network + "(" + tvShow?.country + ")"
        activityTVShowDetailsBinding.status = tvShow?.status
        activityTVShowDetailsBinding.startDate = tvShow?.startDate
        activityTVShowDetailsBinding.textName.visibility = View.VISIBLE
        activityTVShowDetailsBinding.textNetworkCountry.visibility = View.VISIBLE
        activityTVShowDetailsBinding.textStatus.visibility = View.VISIBLE
        activityTVShowDetailsBinding.textStarted.visibility = View.VISIBLE
    }

}