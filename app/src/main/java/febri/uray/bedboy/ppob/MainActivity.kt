package febri.uray.bedboy.ppob

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.isVisible
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import dagger.hilt.android.AndroidEntryPoint
import febri.uray.bedboy.core.util.SharedPreferencesHelper
import febri.uray.bedboy.ppob.databinding.ActivityMainBinding

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var sharedPreferencesHelper: SharedPreferencesHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        sharedPreferencesHelper = SharedPreferencesHelper(this)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController

        if (!sharedPreferencesHelper.getString("username").isNullOrEmpty() &&
            !sharedPreferencesHelper.getString("apikey").isNullOrEmpty()
        ) {
           navController.navigate(R.id.navigation_home)

            val appBarConfiguration = AppBarConfiguration(
                setOf(
                    R.id.navigation_home, R.id.navigation_history, R.id.navigation_profile
                )
            )
            setupActionBarWithNavController(navController, appBarConfiguration)
            binding.navViewHome.apply {
                setupWithNavController(navController)
                isVisible = true
            }
        } else {
            navController.navigate(R.id.authmodeFragment)
        }
    }
}