package tulio.ecommerce.Services;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;

import tulio.ecommerce.Models.User.UserModel;

@Service
public class TokenService {

    @Value("${api.security.token.secret}")
    private String secret;
    public String generateToken(UserModel userModel)
    {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            final String token = JWT.create()
                .withIssuer("project-Back")
                .withSubject(userModel.login)
                .withExpiresAt(getExpirationDate())
                .sign(algorithm);
            return token;
            
        } catch (JWTCreationException e) {
            throw new RuntimeException("Error while generating token",e);
        }
    }   

    public String validateToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.require(algorithm)
                .withIssuer("project-Back")
                .build()
                .verify(token)
                .getSubject();
        } catch (JWTVerificationException e) {
            return null;
        }
    }

    private Instant getExpirationDate()
    {
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }
}