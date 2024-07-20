package nottodo.persistence.rdb.domain.mission.entity

import jakarta.persistence.*
import nottodo.persistence.rdb.domain.base.BaseEntity
import nottodo.persistence.rdb.domain.user.entity.User

@Entity
class Mission private constructor(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @Column
    var title: String,

    @Column
    var situation: String,

    @Column
    var goal: String,

    @Column
    val userId: Long
) : BaseEntity() {

    companion object {
        fun of(title: String, situation: String, goal: String, userId: Long): Mission {
            return Mission(
                id = null,
                title = title,
                situation = situation,
                goal = goal,
                userId = userId
            )
        }
    }
}
