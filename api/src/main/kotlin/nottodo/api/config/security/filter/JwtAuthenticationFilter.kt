package nottodo.api.config.security.filter

import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import nottodo.api.config.security.jwt.JwtTokenProvider
import nottodo.api.domain.user.service.CustomUserDetailsService
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter

@Component
class JwtAuthenticationFilter(
    private val jwtTokenProvider: JwtTokenProvider,
    private val customUserDetailsService: CustomUserDetailsService
) : OncePerRequestFilter() {

    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        val token = request.getHeader(AUTHORIZATION_HEADER)
        val authentication = getAuthentication(token)
        if (authentication != null) {
            SecurityContextHolder.getContext().authentication = authentication
        }
        filterChain.doFilter(request, response)
    }

    private fun getAuthentication(token: String?): Authentication? {
        if (token.isNullOrEmpty()) {
            return null
        }
        return try {
            val emailAndSocialType = jwtTokenProvider.extractUserFromAccessToken(token)
            val userDetails = customUserDetailsService.loadUserByEmailAndSocialType(emailAndSocialType)
            UsernamePasswordAuthenticationToken(userDetails, null, userDetails.authorities)
        } catch (e: Exception) {
            // TODO: 로깅
            null
        }
    }

    companion object {
        const val AUTHORIZATION_HEADER = "Authorization"
    }
}
