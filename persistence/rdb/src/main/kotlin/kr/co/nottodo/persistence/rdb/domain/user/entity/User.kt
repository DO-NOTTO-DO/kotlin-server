package kr.co.nottodo.persistence.rdb.domain.user.entity

import jakarta.persistence.*
import kr.co.nottodo.persistence.rdb.domain.base.BaseEntity

@Entity(name = "users")
class User private constructor(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @Column
    private val email: String,

    @Column(unique = true)
    private val socialId: String,

    @Column
    @Enumerated(EnumType.STRING)
    private val socialType: SocialType,

    @Column
    private val name: String?,

    @Column
    private val image: String,
): BaseEntity() {
}
