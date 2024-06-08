package nottodo.persistence.rdb.domain.recommend.entity

import jakarta.persistence.*

@Entity
class RecommendMission private constructor(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @Column
    val title: String,

    @Column
    val situation: String,

    @Column
    val description: String,

    @Column
    val image: String,
) {
    companion object {
        fun of(
            title: String,
            situation: String,
            description: String,
            image: String,
        ): RecommendMission {
            return RecommendMission(
                id = null,
                title = title,
                situation = situation,
                description = description,
                image = image
            )
        }
    }
}
