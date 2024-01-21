package febri.uray.bedboy.uicomponent.chiplayout

import android.content.Context
import android.graphics.Color
import android.view.View
import androidx.core.content.ContextCompat
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import febri.uray.bedboy.uicomponent.R

fun ChipGroup.addChip(context: Context, label: String, isChecked: Boolean) {

    Chip(context).apply {

        id = View.generateViewId()

        text = label

        isClickable = true

        isCheckable = true

        setChipSpacingHorizontalResource(R.dimen.dimens_16dp)

        isCheckedIconVisible = false

        isFocusable = true

        if (isChecked) {
            chipBackgroundColor = ContextCompat.getColorStateList(
                context, R.color.colorPrimary
            )
            setTextColor(Color.WHITE)
        }

        addView(this)
    }

}