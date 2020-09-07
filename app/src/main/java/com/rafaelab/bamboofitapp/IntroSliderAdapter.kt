package com.rafaelab.bamboofitapp

import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class IntroSliderAdapter(private val introSliders: List<IntroSlide>):
    RecyclerView.Adapter<IntroSliderAdapter.IntroSlideViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IntroSlideViewHolder {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getItemCount(): Int {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onBindViewHolder(holder: IntroSlideViewHolder, position: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    inner class IntroSlideViewHolder(view: View): RecyclerView.ViewHolder(view){
        private val textTitle = view.findViewById<TextView>(R.id.tvTitle)
        private val description = view.findViewById<TextView>(R.id.tvDescription)
        private val imageIcon = view.findViewById<ImageView>(R.id.ivSlideIcon)

        fun bind(introSlide: IntroSlide){
            textTitle.text = introSlide.title
            description.text = introSlide.description
            imageIcon.setImageResource(introSlide.icon)
        }
    }
}