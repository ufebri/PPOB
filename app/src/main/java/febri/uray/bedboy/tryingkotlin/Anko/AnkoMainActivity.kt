package febri.uray.bedboy.tryingkotlin.Anko

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import febri.uray.bedboy.tryingkotlin.R
import org.jetbrains.anko.*

class AnkoMainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AnkoMainActivity().setContentView(this)
    }

    class AnkoMainActivity : AnkoComponent<febri.uray.bedboy.tryingkotlin.Anko.AnkoMainActivity> {
        override fun createView(ui: AnkoContext<febri.uray.bedboy.tryingkotlin.Anko.AnkoMainActivity>) = with(ui) {
            verticalLayout {

                imageView(R.drawable.ic_launcher_background).lparams(width = wrapContent, height = wrapContent) {
                    padding = dip(20)
                    margin = dip(15)
                }

                val name = editText() {
                    hint = "what is name club is?"
                }

                button("Submit")
            }
        }

    }
}