package febri.uray.bedboy.tryingkotlin

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Button
import febri.uray.bedboy.tryingkotlin.Anko.AnkoMainActivity
import febri.uray.bedboy.tryingkotlin.RecyclerView.RecyclerViewActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var btn_recycler: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn_recycler = findViewById(R.id.btn_recyclerViewSample)
        btn_recycler.setOnClickListener {
            startActivity(Intent(this, RecyclerViewActivity::class.java))
        }

        btn_ankoMain.setOnClickListener {
            startActivity(Intent(this, AnkoMainActivity::class.java))
        }
    }
}
