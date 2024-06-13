package nottodo.infra.kakao.dto.response

data class KakaoUserInfoResponse(
    val id: String,
    val kakaoAccount: KakaoAccountResponse,
    val properties: KakaoPropertiesResponse
)
