package com.hr.demo.security.Token;

import java.util.Calendar;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;


import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import com.hr.demo.security.User.model.AppUserDetail;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;




@Component
public class JwtTokenUtils {
	private Log logger = LogFactory.getLog(JwtTokenUtils.class);
	private static String TOKEN_SECRET;
	private static Long ACCESS_TOKEN_VALIDITY;
	private static Long REFRESH_TOKEN_VALIDITY;

	public JwtTokenUtils(@Value("${auth.secret}") String secret, @Value("${auth.access.expiration}") Long accessValidity 
			, @Value("${auth.refresh.expiration}") Long refreshValidity) {
		Assert.notNull(accessValidity, "Validity must not be null");
		Assert.hasText(secret, "Validity must not be null or empty");

		TOKEN_SECRET = secret;
		ACCESS_TOKEN_VALIDITY = accessValidity;
		REFRESH_TOKEN_VALIDITY = refreshValidity ;
	}

	public static String generateToken(final String username, final String tokenId , boolean isRefresh) {
		return Jwts.builder()
				.setId(tokenId)
				.setSubject(username)
				.setIssuedAt(new Date())
				.setIssuer("app-Service")
				.setExpiration(calcTokenExpirationDate(isRefresh))
				.claim("created", Calendar.getInstance().getTime())
				.signWith(SignatureAlgorithm.HS512, TOKEN_SECRET).compact();
	}

	
	private static Date calcTokenExpirationDate(boolean isRefresh) {
		return new Date(System.currentTimeMillis() + (isRefresh ? REFRESH_TOKEN_VALIDITY : ACCESS_TOKEN_VALIDITY) * 1000);
	}

	public String getUserNameFromToken(String token) {
		Claims claims = getClaims(token);
		return claims.getSubject();
	}

	public String getTokenIdFromToken(String token) {
		return getClaims(token).getId();
	}

	public boolean isTokenValid(String token, AppUserDetail userDetails) {
		logger.info("isTokenExpired >>> " + isTokenExpired(token));
		String username = getUserNameFromToken(token);
		logger.info("username from token >>> " + username);
		logger.info("userDetails.getUsername >>> " + userDetails.getUsername());
		logger.info("username =  >>> userDetails.getUsername >>> " + username.equals(userDetails.getUsername()));
		Boolean isUserNameEqual = username.equalsIgnoreCase(userDetails.getUsername()) ;
		return (isUserNameEqual && !isTokenExpired(token));
	}

	public boolean isTokenExpired(String token) {
		Date expiration = getClaims(token).getExpiration();
		return expiration.before(new Date());
	}

	private Claims getClaims(String token) {
 
         return Jwts.parser().setSigningKey(TOKEN_SECRET).parseClaimsJws(token).getBody();
	}
	
	
	public boolean validateToken(String token , HttpServletRequest httpServletRequest){
	
        try {
            Jwts.parser().setSigningKey(TOKEN_SECRET).parseClaimsJws(token);
            return true;
        }catch (SignatureException ex){
        	logger.info("Invalid JWT Signature");
          //  throw new SecurityException("Invalid JWT Signature");
        }catch (MalformedJwtException ex){
        	logger.info("Invalid JWT token");
        	 httpServletRequest.setAttribute("expired",ex.getMessage());
          //  throw new SecurityException("Invalid JWT token");
        }catch (ExpiredJwtException ex){
        	logger.info("Expired JWT token");
            httpServletRequest.setAttribute("expired",ex.getMessage());
          //  throw new SecurityException("security.token_expired");             
        }catch (UnsupportedJwtException ex){
        	logger.info("Unsupported JWT exception");
         //   throw new SecurityException("Unsupported JWT exception");
        }catch (IllegalArgumentException ex){
        	logger.info("Jwt claims string is empty");
         //   throw new SecurityException("Jwt claims string is empty");
        }
        return false;
}
}