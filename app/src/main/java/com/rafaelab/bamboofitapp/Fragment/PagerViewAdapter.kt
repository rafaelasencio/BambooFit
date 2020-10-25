package com.rafaelab.bamboofitapp.Fragment

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

internal class PagerViewAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {
    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> {
                HomeFragment()
            }
            1 -> {
                ProfileFragment()
            }
            2 -> {
                CalculatorFragment()
            }
            3 -> {
                DietFragment()
            }
            else -> HomeFragment()

        }
    }

    override fun getCount(): Int {
        return 4
    }



}

