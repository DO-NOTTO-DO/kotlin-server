package nottodo.persistence.rdb.domain.mission.repository

import nottodo.persistence.rdb.domain.mission.entity.Mission
import nottodo.persistence.rdb.domain.mission.entity.QMission.mission
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport
import org.springframework.stereotype.Repository

@Repository
class MissionQueryRepository: QuerydslRepositorySupport(Mission::class.java) {

    fun findTitleByUserIdLimit(userId: Long, limit: Long): List<Mission> {
        return from(mission)
            .where(mission.userId.eq(userId))
            .orderBy(mission.createdAt.desc())
            .limit(limit)
            .fetch()
    }
}
