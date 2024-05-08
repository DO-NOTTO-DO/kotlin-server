package kr.co.nottodo.persistence.rdb.domain.mission.entity

import jakarta.persistence.*
import kr.co.nottodo.persistence.rdb.domain.base.BaseEntity
import java.time.LocalDate

@Entity
class DailyMission private constructor(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private val id: Long? = null,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mission_id")
    private val mission: Mission,

    @Column
    private val date: LocalDate,

    @Column
    @Enumerated(EnumType.STRING)
    private val completionStatus: CompletionStatus
) : BaseEntity() {
}
