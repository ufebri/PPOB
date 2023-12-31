package febri.uray.bedboy.ppob.presentations.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import febri.uray.bedboy.core.domain.model.MenuList
import febri.uray.bedboy.ppob.databinding.ItemMenuBinding

class MenuAdapter(
    private val onClick: (MenuList) -> Unit
) : ListAdapter<MenuList, MenuViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MenuViewHolder {
        return MenuViewHolder(
            ItemMenuBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: MenuViewHolder, position: Int) {
        holder.bind(getItem(position), onClick)
    }

    companion object {

        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<MenuList>() {
            override fun areItemsTheSame(
                oldItem: MenuList,
                newItem: MenuList
            ): Boolean {
                return oldItem.idMenu == newItem.idMenu
            }

            override fun areContentsTheSame(
                oldItem: MenuList,
                newItem: MenuList
            ): Boolean {
                return oldItem == newItem
            }
        }
    }
}