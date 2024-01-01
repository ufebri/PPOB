package febri.uray.bedboy.ppob.presentations.home.bicara

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import febri.uray.bedboy.core.domain.model.ProductList
import febri.uray.bedboy.ppob.databinding.ItemPackageDataBinding

class PackageAdapter(private val onClick: (ProductList) -> Unit) :
    ListAdapter<ProductList, RecyclerView.ViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return PackageViewHolder(
            ItemPackageDataBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val mHolder = holder as PackageViewHolder
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