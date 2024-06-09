package nottodo.persistence.rdb.domain.mission.repository

import nottodo.persistence.rdb.domain.mission.entity.DailyMission
import org.springframework.data.jpa.repository.JpaRepository

interface DailyMissionRepository: JpaRepository<DailyMission, Long> {
}
