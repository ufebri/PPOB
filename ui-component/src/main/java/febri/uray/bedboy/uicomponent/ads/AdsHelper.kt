package febri.uray.bedboy.uicomponent.ads

import android.content.Context
import android.os.Build
import android.util.DisplayMetrics
import android.view.View
import android.view.WindowManager
import com.google.android.gms.ads.AdSize

object AdsHelper {

    fun calculateAdSize(context: Context, adViewContainer: View): AdSize {
        val displayMetrics = DisplayMetrics()

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            // Use new API for devices with API level 30 and higher
            context.display?.getRealMetrics(displayMetrics)
        } else {
            // Use the deprecated method for devices with API level lower than 30
            @Suppress("DEPRECATION")
            (context.getSystemService(Context.WINDOW_SERVICE) as WindowManager)
                .defaultDisplay.getMetrics(displayMetrics)
        }

        var adWidthPixels = adViewContainer.width.toFloat()

        // If the ad hasn't been laid out, default to the full screen width.
        if (adWidthPixels == 0f) {
            adWidthPixels = displayMetrics.widthPixels.toFloat()
        }

        val density = context.resources.displayMetrics.density
        val adWidth = (adWidthPixels / density).toInt()

        return AdSize.getCurrentOrientationAnchoredAdaptiveBannerAdSize(context, adWidth)
    }
}