package febri.uray.bedboy.uicomponent.chiplayout

import android.content.Context
import android.view.View
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import febri.uray.bedboy.uicomponent.R

fun ChipGroup.addChip(context: Context, label: String, isCheckAble: Boolean) {

    Chip(context).apply {

        id = View.generateViewId()

        text = label

        isClickable = true

        isCheckable = isCheckAble

        setChipSpacingHorizontalResource(R.dimen.dimens_16dp)

        isCheckedIconVisible = false

        isFocusable = true

        addView(this)
    }

}