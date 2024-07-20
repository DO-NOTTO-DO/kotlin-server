package nottodo.persistence.rdb.fixture.mission

import nottodo.common.random.RandomUtil
import nottodo.persistence.rdb.domain.mission.entity.Mission
import nottodo.persistence.rdb.domain.mission.repository.MissionRepository

object SavedFixtureMission {
    fun create(
        repository: MissionRepository,
        title: String = RandomUtil.string(),
        situation: String = RandomUtil.string(),
        goal: String = RandomUtil.string(),
        userId: Long = RandomUtil.long(),
    ): Mission {
        val mission = FixtureMission.create(title = title, situation = situation, goal = goal, userId = userId)
        return repository.save(mission)
    }
}
