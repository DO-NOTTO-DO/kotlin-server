package nottodo.persistence.rdb.domain.quote.repository

import nottodo.persistence.rdb.domain.quote.entity.Quote
import org.springframework.data.jpa.repository.JpaRepository

interface QuoteRepository : JpaRepository<Quote, Long> {
}
