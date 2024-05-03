package kr.cosine.imagesearch.view.fragment.impl

import android.os.Bundle
import android.view.View
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kr.cosine.imagesearch.databinding.FragmentBookmarkBinding
import kr.cosine.imagesearch.view.fragment.BaseFragment
import kr.cosine.imagesearch.view.model.event.SearchListEvent

class BookmarkFragment : BaseFragment<FragmentBookmarkBinding>(FragmentBookmarkBinding::inflate) {

    override val recyclerView get() = binding.bookmarkRecyclerView

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        registerViewModelEvent()
    }

    private fun registerViewModelEvent() {
        viewLifecycleOwner.lifecycleScope.launch {
            searchViewModel.event.flowWithLifecycle(lifecycle).collectLatest { event ->
                if (event is SearchListEvent.UpdateBookmark) {
                    searchAdapter.update(event.items)
                }
            }
        }
    }
}