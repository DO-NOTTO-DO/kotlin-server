package nottodo.persistence.rdb.domain.recommend.repository

import nottodo.persistence.rdb.domain.recommend.entity.RecommendAction
import org.springframework.data.jpa.repository.JpaRepository

interface RecommendActionRepository : JpaRepository<RecommendAction, Long> {
    fun findByRecommendMissionId(recommendMissionId: Long): List<RecommendAction>
}
