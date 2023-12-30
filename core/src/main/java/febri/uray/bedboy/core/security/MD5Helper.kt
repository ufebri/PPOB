package febri.uray.bedboy.core.security

import java.security.MessageDigest

object MD5Helper {

    fun calculateMD5(input: String): String {
        val md = MessageDigest.getInstance("MD5")
        val byteArray = md.digest(input.toByteArray())

        val result = StringBuilder()

        for (byte in byteArray) {
            result.append(String.format("%02x", byte))
        }

        return result.toString()
    }

}