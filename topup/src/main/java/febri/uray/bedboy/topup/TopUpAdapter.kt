package febri.uray.bedboy.topup

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import febri.uray.bedboy.core.domain.model.TransactionItem
import febri.uray.bedboy.core.domain.model.TypeTransaction
import febri.uray.bedboy.topup.databinding.AdapterItemCenterTextBinding
import febri.uray.bedboy.topup.databinding.AdapterItemDividerBinding
import febri.uray.bedboy.topup.databinding.AdapterItemStandardTextBinding

class TopUpAdapter(private val onClick: (TransactionItem) -> Unit) :
    ListAdapter<TransactionItem, RecyclerView.ViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            TypeTransaction.CenterTextType.ordinal -> ItemCenterTextViewHolder(
                AdapterItemCenterTextBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )

            TypeTransaction.DividerType.ordinal -> ItemDividerViewHolder(
                AdapterItemDividerBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )

            else -> ItemStandardTextViewHolder(
                AdapterItemStandardTextBinding.inflate(
                    LayoutInflater.from(
                        parent.context
                    ), parent, false
                )
            )
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (getItem(position).type.ordinal) {
            TypeTransaction.CenterTextType.ordinal -> {
                val mCenterTextHolder = holder as ItemCenterTextViewHolder
                mCenterTextHolder.bind(getItem(position), onClick)
            }

            TypeTransaction.DividerType.ordinal -> {
                val mDividerHolder = holder as ItemDividerViewHolder
                mDividerHolder.bind(getItem(position))
            }

            TypeTransaction.StandardTextOnlyTitleBoldType.ordinal -> {
                val mStandardTextTitleBold = holder as ItemStandardTextViewHolder
                mStandardTextTitleBold.bindOnlyTitleBold(getItem(position))
            }

            TypeTransaction.StandardTextWithBoldValueType.ordinal -> {
                val mStandardTextWithBoldValue = holder as ItemStandardTextViewHolder
                mStandardTextWithBoldValue.bindOnlyValueBold(getItem(position))
            }

            TypeTransaction.StandardTextBoldType.ordinal -> {
                val bothBoldTypeHolder = holder as ItemStandardTextViewHolder
                bothBoldTypeHolder.bindBothBold(getItem(position))
            }

            else -> {
                val mStandardHolder = holder as ItemStandardTextViewHolder
                mStandardHolder.bind(getItem(position), onClick)
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return getItem(position).type.ordinal
    }

    companion object {

        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<TransactionItem>() {
            override fun areItemsTheSame(
                oldItem: TransactionItem,
                newItem: TransactionItem
            ): Boolean {
                return oldItem.title == newItem.title
            }

            override fun areContentsTheSame(
                oldItem: TransactionItem,
                newItem: TransactionItem
            ): Boolean {
                return oldItem == newItem
            }
        }
    }
}