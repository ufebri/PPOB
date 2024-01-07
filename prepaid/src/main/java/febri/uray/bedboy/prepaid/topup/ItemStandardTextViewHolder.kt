package febri.uray.bedboy.prepaid.topup

import android.graphics.Typeface
import androidx.core.view.isGone
import androidx.recyclerview.widget.RecyclerView
import febri.uray.bedboy.core.domain.model.TransactionItem
import febri.uray.bedboy.prepaid.databinding.AdapterItemStandardTextBinding

class ItemStandardTextViewHolder(private val binding: AdapterItemStandardTextBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(mData: TransactionItem, onClick: (TransactionItem) -> Unit) {
        binding.apply {
            tvTitle.text = mData.title
            tvValue.text = mData.value
        }.root.setOnClickListener { onClick(mData) }
    }

    fun bindOnlyTitleBold(mData: TransactionItem) {
        binding.apply {
            tvTitle.apply {
                text = mData.title
                typeface = Typeface.DEFAULT_BOLD
                textSize = 16f
            }
            tvValue.isGone = true
        }
    }

    fun bindOnlyValueBold(mData: TransactionItem) {
        binding.apply {
            tvTitle.text = mData.title
            tvValue.apply {
                text = mData.value
                typeface = Typeface.DEFAULT_BOLD
            }
        }
    }

    fun bindBothBold(mData: TransactionItem) {
        binding.apply {
            tvTitle.apply {
                text = mData.title
                typeface = Typeface.DEFAULT_BOLD
            }

            tvValue.apply {
                text = mData.value
                typeface = Typeface.DEFAULT_BOLD
            }
        }
    }
}