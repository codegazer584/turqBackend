package io.turq.turq.config;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.Claim;
import io.turq.turq.service.interfaces.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import com.auth0.jwt.exceptions.JWTDecodeException;

import java.util.Date;

import static io.turq.turq.contstants.SecurityConstants.TOKEN_PREFIX;
import static io.turq.turq.contstants.SecurityConstants.EXPIRATION_TIME;

@Component
public class JwtTokenUtil {


    @Value("${jwt.secret}")
    private String jwtSecret;

    @Autowired
    private IUserService userService;

    public String getSubject(String token) {
        String retClaim = null;
        token = token.replace(TOKEN_PREFIX, "");
        try {
            DecodedJWT jwt = JWT.decode(token);
            retClaim = jwt.getSubject();
        } catch (JWTDecodeException exception){
            System.out.println("Received Malformed JWT: " + token);
        }
        return retClaim;
    }

    public Boolean isAdmin(String token) {
        Claim retClaim = null;
        token = token.replace(TOKEN_PREFIX, "");
        try {
            DecodedJWT jwt = JWT.decode(token);
            retClaim = jwt.getClaim("admin");
        } catch (JWTDecodeException exception){
            System.out.println("Received Malformed JWT: " + token);
        }
        return retClaim.asBoolean();
    }

    public String verifyJwt(String token) {
          String user = null;
          Date expiresAt = null;
          DecodedJWT jwt = null;
          try {
              jwt = JWT.require(Algorithm.HMAC512(jwtSecret.getBytes()))
                      .build()
                      .verify(token.replace(TOKEN_PREFIX, ""));
              user = jwt.getSubject();

          } catch (JWTDecodeException e) {
               System.out.println("Received Malformed JWT: " + token);
               return null;
          } catch (IllegalArgumentException e) {
              System.out.println("Received Malformed JWT: " + token);
              return null;
          }
          catch (JWTVerificationException exception){
              System.out.println("JWT Verfication Exception: " + token);
            return null;
          }
        return user;
     }

    public String generateJwt(String email, Boolean admin) {

        return JWT.create()
                .withSubject(email)
                .withClaim("admin", admin)
                .withExpiresAt(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .sign(Algorithm.HMAC512( jwtSecret) );
    }
}
