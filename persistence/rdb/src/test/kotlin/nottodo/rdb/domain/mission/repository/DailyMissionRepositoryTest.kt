package nottodo.rdb.domain.mission.repository

import io.kotest.matchers.collections.shouldContainAll
import io.kotest.matchers.collections.shouldNotContainAnyOf
import io.kotest.matchers.shouldBe
import nottodo.persistence.rdb.domain.mission.repository.DailyMissionRepository
import nottodo.persistence.rdb.domain.mission.repository.MissionRepository
import nottodo.persistence.rdb.domain.user.repository.UserRepository
import nottodo.persistence.rdb.fixture.mission.SavedFixtureDailyMission
import nottodo.persistence.rdb.fixture.mission.SavedFixtureMission
import nottodo.persistence.rdb.fixture.user.SavedFixtureUser
import nottodo.rdb.test.RdbTestSpec
import java.time.LocalDate


class DailyMissionRepositoryTest(
    val sut: DailyMissionRepository,
    val missionRepository: MissionRepository,
    val userRepository: UserRepository
) : RdbTestSpec({

    describe("findByUserIdAndDates") {
        /**
         * user1 : 검색 조건, mission1 : user1의 미션
         * user2 : 검색 조건 X, mission2 : user2의 미션
         * SearchedDates : 검색 조건, nonSearchedDates : 검색 조건 X
         */
        val user1 = SavedFixtureUser.create(userRepository)
        val mission1 = SavedFixtureMission.create(repository = missionRepository, userId = user1.id!!)
        val user2 = SavedFixtureUser.create(userRepository)
        val mission2 = SavedFixtureMission.create(repository = missionRepository, userId = user2.id!!)
        val searchedDates = listOf(LocalDate.of(2024, 6, 1), LocalDate.of(2024, 6, 2))
        val nonSearchedDates = listOf(LocalDate.of(2024, 6, 3), LocalDate.of(2024, 6, 4))
        val user1SearchedDailyMission1 = SavedFixtureDailyMission.create(repository = sut, mission = mission1, date = searchedDates[0])
        val user1SearchedDailyMission2 = SavedFixtureDailyMission.create(repository = sut, mission = mission1, date = searchedDates[1])
        SavedFixtureDailyMission.create(repository = sut, mission = mission2, date = searchedDates[0])
        SavedFixtureDailyMission.create(repository = sut, mission = mission2, date = searchedDates[1])
        val user1NonSearchedDailyMission1 = SavedFixtureDailyMission.create(repository = sut, mission = mission1, date = nonSearchedDates[0])
        val user1NonSearchedDailyMission2 = SavedFixtureDailyMission.create(repository = sut, mission = mission1, date = nonSearchedDates[1])
        SavedFixtureDailyMission.create(repository = sut, mission = mission2, date = nonSearchedDates[0])
        SavedFixtureDailyMission.create(repository = sut, mission = mission2, date = nonSearchedDates[1])

        context("user1과 searchedDates로 조회하면") {
            val dailyMissions = sut.findByUserIdAndDates(userId = user1.id!!, dates = searchedDates)
            it("총 2개의 일일 미션이 조회된다.") {
                dailyMissions.size shouldBe 2
                dailyMissions.map { it.id } shouldContainAll listOf(user1SearchedDailyMission1.id, user1SearchedDailyMission2.id)
                dailyMissions.map { it.date } shouldContainAll searchedDates
                dailyMissions.map { it.date } shouldNotContainAnyOf nonSearchedDates
            }
        }
    }
})
