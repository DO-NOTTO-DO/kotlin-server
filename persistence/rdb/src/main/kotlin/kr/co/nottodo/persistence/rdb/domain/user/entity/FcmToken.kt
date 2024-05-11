package kr.co.nottodo.persistence.rdb.domain.user.entity

import jakarta.persistence.*
import kr.co.nottodo.persistence.rdb.domain.base.BaseEntity

@Entity
class FcmToken private constructor(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private val id: Long? = null,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private val user: User,

    @Column
    private val token: String
) : BaseEntity() {
}
