package nottodo.persistence.rdb.domain.recommend.entity

import jakarta.persistence.*

@Entity
class RecommendAction private constructor(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "recommend_mission_id")
    val recommendMission: RecommendMission,

    @Column(unique = true)
    val name: String,

    @Column
    val description: String,
) {
}
