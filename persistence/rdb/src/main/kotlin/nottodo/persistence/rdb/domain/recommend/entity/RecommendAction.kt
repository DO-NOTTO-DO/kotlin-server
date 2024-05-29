package nottodo.persistence.rdb.domain.recommend.entity

import jakarta.persistence.*

@Entity
class RecommendAction private constructor(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private val id: Long? = null,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "recommend_mission_id")
    private val recommendMission: RecommendMission,

    @Column(unique = true)
    private var name: String,

    @Column
    private val description: String,
) {
}
