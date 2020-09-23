package com.rafaelab.bamboofitapp.Adapter
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.rafaelab.bamboofitapp.Fragment.FitnessFragment
import com.rafaelab.bamboofitapp.Fragment.HomeFragment
import com.rafaelab.bamboofitapp.Fragment.ProfileFragment
import com.rafaelab.bamboofitapp.Fragment.SearchFragment

internal class PageAdapter (fm: FragmentManager?):
    FragmentPagerAdapter(fm!!) {

    override fun getItem(position: Int): Fragment {
        return when(position){
            0 -> {
                HomeFragment()
            }
            1 -> {
                ProfileFragment()
            }
            2 -> {
                FitnessFragment()
            }
            3 -> {
                SearchFragment()
            }
            else -> HomeFragment()
        }
    }

    override fun getCount(): Int {
        return  4
    }

}