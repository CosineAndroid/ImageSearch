package kr.cosine.imagesearch.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import kr.cosine.imagesearch.view.model.SearchViewModel
import kr.cosine.imagesearch.view.model.factory.SearchViewModelFactory
import kr.cosine.imagesearch.view.recyclerview.adpater.SearchAdpater

abstract class BaseFragment<T : ViewBinding>(
    private val inflate: (LayoutInflater, ViewGroup?, Boolean) -> T
) : Fragment() {

    private var _binding: T? = null
    protected val binding get() = _binding!!

    protected abstract val recyclerView: RecyclerView

    protected val searchViewModel: SearchViewModel by activityViewModels { SearchViewModelFactory() }

    protected lateinit var searchAdapter: SearchAdpater

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = inflate.invoke(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        registerRecyclerView()
    }

    private fun registerRecyclerView() = with(recyclerView) {
        layoutManager = GridLayoutManager(context, 2)
        adapter = SearchAdpater(searchViewModel::onBookmark).apply {
            searchAdapter = this
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}