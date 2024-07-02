package nottodo.api.domain.user.controller

import nottodo.api.domain.user.service.LoginService
import nottodo.commonspring.dto.response.ApiResponseBody
import nottodo.commonspring.dto.response.ResponseUtil
import nottodo.persistence.rdb.domain.user.entity.SocialType
import nottodo.user.controller.UserControllerPath
import nottodo.user.dto.request.LoginRequest
import nottodo.user.dto.response.LoginResponse
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class UserController(
    private val loginService: LoginService
) {

    @PostMapping(UserControllerPath.LOGIN)
    fun login(
        @PathVariable socialType: SocialType,
        @RequestBody request: LoginRequest
    ): ResponseEntity<ApiResponseBody<LoginResponse>> {
        val data = loginService.login(socialType, request)
        return ResponseUtil.created(data = data, location = "/")
    }
}
