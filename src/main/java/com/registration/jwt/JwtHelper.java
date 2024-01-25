package com.registration.jwt;

import io.jsonwebtoken.SignatureAlgorithm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
public class JwtHelper {
    private String SECRET = "afafasfafafasfasfasfafacasdasfasxASFACASDFACASDFASFASFDAFASFASDAADSCSDFADCVSGCFVADXCcadwavfsfarvf";
    private Long TOKEN_EXPIRE_TIME = (long) (5*60*60);

    public String getNameFromToken(String token){
        return getClailFromToken(token,Claims::getSubject);
    }
    public Date getExpirationFromToken(String token){
        return getClailFromToken(token,Claims::getExpiration);
    }
    public <T> T getClailFromToken(String token, Function<Claims,T> claimResolver){
        Claims allClaims = getAllClaims(token);
        return claimResolver.apply(allClaims);
    }

    public Claims getAllClaims(String token){
        return Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token).getBody();
    }
    public String generateToken(UserDetails userDetails){
        Map<String,Object> claims =new HashMap<>();
        return doGenerateToken(claims,userDetails.getUsername());
    }

    public boolean isTokenExpired(String token){
            Date expiryDateFromToken = getExpirationFromToken(token);
            return expiryDateFromToken.before(new Date());
            }
    private String doGenerateToken(Map<String,Object> claims,String subject) {
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()+TOKEN_EXPIRE_TIME*1000))
                .signWith(SignatureAlgorithm.HS512,SECRET)
                .compact();
    }
    public boolean validateToken(String token,UserDetails userDetails){
    final String username = getNameFromToken (token);
          return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
     }

}
//@Component
//public class JwtHelper {
//    private Logger logger = LoggerFactory.getLogger(JwtHelper.class);
//    public static final Long TOKEN_VALIDITY = (long) (5*60*60);
//    private static final String SECRET = "afafasfafafasfasfasfafacasdasfasxASFACASDFACASDFASFASFDAFASFASDAADSCSDFADCVSGCFVADXCcadwavfsfarvf";
//    public String getUsernameFromToken(String token){
//        Function<Claims,Object> function = Claims::getSubject;
//        logger.info(function.toString());
//        return getClaimFromToken(token, Claims::getSubject);
//    }
//    public Date getExpiryDateFromToken(String token){
//        return getClaimFromToken(token,Claims::getExpiration);
//    }
//
//
//    public <T> T getClaimFromToken(String token, Function<Claims, T> claimResolver) {
//        Claims allClaims = getAllClaims(token);
//        return claimResolver.apply(allClaims);
//    }
//
//    public Claims getAllClaims(String token){
//        return Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token).getBody();
//    }
//
//    public boolean isTokenExpired(String token){
//        Date expiryDateFromToken = getExpiryDateFromToken(token);
//        return expiryDateFromToken.before(new Date());
//    }
//    public String generateToken(UserDetails userDetails){
//        Map<String,Object> claims = new HashMap<>();
//        return doGenerateToken(claims,userDetails.getUsername());
//    }
//    private String doGenerateToken(Map<String,Object> claims,String subject){
//        return Jwts.builder()
//                .setClaims(claims)
//                .setSubject(subject)
//                .setIssuedAt(new Date(System.currentTimeMillis()))
//                .setExpiration(new Date(System.currentTimeMillis()+TOKEN_VALIDITY * 1000))
//                .signWith(SignatureAlgorithm.HS512,SECRET)
//                .compact();
//    }
//
//    public boolean validateToken(String token,UserDetails userDetails){
//        final String username =getUsernameFromToken(token);
//        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
//    }
//}
