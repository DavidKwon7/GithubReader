package com.example.githubreader.ui.feature.favorite

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.LifecycleOwner
import com.example.common.base.BaseFragment
import com.example.githubreader.R
import com.example.githubreader.databinding.FragmentFavoriteBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavoriteFragment : BaseFragment<FragmentFavoriteBinding>() {

    val viewModel: FavoriteViewModel by viewModels()

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentFavoriteBinding {
        return FragmentFavoriteBinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.rvFavorite.adapter = FavoriteAdapter()
        viewModel.getAllLocalData()

        observeLiveData()
    }

    private fun observeLiveData() {
        viewModel.favoriteLiveData.observe(viewLifecycleOwner) {
            (binding.rvFavorite.adapter as FavoriteAdapter).setItems(
                it,
                deleteItemClickListener = {
                    Toast.makeText(getActivity(), "삭제", Toast.LENGTH_SHORT).show()
                }
            )
        }
    }
}