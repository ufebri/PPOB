package febri.uray.bedboy.prepaid.form

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
            tvPrice.text = getTextInfo(mData)

            root.setOnClickListener { onClick(mData) }
        }
    }


    private fun getTextInfo(mData: ProductList): String {
        return when (mData.activePeriod) {
            "0" -> String.format("%s", TextHelper.formatRupiah(mData.productPrice.toString()))
            else -> String.format("%s / %s Days", TextHelper.formatRupiah(mData.productPrice.toString()), mData.activePeriod)
        }.also {
            if (it.contains("INFO"))
                String.format("%s", TextHelper.formatRupiah(mData.productPrice.toString()))
        }
    }
}