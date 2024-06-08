package nottodo.persistence.rdb.domain.recommend.repository

import nottodo.persistence.rdb.domain.recommend.entity.RecommendMission
import org.springframework.data.jpa.repository.JpaRepository

interface RecommendMissionRepository: JpaRepository<RecommendMission, Long> {
}
