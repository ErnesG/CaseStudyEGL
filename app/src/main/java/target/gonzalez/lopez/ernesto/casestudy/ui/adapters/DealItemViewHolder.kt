package target.gonzalez.lopez.ernesto.casestudy.ui.adapters

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import target.gonzalez.lopez.ernesto.casestudy.databinding.DealListItemBinding
import target.gonzalez.lopez.ernesto.casestudy.model.Product

class DealItemViewHolder (private val binding: DealListItemBinding, private val productDetail: (Int?) -> Unit) : RecyclerView.ViewHolder(binding.root)  {
    fun bind(item: Product) {
        binding.dealListItemTitle.text = item.title
        binding.dealListItemPrice.text = item.salePrice?.displayString ?: item.regularPrice?.displayString
        binding.dealListItemAisle.text = item.aisle
        item.imageUrl?.let { url ->
            Glide.with(binding.root.context)
                 .load(url)
                 .into(binding.dealListItemImageView)
        }

        binding.dealListItem.setOnClickListener {
            productDetail(item.id)
        }

    }
}