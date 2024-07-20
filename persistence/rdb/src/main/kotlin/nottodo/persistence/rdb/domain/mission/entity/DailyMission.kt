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

    fun isCompleted(): Boolean = completionStatus == CompletionStatus.CHECKED

    fun changeCompletionStatus(completionStatus: CompletionStatus): DailyMission {
        return DailyMission(
            id = this.id,
            mission = this.mission,
            date = this.date,
            completionStatus = completionStatus
        )
    }

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
