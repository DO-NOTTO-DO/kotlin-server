package kr.co.nottodo.commonspring.dto.response

import org.springframework.http.ResponseEntity
import java.net.URI

object ResponseUtil {

    fun <T> ok(data: T, message: String): ResponseEntity<ApiResponseBody<T>> {
        val responseBody: ApiResponseBody<T> = ApiResponseBody.success(200, message, data)
        return ResponseEntity.ok(responseBody)
    }

    fun <T> created(data: T, message: String, location: String): ResponseEntity<ApiResponseBody<T>> {
        val responseBody: ApiResponseBody<T> = ApiResponseBody.success(201, message, data)
        return ResponseEntity.created(URI(location)).body(responseBody)
    }

    fun <T> badRequest(data: T, message: String): ResponseEntity<ApiResponseBody<T>> {
        val responseBody: ApiResponseBody<T> = ApiResponseBody.error(400, message)
        return ResponseEntity.badRequest().body(responseBody)
    }

    fun <T> notFound(data: T, message: String): ResponseEntity<ApiResponseBody<T>> {
        val responseBody: ApiResponseBody<T> = ApiResponseBody.error(404, message)
        return ResponseEntity.status(404).body(responseBody)
    }

    fun <T> internalServerError(data: T, message: String): ResponseEntity<ApiResponseBody<T>> {
        val responseBody: ApiResponseBody<T> = ApiResponseBody.error(500, message)
        return ResponseEntity.internalServerError().body(responseBody)
    }
}
