package febri.uray.bedboy.ppob

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import dagger.hilt.android.AndroidEntryPoint
import febri.uray.bedboy.core.data.Resource
import febri.uray.bedboy.ppob.databinding.ActivityMainBinding

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val balanceViewModel: BalanceViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        balanceViewModel.balance.observe(this) { mData ->
            if (mData.data != null) {
                when (mData) {
                    is Resource.Loading -> binding.tvBalance.text = "loading"
                    is Resource.Success -> binding.tvBalance.text = mData.data.let {
                        it?.balance
                            ?: "kosong"
                    }

                    is Resource.Error -> binding.tvBalance.text = mData.message
                }
            } else {
                binding.tvBalance.text = mData.message
            }
        }
    }
}