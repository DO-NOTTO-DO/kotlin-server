package nottodo.persistence.rdb.domain.mission.repository

import nottodo.persistence.rdb.domain.mission.entity.Action
import org.springframework.data.jpa.repository.JpaRepository

interface ActionRepository: JpaRepository<Action, Long> {
}
