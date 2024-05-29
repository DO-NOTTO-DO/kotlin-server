package nottodo.persistence.rdb.domain.recommend.entity

import jakarta.persistence.CascadeType
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.OneToMany

@Entity
class RecommendMission private constructor(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private val id: Long? = null,

    @Column
    private val title: String,

    @Column
    private val situation: String,

    @Column
    private val description: String,

    @Column
    private val image: String,
) {

    @OneToMany(mappedBy = "recommendMission", cascade = [CascadeType.PERSIST])
    private val recommendActions: MutableList<RecommendAction> = mutableListOf()

}
