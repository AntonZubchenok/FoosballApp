package com.example.foosballapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.foosballapp.databinding.ActivityMainBinding
import com.example.foosballapp.gameresults.GameResultsFragment
import com.example.foosballapp.rankings.RankingsFragment
import dagger.android.support.DaggerAppCompatActivity

class MainActivity : DaggerAppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        showFragment(GameResultsFragment())

        binding.bottomNavigation.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.page_1 -> {
                    showFragment(GameResultsFragment())
                    true
                }
                R.id.page_2 -> {
                    showFragment(RankingsFragment())
                    true
                }
                else -> false
            }
        }
    }

    private fun showFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .commit()
    }
}