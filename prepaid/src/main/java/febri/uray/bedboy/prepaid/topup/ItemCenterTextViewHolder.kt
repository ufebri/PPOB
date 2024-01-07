package febri.uray.bedboy.prepaid.topup

import androidx.recyclerview.widget.RecyclerView
import febri.uray.bedboy.core.domain.model.TransactionItem
import febri.uray.bedboy.prepaid.databinding.AdapterItemCenterTextBinding

class ItemCenterTextViewHolder(private val binding: AdapterItemCenterTextBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(mData: TransactionItem, onClick: (TransactionItem) -> Unit) {
        binding.apply {
            tvTitle.text = mData.title
            tvValue.text = mData.value
        }.root.setOnClickListener { onClick(mData) }
    }
}