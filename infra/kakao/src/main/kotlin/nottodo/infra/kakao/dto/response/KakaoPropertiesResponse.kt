package nottodo.infra.kakao.dto.response

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
data class KakaoPropertiesResponse(
    val nickname: String
)
