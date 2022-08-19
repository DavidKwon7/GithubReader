package com.example.githubreader

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.githubreader.ui.feature.favorite.FavoriteFragment
import com.example.githubreader.ui.feature.search.SearchFragment
import com.example.githubreader.ui.feature.setting.SettingFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val searchFragment = SearchFragment()
        val favoriteFragment = FavoriteFragment()
        val settingFragment = SettingFragment()

        makeCurrentFragment(searchFragment)

        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottomNavigationView)
        bottomNavigationView.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.item_searchFragment -> makeCurrentFragment(searchFragment)
                R.id.item_favoriteFragment -> makeCurrentFragment(favoriteFragment)
                R.id.item_settingFragment -> makeCurrentFragment(settingFragment)
            }
            true
        }
    }

    private fun makeCurrentFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.searchFragment, fragment)
            commit()
        }
    }
}