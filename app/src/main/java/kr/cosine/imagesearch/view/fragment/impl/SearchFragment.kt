package kr.cosine.imagesearch.view.fragment.impl

import android.content.Context
import android.content.Context.INPUT_METHOD_SERVICE
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.core.content.ContextCompat.getSystemService
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kr.cosine.imagesearch.databinding.FragmentSearchBinding
import kr.cosine.imagesearch.view.fragment.BaseFragment


class SearchFragment : BaseFragment<FragmentSearchBinding>(FragmentSearchBinding::inflate) {

    override val recyclerView get() = binding.searchRecyclerView

    private val sharedPreferences by lazy { activity?.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        registerSearchButton()
        registerViewModelEvent()
        loadInput()
    }

    private fun registerSearchButton() = with(binding) {
        searchButton.setOnClickListener {
            val input = searchEditText.text.toString()
            if (input.isBlank()) return@setOnClickListener
            hideKeyboard()
            searchViewModel.onSearch(input)
            saveInput(input)
        }
    }

    private fun hideKeyboard() = activity?.let {
        val manager = it.getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        manager.hideSoftInputFromWindow(it.currentFocus?.windowToken, InputMethodManager.HIDE_NOT_ALWAYS)
    }

    private fun registerViewModelEvent() {
        viewLifecycleOwner.lifecycleScope.launch {
            searchViewModel.uiState.flowWithLifecycle(lifecycle).collectLatest { uiState ->
                searchAdapter.update(uiState.items)
            }
        }
    }

    private fun loadInput() {
        sharedPreferences?.getString(INPUT_KEY, INPUT_DEFAULT_VALUE)?.let {
            if (it.isBlank()) return
            binding.searchEditText.setText(it)
            searchViewModel.onSearch(it)
        }
    }

    private fun saveInput(input: String) {
        sharedPreferences?.edit()?.apply {
            putString(INPUT_KEY, input)
            apply()
        }
    }

    private companion object {
        const val FILE_NAME = "ImageSearch"
        const val INPUT_KEY = "Input"
        const val INPUT_DEFAULT_VALUE = ""
    }
}