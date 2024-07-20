package nottodo.persistence.rdb.fixture.mission

import nottodo.persistence.rdb.domain.mission.entity.CompletionStatus
import nottodo.persistence.rdb.domain.mission.entity.DailyMission
import nottodo.persistence.rdb.domain.mission.entity.Mission
import nottodo.persistence.rdb.domain.mission.repository.DailyMissionRepository
import java.time.LocalDate

object SavedFixtureDailyMission {
    fun create(
        repository: DailyMissionRepository,
        mission: Mission,
        date: LocalDate = LocalDate.now(),
        completionStatus: CompletionStatus = CompletionStatus.UNCHECKED
    ): DailyMission {
        val dailyMission = FixtureDailyMission.create(
            mission = mission,
            date = date,
            completionStatus = completionStatus
        )
        return repository.save(dailyMission)
    }
}
