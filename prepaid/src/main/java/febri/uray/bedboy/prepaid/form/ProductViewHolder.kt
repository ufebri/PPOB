package febri.uray.bedboy.prepaid

import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import febri.uray.bedboy.core.domain.model.ProductList
import febri.uray.bedboy.core.util.TextHelper
import febri.uray.bedboy.prepaid.databinding.ItemProductBinding

class ProductViewHolder(private val binding: ItemProductBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(mData: ProductList, onClick: (ProductList) -> Unit) {
        binding.apply {
            if (mData.iconUrl.isNullOrEmpty()) {
                ivIcon.isGone = true
            } else {
                Glide.with(root.context).load(mData.iconUrl).into(ivIcon)
                ivIcon.isVisible
            }

            tvTitle.text = mData.productNominal
            tvPrice.text = String.format(
                "%s / %s Hari",
                TextHelper.formatRupiah(mData.productPrice.toString()),
                mData.activePeriod
            )

            root.setOnClickListener { onClick(mData) }
        }
    }
}