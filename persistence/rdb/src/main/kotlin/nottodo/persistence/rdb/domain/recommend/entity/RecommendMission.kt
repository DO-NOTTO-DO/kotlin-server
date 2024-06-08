package nottodo.persistence.rdb.domain.recommend.entity

import jakarta.persistence.CascadeType
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id

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
}
