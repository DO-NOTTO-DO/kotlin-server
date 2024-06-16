package nottodo.infra.kakao.dto.response

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
data class KakaoUserInfoResponse(
    val id: String,
    @JsonProperty("kakao_account")
    val kakaoAccount: KakaoAccountResponse,
    val properties: KakaoPropertiesResponse
)
