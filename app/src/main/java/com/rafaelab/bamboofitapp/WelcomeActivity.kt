package com.rafaelab.bamboofitapp

import android.content.Intent
import android.media.Image
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import androidx.core.view.get
import androidx.viewpager2.widget.ViewPager2
import kotlinx.android.synthetic.main.activity_welcome.*

class WelcomeActivity : AppCompatActivity() {

    private val introSliderAdapter = IntroSliderAdapter(
        listOf(
            IntroSlide(
                "Aa",
                "aaa",
                R.drawable.ic_action_done
            ),
            IntroSlide(
                "Bb",
                "bbb",
                R.drawable.ic_action_done
            ),
            IntroSlide(
                "Cc",
                "ccc",
                R.drawable.ic_action_done
            )
        )
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)
        vp_introSlider.adapter = introSliderAdapter
        setupIndicators()
        setCurrentIndicator(0)
        vp_introSlider.registerOnPageChangeCallback(object :
            ViewPager2.OnPageChangeCallback(){

            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                setCurrentIndicator(position)
            }
        })
        btnNext.setOnClickListener {
            if (vp_introSlider.currentItem + 1 < introSliderAdapter.itemCount){
                vp_introSlider.currentItem+=1
            } else {
                Intent(applicationContext, MainActivity::class.java).also {
                    startActivity(it)
                }
            }
        }
        tvSkipIntro.setOnClickListener {
            Intent(applicationContext, MainActivity::class.java).also {
                startActivity(it)
            }
        }
    }

    private fun setupIndicators(){
        val indicators = arrayOfNulls<ImageView>(introSliderAdapter.itemCount)
        val layoutParams: LinearLayout.LayoutParams =
            LinearLayout.LayoutParams(WRAP_CONTENT,WRAP_CONTENT)
        layoutParams.setMargins(8,0,8,0)
        for(i in indicators.indices){
            indicators[i] = ImageView(applicationContext)
            indicators[i].apply {
                this?.setImageDrawable(
                    ContextCompat.getDrawable(
                        applicationContext, R.drawable.item_indicator_inactive
                    )
                )
                this?.layoutParams = layoutParams
            }
            ll_indicators.addView(indicators[i])
        }
    }

    private fun setCurrentIndicator(index: Int){
        val childCount = ll_indicators.childCount
        for (i in 0 until childCount){
            val imageView = ll_indicators[i] as ImageView
            if (i == index){
                imageView.setImageDrawable(
                    ContextCompat.getDrawable(applicationContext, R.drawable.item_indicator_active)
                )
            }else{
                imageView.setImageDrawable(
                    ContextCompat.getDrawable(applicationContext, R.drawable.item_indicator_inactive)
                )
            }
        }
    }
}
