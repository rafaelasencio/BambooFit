package com.rafaelab.bamboofitapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager
import com.rafaelab.bamboofitapp.utils.Constants

class MainActivity : AppCompatActivity() {

    private lateinit var mViewPager: ViewPager
    private lateinit var btnHome: ImageButton
    private lateinit var btnProfile: ImageButton
    private lateinit var btnCalculator: ImageButton
    private lateinit var btnDiet: ImageButton
    private lateinit var mPagerAdapter: PagerAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initComponents()
    }

    override fun onBackPressed() {
        if (mViewPager.currentItem == 0){
            super.onBackPressed()
        }else {
            mViewPager.currentItem = mViewPager.currentItem - 1
        }

    }

    private fun initComponents(){

        mViewPager = findViewById(R.id.mViewPager)

        btnHome = findViewById(R.id.homeBtn)
        btnProfile = findViewById(R.id.profileBtn)
        btnCalculator = findViewById(R.id.calculatorBtn)
        btnDiet = findViewById(R.id.dietBtn)

        btnHome.setOnClickListener {
            mViewPager.currentItem = 0
        }

        btnProfile.setOnClickListener {
            mViewPager.currentItem = 1
        }

        btnCalculator.setOnClickListener {
            mViewPager.currentItem = 2
        }

        btnDiet.setOnClickListener {
            mViewPager.currentItem = 3
        }

        mPagerAdapter = com.rafaelab.bamboofitapp.Fragment.PagerViewAdapter(
            supportFragmentManager
        )
        mViewPager.adapter = mPagerAdapter
        mViewPager.offscreenPageLimit = 4

        mViewPager.addOnPageChangeListener(object :ViewPager.OnPageChangeListener{
            override fun onPageScrollStateChanged(state: Int) {}
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
            }
            override fun onPageSelected(position: Int) {
                changingTabs(position)
                Constants.hideKeyboard(mViewPager.context)
            }
        })

        mViewPager.currentItem = 0
        btnHome.setImageResource(R.drawable.ic_home_green)
    }

    private fun changingTabs(position: Int){
        if (position==0){
            btnHome.setImageResource(R.drawable.ic_home_green)
            btnProfile.setImageResource(R.drawable.ic_person_black)
            btnCalculator.setImageResource(R.drawable.ic_weight_black)
            btnDiet.setImageResource(R.drawable.ic_baseline_list_black)
        }
        if (position==1){
            btnHome.setImageResource(R.drawable.ic_home_black)
            btnProfile.setImageResource(R.drawable.ic_person_green)
            btnCalculator.setImageResource(R.drawable.ic_weight_black)
            btnDiet.setImageResource(R.drawable.ic_baseline_list_black)
        }
        if (position==2){
            btnHome.setImageResource(R.drawable.ic_home_black)
            btnProfile.setImageResource(R.drawable.ic_person_black)
            btnCalculator.setImageResource(R.drawable.ic_weight_green)
            btnDiet.setImageResource(R.drawable.ic_baseline_list_black)
        }
        if (position==3){
            btnHome.setImageResource(R.drawable.ic_home_black)
            btnProfile.setImageResource(R.drawable.ic_person_black)
            btnCalculator.setImageResource(R.drawable.ic_weight_black)
            btnDiet.setImageResource(R.drawable.ic_baseline_list_green)
        }
    }
}
