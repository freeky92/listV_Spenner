package com.bignerdranch.android.listv_spenner

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bignerdranch.android.listv_spenner.databinding.MainActivityBinding
import com.bignerdranch.android.listv_spenner.ui.main.MainFragment

class MainActivity : AppCompatActivity(R.layout.main_activity) {

    private lateinit var binding: MainActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = MainActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, MainFragment.newInstance())
                .commitNow()
        }
    }
}