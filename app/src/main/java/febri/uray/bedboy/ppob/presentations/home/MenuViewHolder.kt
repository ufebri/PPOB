package febri.uray.bedboy.ppob.presentations.home

import android.content.Context
import android.graphics.drawable.Drawable
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import febri.uray.bedboy.core.domain.model.MenuList
import febri.uray.bedboy.ppob.databinding.ItemMenuBinding
import febri.uray.bedboy.uicomponent.R

class MenuViewHolder(
    private val binding: ItemMenuBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(mData: MenuList, onClick: (MenuList) -> Unit) {
        binding.let {
            it.ivIcon.setImageDrawable(
                getDrawableFromResourceId(
                    it.root.context,
                    "ic_${mData.idMenu}"
                )
            )

            it.tvMenu.text = mData.nameMenu

            itemView.setOnClickListener { onClick(mData) }
        }
    }

    private fun getDrawableFromResourceId(context: Context, resourceIdString: String): Drawable? {
        val resourceId =
            context.resources.getIdentifier(resourceIdString, "drawable", context.packageName)
        return if (resourceId != 0) {
            ContextCompat.getDrawable(context, resourceId)
        } else {
            ContextCompat.getDrawable(context, R.drawable.baseline_error_outline_24)
        }
    }
}