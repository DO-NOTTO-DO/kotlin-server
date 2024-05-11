package kr.co.nottodo.persistence.rdb.domain.quote.repository

import kr.co.nottodo.persistence.rdb.domain.quote.entity.Quote
import org.springframework.data.jpa.repository.JpaRepository

interface quoteRepository : JpaRepository<Quote, Long> {
}
