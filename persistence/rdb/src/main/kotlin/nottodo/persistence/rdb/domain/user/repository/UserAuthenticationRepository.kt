package nottodo.persistence.rdb.domain.user.repository

import nottodo.commonspring.exception.CustomAuthenticationException
import nottodo.persistence.rdb.domain.user.entity.QUser.user
import nottodo.persistence.rdb.domain.user.entity.SocialType
import nottodo.persistence.rdb.domain.user.entity.User
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport
import org.springframework.stereotype.Repository

@Repository
class UserAuthenticationRepository: QuerydslRepositorySupport(User::class.java) {

    fun findByEmailAndSocialType(email: String, socialType: SocialType): User {
        val results = from(user)
            .where(user.email.eq(email), user.socialType.eq(socialType))
            .fetch()
        val authenticatedUser = authenticate(results)
        return authenticatedUser
    }

    private fun authenticate(results: List<User>): User {
        if (results.isEmpty()) {
            throw CustomAuthenticationException("존재하지 않는 유저입니다.")
        }
        if (results.size > 1) {
            throw CustomAuthenticationException("중복 유저입니다. 서버팀에 문의해주세요.")
        }
        return results.first()
    }
}
