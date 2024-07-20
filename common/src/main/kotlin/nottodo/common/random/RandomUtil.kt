package nottodo.common.random

import kotlin.random.Random

object RandomUtil {

    private const val CHAR_POOL: String = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789"

    fun string(length: Int = 10): String {
        return (1..length)
            .map { Random.nextInt(0, CHAR_POOL.length) }
            .map(CHAR_POOL::get)
            .joinToString("")
    }

    fun long(): Long {
        return Random.nextLong()
    }
}
