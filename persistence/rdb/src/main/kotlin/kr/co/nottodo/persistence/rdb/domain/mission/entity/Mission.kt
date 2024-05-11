package kr.co.nottodo.persistence.rdb.domain.mission.entity

import jakarta.persistence.*
import kr.co.nottodo.persistence.rdb.domain.base.BaseEntity
import kr.co.nottodo.persistence.rdb.domain.user.entity.User

@Entity
class Mission private constructor(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private val id: Long? = null,

    @Column
    private var title: String,

    @Column
    private var situation: String,

    @Column
    private var goal: String,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    val user: User
) : BaseEntity() {

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "mission", cascade = [CascadeType.PERSIST])
    val dailyMissions: List<DailyMission> = mutableListOf()

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "action", cascade = [CascadeType.PERSIST])
    val actions: List<Action> = mutableListOf()
}