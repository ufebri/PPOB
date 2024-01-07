package febri.uray.bedboy.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ResultTransaction(
    var statusTransaction: TransactionItem,
    var listTransaction: List<TransactionItem>
) : Parcelable
