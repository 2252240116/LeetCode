package com.riven.leetcode

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        tv1.setOnClickListener {
            var lengthOfLongestSubstring = Algorithm1.lengthOfLongestSubstring("ababcdefcfhijklmnab")
            Log.d("lengthstring:",""+lengthOfLongestSubstring)
        }
    }


}