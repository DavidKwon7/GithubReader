package com.example.githubreader.ui.feature.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import com.example.domain.entity.UserEntityModel
import com.example.githubreader.R
import com.example.githubreader.databinding.ActivityDetailBinding
import com.example.githubreader.model.UserPresentationModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding
    val viewModel: DetailViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        getIntentData()
        clickFavorite()
        observe()
    }

    fun observe() {
        viewModel.favoriteLiveData.observe(this) {
            binding.tvId.text = it.id.toString()
            binding.tvName.text = it.name
            binding.tvUrl.text = it.url
        }
    }

    private fun clickFavorite() = with(binding) {
        ivFavorite.setOnClickListener {
            Toast.makeText(this@DetailActivity, "즐겨찾기에 등록합니다", Toast.LENGTH_SHORT).show()
            insertDB()
        }
    }

    private fun insertDB() {
        val userEntityModel = UserEntityModel(
            uid = null,
            id = binding.tvId.id,
            name = binding.tvName.text.toString(),
            url = binding.tvUrl.text.toString()
        )
        viewModel.insertLocalDB(userEntityModel)
    }

    private fun getIntentData() = with(binding) {
        val name = intent.getStringExtra("name").toString()
        val url = intent.getStringExtra("url").toString()

        tvName.text = name
        tvUrl.text = url
    }
}