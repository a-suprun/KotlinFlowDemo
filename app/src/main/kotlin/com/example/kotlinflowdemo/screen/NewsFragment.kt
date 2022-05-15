package com.example.kotlinflowdemo.screen

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlinflowdemo.databinding.FragmentNewsBinding
import com.example.kotlinflowdemo.network.Article
import com.example.kotlinflowdemo.viewmodel.MainViewModel
import com.example.kotlinflowdemo.viewmodel.NewsUiState
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch


class NewsFragment : Fragment() {

    private var _binding: FragmentNewsBinding? = null
    private val binding get() = _binding!!
    private val mViewModel: MainViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentNewsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                mViewModel.newsFlow.collect { newsFlow ->
                    when (newsFlow) {
                        is NewsUiState.Success -> showNews(newsFlow.news)
                        is NewsUiState.Error -> showError(newsFlow.exception)
                    }
                }
            }
        }
    }

    private fun showNews(newsList: List<Article>) {
        // TODO: add update logic
        binding.rvNews.addItemDecoration(DividerItemDecoration(requireContext(), RecyclerView.VERTICAL))
        binding.rvNews.adapter = NewsAdapter(newsList)
    }

    private fun showError(e: Throwable) {
        Toast.makeText(requireContext(), "Error: ${e.message}", Toast.LENGTH_SHORT).show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}