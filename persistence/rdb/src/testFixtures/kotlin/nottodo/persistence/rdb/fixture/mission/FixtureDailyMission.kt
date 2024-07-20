package nottodo.persistence.rdb.fixture.mission

import nottodo.persistence.rdb.domain.mission.entity.CompletionStatus
import nottodo.persistence.rdb.domain.mission.entity.DailyMission
import nottodo.persistence.rdb.domain.mission.entity.Mission
import java.time.LocalDate

object FixtureDailyMission {
    fun create(
        mission: Mission,
        date: LocalDate = LocalDate.now(),
        completionStatus: CompletionStatus = CompletionStatus.UNCHECKED
    ) : DailyMission {
        return DailyMission.of(
            mission = mission,
            date = date,
            completionStatus = completionStatus
        )
    }
}
