package coursework.server.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

/**
 * сервис для создания jwtToken и их валидации
 */
@Service
public class JwtService {
    private static final String SECRET_KEY = "4D635166546A576D5A7134743777217A25432A462D4A614E645267556B587032";

    /**
     * @param jwtToken уникальный токен пользователя
     * @return email заложенный в токен
     */
    public String extractUserEmail(String jwtToken) {
        return extractClaim(jwtToken, Claims::getSubject);
    }

    /**
     * @param jwtToken уникальный токен пользователя
     * @param claimsResolver метод, принадлежащий классу Claims
     * @param <T> класс Claims
     * @return
     */
    public <T> T extractClaim(String jwtToken, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(jwtToken);
        return claimsResolver.apply(claims);
    }

    /**
     * @param userDetails информация о пользователе
     * @return новый токен для авторизации
     */
    public String generateToken(UserDetails userDetails) {
        return generateToken(new HashMap<>(), userDetails);
    }

    /**
     * @param extraClaims дополнительная информация, котору надо заложить в токен
     * @param userDetails информация о пользователе
     * @return новый токен для авторизации
     */
    public String generateToken(
        Map<String, Object> extraClaims,
        UserDetails userDetails) {
        return Jwts
                .builder()
                .setClaims(extraClaims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000*60*24))
                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    /**
     * @param jwtToken уникальный токен пользователя
     * @param userDetails информация о пользователе
     * @return информацию о том разрешен ли для использования токен
     */
    public boolean isTokenValid(String jwtToken, UserDetails userDetails) {
        final String userEmail = extractUserEmail(jwtToken);
        return (userEmail.equals(userDetails.getUsername())) && !isTokenExpired(jwtToken);
    }

    /**
     * @param jwtToken уникальный токен пользователя
     * @return информацию о том истекло ли время действия токена
     */
    private boolean isTokenExpired(String jwtToken) {
        return extractExpiration(jwtToken).before(new Date());
    }

    /**
     * @param jwtToken уникальный токен пользователя
     * @return время, когда токен будет истечен для использования
     */
    private Date extractExpiration(String jwtToken) {
        return extractClaim(jwtToken, Claims::getExpiration);
    }

    /**
     * @param jwtToken уникальный токен пользователя
     * @return все данные, заложенные в токен
     */
    private Claims extractAllClaims(String jwtToken) {
        return Jwts
                .parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(jwtToken)
                .getBody();
    }

    /**
     * @return 
     */
    private Key getSigningKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
