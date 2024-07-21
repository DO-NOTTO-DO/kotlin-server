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
    val title: String,

    @Column
    val situation: String,

    @Column
    val goal: String?,

    @Column
    val userId: Long
) : BaseEntity() {

    companion object {
        fun of(id: Long? = null, title: String, situation: String, goal: String?, userId: Long): Mission {
            return Mission(
                id = id,
                title = title,
                situation = situation,
                goal = goal,
                userId = userId
            )
        }
    }
}
