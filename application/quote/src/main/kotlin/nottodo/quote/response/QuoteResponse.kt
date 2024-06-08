package nottodo.quote.response

import nottodo.common.converter.NonNullConverter
import nottodo.persistence.rdb.domain.quote.entity.Quote

data class QuoteResponse(
    val id: Long,
    val description: String,
    val author: String
) {

    companion object {
        fun from(quote: Quote): QuoteResponse {
            return QuoteResponse(
                id = NonNullConverter.convert(quote.id),
                description = quote.description,
                author = quote.author
            )
        }
    }
}
