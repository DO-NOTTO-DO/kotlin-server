package nottodo.persistence.rdb.domain.recommend.repository

import nottodo.persistence.rdb.domain.recommend.entity.RecommendAction
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface RecommendActionRepository : JpaRepository<RecommendAction, Long> {
    @Query("SELECT recommendAction FROM ")
    fun fetchByRecommendMissionId(recommendMissionId: Long): List<RecommendAction>
}
