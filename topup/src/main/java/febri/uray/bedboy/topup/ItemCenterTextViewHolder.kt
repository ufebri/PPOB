package febri.uray.bedboy.topup

import androidx.recyclerview.widget.RecyclerView
import febri.uray.bedboy.core.domain.model.TransactionItem
import febri.uray.bedboy.topup.databinding.AdapterItemCenterTextBinding

class ItemCenterTextViewHolder(private val binding: AdapterItemCenterTextBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(mData: TransactionItem, onClick: (TransactionItem) -> Unit) {
        binding.apply {
            tvTitle.text = mData.title
            tvValue.text = mData.value
        }.root.setOnClickListener { onClick(mData) }
    }
}