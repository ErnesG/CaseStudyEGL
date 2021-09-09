package target.gonzalez.lopez.ernesto.casestudy.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import target.gonzalez.lopez.ernesto.casestudy.databinding.DealListItemBinding
import target.gonzalez.lopez.ernesto.casestudy.model.Product

class DealItemAdapter(private val deals: List<Product>, private val onDealClicked: (Int?) -> Unit): RecyclerView.Adapter<DealItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DealItemViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = DealListItemBinding.inflate(inflater, parent, false)
        return DealItemViewHolder(view, onDealClicked)
    }

    override fun onBindViewHolder(holder: DealItemViewHolder, position: Int) {
        val deal = deals[position]
        holder.bind(deal)
    }

    override fun getItemCount(): Int {
        return deals.size
    }

}