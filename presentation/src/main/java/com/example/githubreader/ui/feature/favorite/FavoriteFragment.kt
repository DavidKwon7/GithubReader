package com.example.githubreader.ui.feature.favorite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.viewModels
import com.example.common.base.BaseFragment
import com.example.domain.entity.UserEntityModel
import com.example.githubreader.R
import com.example.githubreader.databinding.FragmentFavoriteBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavoriteFragment() : BaseFragment<FragmentFavoriteBinding>() {

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
        swipeRefresh()
    }

    private fun swipeRefresh() {
        binding.swipeRefresh.setOnRefreshListener {
            viewModel.getAllLocalData()
                binding.swipeRefresh.isRefreshing = false
        }
    }

    private fun observeLiveData() {
        viewModel.favoriteLiveData.observe(viewLifecycleOwner) {
            (binding.rvFavorite.adapter as FavoriteAdapter).setItems(
                it,
                deleteItemClickListener = {
                    alertDialog()
                }

            )
        }
    }

    private fun alertDialog() {
        AlertDialog.Builder(requireContext())
            .setTitle("삭제")
            .setMessage("삭제하시겠습니까?")
            .setPositiveButton("네") { dialog, _ ->
                dialog.dismiss()
                deleteFavorite()
                toastMessage(getString(R.string.insertLocal))
            }
            .setNegativeButton("아니요") { dialog, _ ->
                dialog.dismiss()
            }
            .create()
            .show()
    }

    private fun deleteFavorite() {
        // todo 작성
    }

    private fun toastMessage(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }
}