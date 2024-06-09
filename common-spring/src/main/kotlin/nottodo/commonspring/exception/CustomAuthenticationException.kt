package nottodo.commonspring.exception

import javax.security.sasl.AuthenticationException

class CustomAuthenticationException(
    override val message: String
) : RuntimeException(message) {
}
