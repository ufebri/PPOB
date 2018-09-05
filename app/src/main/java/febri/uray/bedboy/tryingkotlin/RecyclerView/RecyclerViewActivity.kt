package febri.uray.bedboy.tryingkotlin.RecyclerView

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.widget.Toast
import febri.uray.bedboy.tryingkotlin.R
import kotlinx.android.synthetic.main.activity_recycler_view.*

class RecyclerViewActivity : AppCompatActivity() {

    private var items : MutableList<ItemFootballClub> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recycler_view)

        rec_footballClub.layoutManager = LinearLayoutManager(this)
        rec_footballClub.adapter = RecyclerViewAdapter(this, items) {
            val toast = Toast.makeText(applicationContext, it.name, Toast.LENGTH_SHORT).show()
        }

        initData()
    }

    private fun initData() {
        val name = resources.getStringArray(R.array.clubName)
        val image = resources.obtainTypedArray(R.array.imageClub)
        items.clear()
        for (i in name.indices) {
            items.add(ItemFootballClub(name[i],
                    image.getResourceId(i, 0)))
        }

        image.recycle()
    }
}
