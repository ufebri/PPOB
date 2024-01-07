package febri.uray.bedboy.core.domain.model

import android.os.Parcelable
import febri.uray.bedboy.core.data.source.remote.response.ResponseData
import febri.uray.bedboy.core.util.DateHelper
import febri.uray.bedboy.core.util.TextHelper
import kotlinx.parcelize.Parcelize

@Parcelize
data class TransactionItem(
    var title: String? = null,
    var value: String? = null,
    var type: TypeTransaction,
    var additionalStyle: String? = null
) : Parcelable


enum class TypeTransaction {
    StatusType,
    CenterTextType,
    StandardTextType,
    StandardTextWithBoldValueType,
    StandardTextOnlyTitleBoldType,
    StandardTextBoldType,
    DividerType,
    BackgroundColorType,
    CenterCaptionType
}

fun generateTransaction(mData: ResponseData): List<TransactionItem> =
    listOf(TransactionItem(title = "Total Transaksi", value = TextHelper.formatRupiah(mData.price.toString()), TypeTransaction.CenterTextType),
        TransactionItem(title = "Tanggal Transaksi", value = DateHelper.getCurrentFormattedDate(), TypeTransaction.StandardTextType),
        TransactionItem(title = "Nomor Referensi", value = mData.refId, TypeTransaction.StandardTextType),
        TransactionItem(title = null, value = null, TypeTransaction.DividerType),
        TransactionItem(title = "Detail Transaksi", value = null, TypeTransaction.StandardTextOnlyTitleBoldType),
        TransactionItem(title = "Jenis Transaksi", value = null, TypeTransaction.StandardTextWithBoldValueType),
        TransactionItem(title = "Status Transaksi", value = mData.message, TypeTransaction.StandardTextWithBoldValueType),
        TransactionItem(title = null, value = null, TypeTransaction.DividerType),
        TransactionItem(title = "Nominal Transaksi", value = null, TypeTransaction.StandardTextOnlyTitleBoldType),
        TransactionItem(title = "Nominal Transaksi",value = TextHelper.formatRupiah(mData.price.toString()), TypeTransaction.StandardTextType),
        TransactionItem(title = "Biaya admin", value = "" ?: "", TypeTransaction.StandardTextType),
        TransactionItem(title = "Total Transaksi", value = TextHelper.formatRupiah(mData.price.toString()), TypeTransaction.StandardTextBoldType)
    )
