package com.example.goodride

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.viewpager2.widget.ViewPager2
import com.example.goodride.Adapter.ViewPagerAdapter
import com.example.goodride.LOGIN.Login
import me.relex.circleindicator.CircleIndicator
import me.relex.circleindicator.CircleIndicator2
import me.relex.circleindicator.CircleIndicator3


class MainActivity : AppCompatActivity(), View.OnClickListener {

    private var titleList = mutableListOf<String>()
    private var descList = mutableListOf<String>()
    private var imageList = mutableListOf<Int >()
    private lateinit var buttonSkip: Button

    private var viewPagerImages = arrayOf(R.drawable.googlemap, R.drawable.call, R.drawable.biker, R.drawable.ride, R.drawable.time)
    private var title = arrayOf("Set Location", "Call from rider", "Rider on the way", "Share their ride", "On time")
    private var description = arrayOf("User can set their location", "Receive the call from nearest rider", "Wait for the rider for short period",
    "Share their ride to your destination", "To destination on time")


    private lateinit var view_pager2: ViewPager2
    private lateinit var indicator: CircleIndicator3

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        PostToList()

        view_pager2 = findViewById(R.id.view_pager2)
        indicator = findViewById(R.id.indicator)
        buttonSkip= findViewById(R.id.btnSkip)

        view_pager2.adapter = ViewPagerAdapter(titleList, descList, imageList)
        view_pager2.orientation = ViewPager2.ORIENTATION_HORIZONTAL

        indicator.setViewPager(view_pager2)

        buttonSkip.setOnClickListener(this)



    }

    private fun addToList(title:String, details: String, image: Int){
        titleList.add(title)
        descList.add(details)
        imageList.add(image)
    }

    private fun PostToList(){
        for (i in 0..4){
            addToList(title[i], description[i], viewPagerImages[i] )

            if (i ==4){

            }

        }
    }

    override fun onResume() {
        super.onResume()
        supportActionBar!!.hide()
    }

    override fun onClick(v: View?) {
        val intent = Intent(this, Login::class.java)
        startActivity(intent)
    }
}
