package nottodo.persistence.rdb.common

import nottodo.commonspring.exception.CustomNotFoundException
import nottodo.commonspring.exception.CustomServerException

object ParentEntityUtil {
    fun <T, R> validateAndExtractParent(children: List<T>, parentSelector: (T) -> R): R {
        if (children.isEmpty()) {
            throw CustomNotFoundException("데이터를 찾을 수 없습니다.")
        }
        val parent = parentSelector(children.first())
        if (children.any { parentSelector(it) != parent}) {
            throw CustomServerException("데이터에 문제가 있습니다.")
        }
        return parent
    }
}
