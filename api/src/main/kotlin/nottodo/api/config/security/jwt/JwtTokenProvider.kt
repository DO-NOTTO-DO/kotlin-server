package nottodo.api.config.security.jwt

import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import io.jsonwebtoken.security.Keys
import nottodo.api.config.security.jwt.dto.EmailAndSocialType
import nottodo.persistence.rdb.domain.user.entity.SocialType
import org.springframework.stereotype.Component
import java.nio.charset.StandardCharsets
import java.util.*

@Component
class JwtTokenProvider(
    private val jwtProperties: JwtProperties
) {

    private val accessSecretKey = Keys.hmacShaKeyFor(jwtProperties.accessSecret.toByteArray(StandardCharsets.UTF_8))

    fun generateAccessToken(email: String, socialType: SocialType): String {
        val claims = Jwts.claims()
        claims["email"] = email
        claims["socialType"] = socialType.name
        val now = Date()
        val expiration = Date(now.time + jwtProperties.accessExpireLength)
        return Jwts.builder()
            .setClaims(claims)
            .setIssuedAt(Date())
            .setExpiration(expiration)
            .signWith(accessSecretKey, SignatureAlgorithm.HS256)
            .compact()
    }

    fun extractUserFromAccessToken(token: String): EmailAndSocialType {
        val claims = extractAllClaimsFromAccessToken(token)
        val email = claims.get("email", String::class.java)
        val socialType = SocialType.valueOf(claims.get("socialType", String::class.java))
        return EmailAndSocialType(email, socialType)
    }

    private fun extractAllClaimsFromAccessToken(accessToken: String): Claims {
        return Jwts.parserBuilder()
            .setSigningKey(accessSecretKey)
            .build()
            .parseClaimsJws(accessToken)
            .body
    }
}
