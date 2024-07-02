package nottodo.persistence.rdb.fixture.recommend

import nottodo.persistence.rdb.domain.user.entity.User
import nottodo.persistence.rdb.domain.user.repository.UserRepository

object SavedFixtureUser {
    fun create(repository: UserRepository): User {
        val user =  FixtureUser.create()
        return repository.save(user)
    }
}
