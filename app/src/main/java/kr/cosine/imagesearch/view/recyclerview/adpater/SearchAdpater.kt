package kr.cosine.imagesearch.view.recyclerview.adpater

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kr.cosine.imagesearch.databinding.ItemSearchBinding
import kr.cosine.imagesearch.util.DateTimeUtil
import kr.cosine.imagesearch.view.model.state.item.SearchListItem

class SearchAdpater(
    private val onItemClick: (SearchListItem) -> Unit = {}
) : RecyclerView.Adapter<SearchAdpater.SearchViewHolder>() {

    inner class SearchViewHolder(
        private val binding: ItemSearchBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        private val glide = Glide.with(binding.root)

        init {
            binding.root.setOnClickListener {
                val searchListItem = searchListItems[adapterPosition]
                onItemClick(searchListItem)
            }
        }

        fun bind(searchListItem: SearchListItem) = with(binding) {
            glide.load(searchListItem.thumbnailUrl).into(thumbnailImageView)
            siteNameTextView.text = searchListItem.siteName
            dateTimeTextView.text = DateTimeUtil.toFormattedDateTime(searchListItem.dateTime)
            heartImageView.visibility = if (searchListItem.isBookmarked) View.VISIBLE else View.INVISIBLE
        }
    }

    private val searchListItems = mutableListOf<SearchListItem>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemSearchBinding.inflate(layoutInflater, parent, false)
        return SearchViewHolder(binding)
    }

    override fun getItemCount(): Int = searchListItems.size

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        val item = searchListItems[position]
        holder.bind(item)
    }

    @SuppressLint("NotifyDataSetChanged")
    fun update(searchListItems: List<SearchListItem>) {
        this.searchListItems.clear()
        this.searchListItems.addAll(searchListItems)
        notifyDataSetChanged()
    }
}