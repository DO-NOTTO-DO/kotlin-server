package kr.co.nottodo.common.random

import kotlin.random.Random

object RandomPickUtil {
    fun <T> pickOne(data: List<T>): T {
        if (data.isEmpty()) {
            throw IllegalArgumentException("리스트가 비어있습니다.")
        }
        val randomIndex: Int = Random.nextInt(data.size)
        return data[randomIndex]
    }
}
