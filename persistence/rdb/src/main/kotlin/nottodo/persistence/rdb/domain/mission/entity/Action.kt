package nottodo.persistence.rdb.domain.mission.entity

import jakarta.persistence.*
import nottodo.persistence.rdb.domain.base.BaseEntity

@Entity
class Action private constructor(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @Column
    val name: String,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mission_id")
    val mission: Mission
) : BaseEntity() {

    companion object {
        fun of(name: String, mission: Mission): Action {
            return Action(
                name = name,
                mission = mission
            )
        }
    }
}
