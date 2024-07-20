package nottodo.api.config.security.resolver

import nottodo.api.config.security.CustomUserDetails
import nottodo.common.converter.NonNullConverter
import nottodo.persistence.rdb.domain.user.entity.User
import org.springframework.core.MethodParameter
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component
import org.springframework.web.bind.support.WebDataBinderFactory
import org.springframework.web.context.request.NativeWebRequest
import org.springframework.web.method.support.HandlerMethodArgumentResolver
import org.springframework.web.method.support.ModelAndViewContainer

@Component
class AuthUserArgumentResolver: HandlerMethodArgumentResolver {

    override fun supportsParameter(parameter: MethodParameter): Boolean {
        return parameter.getParameterAnnotation(Auth::class.java) != null && parameter.parameterType == Long::class.java
    }

    override fun resolveArgument(
        parameter: MethodParameter,
        mavContainer: ModelAndViewContainer?,
        webRequest: NativeWebRequest,
        binderFactory: WebDataBinderFactory?
    ): Any? {
        val principal = SecurityContextHolder.getContext().authentication.principal
        if (principal is CustomUserDetails) {
            return NonNullConverter.convert(principal.getUser().id)
        }
        throw IllegalArgumentException("시큐리티 오류입니다.")
    }
}
