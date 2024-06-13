package nottodo.infra.kakao.service

import nottodo.infra.kakao.client.KakaoUserClient
import nottodo.infra.kakao.dto.response.KakaoUserInfoResponse
import org.springframework.stereotype.Service

@Service
class KakaoUserService(
    private val kakaoUserClient: KakaoUserClient
) {

    fun getUserInfo(token: String): KakaoUserInfoResponse {
        return kakaoUserClient.getKakaoInfo(AUTHORIZATION_PREFIX + token)
    }

    companion object {
        const val AUTHORIZATION_PREFIX = "Bearer "
    }
}
