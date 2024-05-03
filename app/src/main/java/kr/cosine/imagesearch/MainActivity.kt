package kr.cosine.imagesearch

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.tabs.TabLayoutMediator
import kr.cosine.imagesearch.databinding.ActivityMainBinding
import kr.cosine.imagesearch.view.fragment.adapter.FragmentAdapter

class MainActivity : AppCompatActivity() {

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        registerViewPager()
    }

    private fun registerViewPager() = with(binding) {
        viewPager.adapter = FragmentAdapter(supportFragmentManager, lifecycle)
        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = getTabName(position)
        }.attach()
    }

    private fun getTabName(position: Int): String {
        return when (position) {
            0 -> getString(R.string.search_tab_item_title)
            1 -> getString(R.string.bookmark_tab_item_title)
            else -> throw IllegalArgumentException()
        }
    }
}