package com.rafaelab.bamboofitapp

import android.content.Intent
import android.media.Image
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import androidx.core.view.get
import androidx.viewpager2.widget.ViewPager2
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.android.synthetic.main.activity_welcome.*

class WelcomeActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    private val introSliderAdapter = IntroSliderAdapter(
        listOf(
            IntroSlide(
                "Realiza los ejercicios que mejor se adapten a tu nivel",
                "",
                R.drawable.ic_onboarding1
            ),
            IntroSlide(
                "Escoge entre una gran variedad de alimentos y dietas saludables",
                "",
                R.drawable.ic_onboarding2
            ),
            IntroSlide(
                "Registra tus rutinas para llevar un control de tu progreso",
                "",
                R.drawable.ic_onboarding3
            )
        )
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)

        val someView: View = findViewById(R.id.ll_welcome)
        val root = someView.rootView
        root.setBackgroundColor(resources.getColor(R.color.colorWhite))

        auth = FirebaseAuth.getInstance()

        checkIfUserIsLogged(auth.currentUser)
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
                Intent(applicationContext, RegisterActivity::class.java).also {
                    startActivity(it)
                }
            }
        }
        tvSkipIntro.setOnClickListener {
            Intent(applicationContext, RegisterActivity::class.java).also {
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

    private fun checkIfUserIsLogged(user: FirebaseUser?){
        if(user != null){
            startActivity(Intent(this, MainActivity::class.java))
        }
    }
}
