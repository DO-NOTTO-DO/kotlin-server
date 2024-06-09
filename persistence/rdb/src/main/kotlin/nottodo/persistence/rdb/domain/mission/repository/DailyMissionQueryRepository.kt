package nottodo.persistence.rdb.domain.mission.repository

import nottodo.persistence.rdb.domain.mission.entity.DailyMission
import nottodo.persistence.rdb.domain.mission.entity.QDailyMission.dailyMission
import nottodo.persistence.rdb.domain.mission.entity.QMission.mission
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport
import org.springframework.stereotype.Repository
import java.time.LocalDate

@Repository
class DailyMissionQueryRepository: QuerydslRepositorySupport(DailyMission::class.java) {

    fun fetchByDateAndUserId(date: LocalDate, userId: Long): List<DailyMission> {
        return from(dailyMission)
            .leftJoin(dailyMission.mission, mission).fetchJoin()
            .where(dailyMission.date.eq(date), dailyMission.mission.userId.eq(userId))
            .orderBy(dailyMission.createdAt.desc())
            .fetch()
    }
}
