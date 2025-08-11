package com.stackroute.userauthenticationservice.security;


import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.stackroute.userauthenticationservice.domain.User;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
public class JWTSecurityTokenGeneratorImpl implements SecurityTokenGenerator {
	
	private String secretKey="";

	public JWTSecurityTokenGeneratorImpl() throws NoSuchAlgorithmException {
		KeyGenerator keygen=KeyGenerator.getInstance("HmacSHA256");
		SecretKey s=keygen.generateKey();
		secretKey=Base64.getEncoder().encodeToString(s.getEncoded());
		
	}
	
    public Map<String, String> generateToken(User user) {
        Map<String,String> map = new HashMap<>();
       
        Map<String, Object> claims=new HashMap<>();
        String jwtToken= Jwts.builder()
        		.claims()
        		.add(claims)
        		.subject(user.getEmail())
        		.issuedAt(new Date(System.currentTimeMillis()))
        		.expiration(new Date(System.currentTimeMillis() +60*60*30))
        		.and()
        		.signWith(getKey())
        		.compact();
        		
        		
        
        
        map.put("token",jwtToken);
        map.put("status","200 OK");
        map.put("email", user.getEmail());
        return map;
    }

	private SecretKey  getKey() {
		byte[] b=Decoders.BASE64.decode(secretKey);
		return Keys.hmacShaKeyFor(b);
	}
	
	public String extractUsername(String token) {
		return extractClaims(token, Claims::getSubject);
	}

	private Claims extractALLClaims(String token) {
		// TODO Auto-generated method stub
		return Jwts.parser().verifyWith(getKey()).build().parseSignedClaims(token).getPayload();
	}

	private <T> T extractClaims(String token, Function<Claims,T> b) {
		final Claims claim=extractALLClaims(token);
		return b.apply(claim);
	}
	
	public boolean validateToken(UserDetails user, String token) {
		String uname=extractUsername(token);
		return (uname.equals(user.getUsername()) && !isTokenExpired(token));
	}

	private boolean isTokenExpired(String token) {
		// TODO Auto-generated method stub
		return expiration(token).before(new Date());
	}

	private Date expiration(String token) {
		// TODO Auto-generated method stub
		return extractALLClaims(token).getExpiration();
	}
	
    
    
  
}
