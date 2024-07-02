package nottodo.persistence.rdb.domain.user.entity

import jakarta.persistence.*
import nottodo.persistence.rdb.domain.base.BaseEntity

@Entity(name = "users")
class User private constructor(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @Column
    val email: String,

    @Column(unique = true)
    val socialId: String,

    @Column
    @Enumerated(EnumType.STRING)
    val socialType: SocialType,

    @Column
    val name: String?,

    @Column
    val image: String,

    @Column
    @Enumerated(EnumType.STRING)
    val role: UserRole
) : BaseEntity() {

    companion object {
        fun of(email: String, socialId: String, socialType: SocialType, name: String?, image: String): User {
            return User(
                id = null,
                email = email,
                socialId = socialId,
                socialType = socialType,
                name = name,
                image = image,
                role = UserRole.NORMAL
            )
        }
    }
}
