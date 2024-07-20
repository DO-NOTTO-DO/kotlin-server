package nottodo.common.random

import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.string.shouldHaveLength

class RandomUtilTest : DescribeSpec({

    describe("string()") {
        it("매개변수가 없는 경우에 길이가 10인 랜덤 문자열이 생성된다") {
            val result = RandomUtil.string()
            result shouldHaveLength 10
        }

        it("매개변수로 길이 20을 넣으면 길이가 20인 랜덤 문자열이 생성된다") {
            val length = 20
            val result = RandomUtil.string(length)
            result shouldHaveLength length
        }
    }
})
