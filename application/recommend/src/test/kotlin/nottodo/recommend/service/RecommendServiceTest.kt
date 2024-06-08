package nottodo.recommend.service

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.matchers.collections.shouldContainAll
import io.kotest.matchers.shouldBe
import nottodo.commonspring.exception.CustomNotFoundException
import nottodo.persistence.rdb.domain.recommend.repository.RecommendActionRepository
import nottodo.persistence.rdb.domain.recommend.repository.RecommendMissionRepository
import nottodo.persistence.rdb.fixture.recommend.SavedFixtureRecommendAction
import nottodo.persistence.rdb.fixture.recommend.SavedFixtureRecommendMission
import nottodo.recommend.response.RecommendActionsOfMissionResponse
import nottodo.recommend.test.RecommendTestSpec

class RecommendServiceTest(
    val sut: RecommendService,
    val recommendMissionRepository: RecommendMissionRepository,
    val recommendActionRepository: RecommendActionRepository
) : RecommendTestSpec({

    describe("getRecommendActionsByRecommendMission()") {
        context("RecommendMissionId 에 해당하는 RecommendAction 목록을 조회하는 경우") {
            val recommendMission = SavedFixtureRecommendMission.create(recommendMissionRepository)
            val recommendAction1 = SavedFixtureRecommendAction.create(recommendActionRepository, recommendMission)
            val recommendAction2 = SavedFixtureRecommendAction.create(recommendActionRepository, recommendMission)

            it("결과값 조회에 성공한다") {
                val actual = sut.getRecommendActionsByRecommendMission(recommendMission.id!!)
                val expected = RecommendActionsOfMissionResponse.of(recommendMission, listOf(recommendAction1, recommendAction2))
                actual.id shouldBe expected.id
                actual.recommendActions.map { it.name } shouldContainAll expected.recommendActions.map { it.name }
            }
        }

        context("RecommendMissionId 가 잘못된 경우") {
            val recommendMissionId = -1L
            it("CustomNotFoundException 이 발생한다") {
                shouldThrow<CustomNotFoundException> {
                    sut.getRecommendActionsByRecommendMission(recommendMissionId)
                }
            }
        }
    }
})
