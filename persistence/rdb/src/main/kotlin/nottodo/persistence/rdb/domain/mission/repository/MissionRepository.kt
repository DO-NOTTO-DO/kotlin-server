package nottodo.persistence.rdb.domain.mission.repository

import nottodo.persistence.rdb.domain.mission.entity.Mission
import org.springframework.data.jpa.repository.JpaRepository

interface MissionRepository: JpaRepository<Mission, Long> {
    fun findByUserIdAndTitleAndSituation(userId: Long, title: String, situation: String): List<Mission>
}
