package nottodo.api.config

import nottodo.commonspring.dto.response.ApiResponseBody
import nottodo.commonspring.dto.response.ResponseUtil
import nottodo.commonspring.exception.CustomNotFoundException
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class ApiExceptionAdvice {

    @ExceptionHandler(CustomNotFoundException::class)
    fun handleCustomNotFoundException(e: CustomNotFoundException): ResponseEntity<ApiResponseBody<String>> {
        return ResponseUtil.notFound(e.message)
    }
}
