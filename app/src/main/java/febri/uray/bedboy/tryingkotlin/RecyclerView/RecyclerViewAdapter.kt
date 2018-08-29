package febri.uray.bedboy.tryingkotlin.RecyclerView

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import febri.uray.bedboy.tryingkotlin.R

class RecyclerViewAdapter(private val context: Context, private val items: List<ItemFootballClub>,
                          private val listener: (ItemFootballClub) -> Unit)
    : RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
            ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_football_club, parent, false))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(items[position], listener)
    }

    override fun getItemCount(): Int = items.size

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private val name = view.findViewById<TextView>(R.id.tv_footballClubName)
        private val image = view.findViewById<ImageView>(R.id.img_footballClub)

        fun bindItem(items: ItemFootballClub, listener: (ItemFootballClub) -> Unit) {
            name.text = items.name
            Glide.with(itemView.context).load(items.image).into(image)
            itemView.setOnClickListener { listener(items) }
        }
    }
}