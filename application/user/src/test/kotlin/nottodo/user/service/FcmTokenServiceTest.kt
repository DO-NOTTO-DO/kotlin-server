package nottodo.user.service

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import nottodo.common.random.RandomUtil
import nottodo.commonspring.exception.CustomNotFoundException
import nottodo.persistence.rdb.domain.user.repository.FcmTokenRepository
import nottodo.persistence.rdb.domain.user.repository.UserRepository
import nottodo.persistence.rdb.fixture.recommend.SavedFixtureFcmToken
import nottodo.persistence.rdb.fixture.recommend.SavedFixtureUser
import nottodo.user.dto.FcmTokenDto
import nottodo.user.test.UserTestSpec

class FcmTokenServiceTest(
    val sut: FcmTokenService,
    val fcmTokenRepository: FcmTokenRepository,
    val userRepository: UserRepository
) : UserTestSpec({

    describe("findByTokenOrCreate()") {
        context("userId 가 잘못된 경우") {
            val userId = -1L
            val token = RandomUtil.string()
            it("CustomNotFoundException 이 발생한다") {
                shouldThrow<CustomNotFoundException> {
                    sut.findByTokenOrCreate(userId = userId, token = token)
                }
            }
        }

        context("fcmToken 이 등록되어있는 경우") {
            val user = SavedFixtureUser.create(userRepository)
            val fcmToken = SavedFixtureFcmToken.create(repository = fcmTokenRepository, user = user)
            it("해당 fcmToken 을 조회한다") {
                val actual = sut.findByTokenOrCreate(userId = user.id!!, token = fcmToken.token)
                val expected = FcmTokenDto.of(fcmToken = fcmToken, user = user)
                actual.id shouldBe expected.id
                actual.id shouldBe fcmToken.id
            }
        }

        context("fcmToken 이 등록되어있지 않은 경우") {
            val user = SavedFixtureUser.create(userRepository)
            it("fcmToken 을 새로 생성한다") {
                val token = RandomUtil.string()
                val actual = sut.findByTokenOrCreate(userId = user.id!!, token = token)
                fcmTokenRepository.findByToken(token) shouldNotBe null
            }
        }
    }
})
