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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    val user: User
) : BaseEntity() {

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "mission", cascade = [CascadeType.PERSIST])
    val dailyMissions: MutableList<DailyMission> = mutableListOf()

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "mission", cascade = [CascadeType.PERSIST])
    val actions: MutableList<Action> = mutableListOf()
}
