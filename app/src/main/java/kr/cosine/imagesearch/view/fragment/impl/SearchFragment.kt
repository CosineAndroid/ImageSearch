package kr.cosine.imagesearch.view.fragment.impl

import android.os.Bundle
import android.view.View
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kr.cosine.imagesearch.databinding.FragmentSearchBinding
import kr.cosine.imagesearch.view.fragment.BaseFragment

class SearchFragment : BaseFragment<FragmentSearchBinding>(FragmentSearchBinding::inflate) {

    override val recyclerView get() = binding.searchRecyclerView

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        hideKeyboard()
        registerSearchButton()
        registerViewModelEvent()
    }

    private fun hideKeyboard() {
        binding.searchEditText.inputType = 0
    }

    private fun registerSearchButton() = with(binding) {
        searchButton.setOnClickListener {
            val input = searchEditText.text.toString()
            searchViewModel.onSearch(input)
        }
    }

    private fun registerViewModelEvent() {
        viewLifecycleOwner.lifecycleScope.launch {
            searchViewModel.uiState.flowWithLifecycle(lifecycle).collectLatest { uiState ->
                searchAdapter.update(uiState.items)
            }
        }
    }
}