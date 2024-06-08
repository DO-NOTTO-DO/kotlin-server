package nottodo.quote.service

import nottodo.common.random.RandomPickUtil
import nottodo.persistence.rdb.domain.quote.repository.QuoteRepository
import nottodo.quote.response.QuoteResponse
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class QuoteService(
    private val quoteRepository: QuoteRepository
) {

    @Transactional(readOnly = true)
    fun getRandomQuote(): QuoteResponse {
        val allQuotes = quoteRepository.findAll()
        val pickedQuote = RandomPickUtil.pickOne(allQuotes)
        return QuoteResponse.from(pickedQuote)
    }
}
