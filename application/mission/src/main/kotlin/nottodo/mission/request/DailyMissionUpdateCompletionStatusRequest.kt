package nottodo.mission.request

import nottodo.persistence.rdb.domain.mission.entity.CompletionStatus

data class DailyMissionUpdateCompletionStatusRequest(
    val completionStatus: CompletionStatus
)
