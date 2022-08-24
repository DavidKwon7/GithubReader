package com.example.githubreader.ui.feature.search

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.common.base.BaseFragment
import com.example.githubreader.R
import com.example.githubreader.databinding.FragmentSearchBinding
import com.example.githubreader.ui.feature.detail.DetailActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchFragment : BaseFragment<FragmentSearchBinding>() {
    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentSearchBinding {
        return FragmentSearchBinding.inflate(inflater, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val viewModel: SearchViewModel by viewModels()

        binding.recyclerView.adapter = SearchAdapter()
        binding.submitBtn.setOnClickListener {
            val owner = binding.ownerEditText.text.toString()
            viewModel.getRepos(owner)
        }

        viewModel.githubUserLiveData.observe(viewLifecycleOwner){
            (binding.recyclerView.adapter as SearchAdapter)
                .setItems(
                    it,
                    itemClickListener = {
                        val intent = Intent(getActivity(), DetailActivity::class.java)
                        intent.putExtra("name", it.name)
                        intent.putExtra("url", it.url)
                        startActivity(intent)
                    }
                )
        }
    }
}