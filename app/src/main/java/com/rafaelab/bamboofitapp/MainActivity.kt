package com.rafaelab.bamboofitapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager
import com.rafaelab.bamboofitapp.Adapter.PageAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var mViewPager: ViewPager
    private lateinit var btnHome: ImageButton
    private lateinit var btnProfile: ImageButton
    private lateinit var btnFitness: ImageButton
    private lateinit var btnSearch: ImageButton
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
        btnHome = findViewById(R.id.btnHome)
        btnProfile = findViewById(R.id.btnProfile)
        btnFitness = findViewById(R.id.btnFitness)
        btnSearch = findViewById(R.id.btnSearch)

        mPagerAdapter = PageAdapter(supportFragmentManager)
        mViewPager.adapter = mPagerAdapter
        mViewPager.offscreenPageLimit = 4

        mViewPager.addOnPageChangeListener(object :ViewPager.OnPageChangeListener{


            override fun onPageScrollStateChanged(state: Int) {

            }

            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {

            }

            override fun onPageSelected(position: Int) {
                changingTabs(position)
            }
        })

        mViewPager.currentItem = 0
        btnHome.setImageResource(R.drawable.ic_home_green)
    }

    private fun changingTabs(position: Int){

        if (position==0){
            btnHome.setImageResource(R.drawable.ic_home_green)
            btnProfile.setImageResource(R.drawable.ic_person_black)
            btnFitness.setImageResource(R.drawable.ic_fitness_center_black)
            btnSearch.setImageResource(R.drawable.ic_search_black)
        }

        if (position==1){
            btnHome.setImageResource(R.drawable.ic_home_black)
            btnProfile.setImageResource(R.drawable.ic_person_green)
            btnFitness.setImageResource(R.drawable.ic_fitness_center_black)
            btnSearch.setImageResource(R.drawable.ic_search_black)
        }

        if (position==2){
            btnHome.setImageResource(R.drawable.ic_home_black)
            btnProfile.setImageResource(R.drawable.ic_person_black)
            btnFitness.setImageResource(R.drawable.ic_fitness_center_green)
            btnSearch.setImageResource(R.drawable.ic_search_black)
        }

        if (position==3){
            btnHome.setImageResource(R.drawable.ic_home_black)
            btnProfile.setImageResource(R.drawable.ic_person_black)
            btnFitness.setImageResource(R.drawable.ic_fitness_center_black)
            btnSearch.setImageResource(R.drawable.ic_search_green)
        }
    }
}
