package febri.uray.bedboy.prepaid.form

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import febri.uray.bedboy.core.domain.model.ProductList
import febri.uray.bedboy.prepaid.databinding.ItemProductBinding

class ProductAdapter(private val onClick: (ProductList) -> Unit) :
    ListAdapter<ProductList, RecyclerView.ViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ProductViewHolder(
            ItemProductBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val mHolder = holder as ProductViewHolder
        mHolder.bind(getItem(position), onClick)
    }

    companion object {

        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<ProductList>() {
            override fun areItemsTheSame(
                oldItem: ProductList,
                newItem: ProductList
            ): Boolean {
                return oldItem.productCode == newItem.productCode
            }

            override fun areContentsTheSame(
                oldItem: ProductList,
                newItem: ProductList
            ): Boolean {
                return oldItem == newItem
            }
        }
    }
}