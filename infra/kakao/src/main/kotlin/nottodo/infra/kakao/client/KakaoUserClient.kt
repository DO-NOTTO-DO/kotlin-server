package nottodo.infra.kakao.client

import nottodo.infra.kakao.dto.response.KakaoUserInfoResponse
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestHeader

@FeignClient(name = "kakaoLoginClient", url = "https://kapi.kakao.com")
interface KakaoUserClient {

    @PostMapping("/v2/user/me")
    fun getKakaoInfo(@RequestHeader("Authorization") authorization: String): KakaoUserInfoResponse
}
