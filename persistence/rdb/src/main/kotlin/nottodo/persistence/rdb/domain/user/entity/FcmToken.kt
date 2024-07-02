package nottodo.persistence.rdb.domain.user.entity

import jakarta.persistence.*
import nottodo.persistence.rdb.domain.base.BaseEntity

@Entity
class FcmToken private constructor(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    val user: User,

    @Column
    val token: String
) : BaseEntity() {

    companion object {
        fun of(user: User, token: String): FcmToken {
            return FcmToken(null, user, token)
        }
    }
}
