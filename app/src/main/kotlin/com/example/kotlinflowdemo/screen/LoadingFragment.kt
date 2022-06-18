package com.example.kotlinflowdemo.screen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.add
import androidx.fragment.app.commit
import com.example.kotlinflowdemo.databinding.FragmentLoadingBinding

class LoadingFragment : Fragment() {

    private var _binding: FragmentLoadingBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentLoadingBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.progressBar.show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {

        private const val TAG_LOADING_FRAGMENT = "loading_fragment_tag"

        fun showLoading(fragmentManager: FragmentManager, @IdRes fragmentContainer: Int) {
            fragmentManager.commit {
                add<LoadingFragment>(fragmentContainer, TAG_LOADING_FRAGMENT)
            }
        }

        fun hideLoading(fragmentManager: FragmentManager) {
            fragmentManager.findFragmentByTag(TAG_LOADING_FRAGMENT)?.let {
                fragmentManager.commit {
                    remove(it)
                }
            }
        }
    }
}