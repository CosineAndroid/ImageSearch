package kr.cosine.imagesearch.view.fragment.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import kr.cosine.imagesearch.view.fragment.impl.BookmarkFragment
import kr.cosine.imagesearch.view.fragment.impl.SearchFragment

class FragmentAdapter(
    fragmentManager: FragmentManager,
    lifecycle: Lifecycle
) : FragmentStateAdapter(fragmentManager, lifecycle) {

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> SearchFragment()
            1 -> BookmarkFragment()
            else -> throw NullPointerException("${position}번 Fragment는 존재하지 않습니다.")
        }
    }

    override fun getItemCount(): Int = PAGE_COUNT

    private companion object {
        const val PAGE_COUNT = 2
    }
}