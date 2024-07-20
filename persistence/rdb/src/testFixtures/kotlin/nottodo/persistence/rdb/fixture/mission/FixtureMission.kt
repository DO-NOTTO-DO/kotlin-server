package nottodo.persistence.rdb.fixture.mission

import nottodo.common.random.RandomUtil
import nottodo.persistence.rdb.domain.mission.entity.Mission

object FixtureMission {
    fun create(
        title: String = RandomUtil.string(),
        situation: String = RandomUtil.string(),
        goal: String = RandomUtil.string(),
        userId: Long = RandomUtil.long(),
    ) : Mission {
        return Mission.of(
            title = title,
            situation = situation,
            goal = goal,
            userId = userId
        )
    }
}
