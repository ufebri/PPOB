package febri.uray.bedboy.tryingkotlin

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import febri.uray.bedboy.tryingkotlin.RecyclerView.RecyclerViewActivity

class MainActivity : AppCompatActivity() {

    private lateinit var btn_recycler : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn_recycler = findViewById(R.id.btn_recyclerViewSample)
        btn_recycler.setOnClickListener {
            startActivity(Intent(this, RecyclerViewActivity::class.java))
        }
    }
}
