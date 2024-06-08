package nottodo.api.domain.quote.controller

import nottodo.quote.controller.QuoteControllerPath
import nottodo.quote.response.QuoteResponse
import nottodo.quote.service.QuoteService
import nottodo.commonspring.dto.response.ApiResponseBody
import nottodo.commonspring.dto.response.ResponseUtil
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class QuoteController(
    private val quoteService: QuoteService
) {

    @GetMapping(QuoteControllerPath.GET_RANDOM_QUOTE)
    fun getRandomQuote(): ResponseEntity<ApiResponseBody<QuoteResponse>> {
        val data: QuoteResponse = quoteService.getRandomQuote()
        return ResponseUtil.ok(data, "랜덤 격언 조회에 성공했습니다.")
    }
}
