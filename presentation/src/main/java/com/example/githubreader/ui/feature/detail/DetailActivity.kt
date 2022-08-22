package com.example.githubreader.ui.feature.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.githubreader.R
import com.example.githubreader.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        getIntentData()
        clickFavorite()
    }

    private fun clickFavorite() = with(binding) {
        ivFavorite.setOnClickListener {
            Toast.makeText(this@DetailActivity, "즐겨찾기에 등록합니다", Toast.LENGTH_SHORT).show()
        }
    }

    private fun getIntentData() = with(binding) {
        val name = intent.getStringExtra("name").toString()
        val url = intent.getStringExtra("url").toString()

        tvName.text = name
        tvUrl.text = url
    }
}