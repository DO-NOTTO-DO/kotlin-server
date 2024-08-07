package nottodo.persistence.rdb.domain.mission.repository

import nottodo.persistence.rdb.domain.mission.entity.DailyMission
import nottodo.persistence.rdb.domain.mission.entity.Mission
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import java.time.LocalDate

interface DailyMissionRepository : JpaRepository<DailyMission, Long> {
    @Query(
        """
        select dm from DailyMission dm join dm.mission m where m.userId = :userId and dm.date in :dates
    """
    )
    fun findByUserIdAndDates(userId: Long, dates: List<LocalDate>): List<DailyMission>

    fun findAllByMissionIn(missions: List<Mission>): List<DailyMission>
    fun findAllByMission(mission: Mission): List<DailyMission>
}
