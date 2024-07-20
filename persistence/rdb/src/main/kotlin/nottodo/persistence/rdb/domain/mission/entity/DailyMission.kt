package nottodo.persistence.rdb.domain.mission.entity

import jakarta.persistence.*
import nottodo.persistence.rdb.domain.base.BaseEntity
import java.time.LocalDate

@Entity
class DailyMission private constructor(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mission_id")
    val mission: Mission,

    @Column
    val date: LocalDate,

    @Column
    @Enumerated(EnumType.STRING)
    val completionStatus: CompletionStatus
) : BaseEntity() {

    companion object {
        fun of(
            mission: Mission,
            date: LocalDate,
            completionStatus: CompletionStatus = CompletionStatus.UNCHECKED
        ): DailyMission {
            return DailyMission(
                id = null,
                mission = mission,
                date = date,
                completionStatus = completionStatus
            )
        }
    }
}
