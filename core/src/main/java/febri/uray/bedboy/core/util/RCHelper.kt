package febri.uray.bedboy.core.util

object RCHelper {

    private const val SUCCESS_TRANSACTION = "00"
    private const val PROCESS_TRANSACTION = "39"
    private const val UNDEFINED_RESPONSE_CODE = "201"

    fun getAnimationStatus(rc: String?): String {
        return when (rc) {
            SUCCESS_TRANSACTION -> "https://lottie.host/de9557f0-cb68-4996-87b1-13bba040094f/sfITeXnx6i.lottie"
            PROCESS_TRANSACTION -> "https://lottie.host/cdbd23df-6f5f-4266-8428-afe7f4fed14e/SwIz0LBNyF.lottie"
            UNDEFINED_RESPONSE_CODE -> "https://lottie.host/cdbd23df-6f5f-4266-8428-afe7f4fed14e/SwIz0LBNyF.lottie"
            else -> "https://lottie.host/15d35147-56c0-4fca-ab59-e704241e33c3/QplLLYZqWl.lottie"
        }
    }
}