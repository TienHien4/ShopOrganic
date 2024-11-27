package com.practice1.service.iplm;
import java.time.Instant;

import java.util.Date;
import org.springframework.stereotype.Service;
import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.JWSHeader;
import com.nimbusds.jose.JWSSigner;
import com.nimbusds.jose.JWSVerifier;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;


@Service
public class JwtTokenProvider {
	
	
	private final String SIGNER_KEY = "8/hYtrCjsEPtD5x6K9xbu6DoizbF+5DBf9PA5Z+7NC5WgOKRcSV4i4GGQN2UYhIK\r\n";
	
	public static String USERNAME = "username";
	
	
	 public String generateToken(String username) {
		 String token = null;
		 try {
			 JWSSigner signer = new MACSigner(SIGNER_KEY);
			 JWTClaimsSet.Builder builder = new JWTClaimsSet.Builder();
			 builder.claim(USERNAME, username);
			 builder.expirationTime(new Date(
//                     Instant.now().plus(1, ChronoUnit.HOURS).toEpochMilli()
             ));
			 JWTClaimsSet claimsSet = builder.build();
			 SignedJWT signedJWT = new SignedJWT(new JWSHeader(JWSAlgorithm.HS512), claimsSet);
			 signedJWT.sign(signer);
			 token = signedJWT.serialize();
			 
		 }catch(Exception e) {
			 e.printStackTrace();
		 }
		 
 
		return token;
		  
	       
	    }
	 
	 private JWTClaimsSet getClaimsFromToken(String token) {
		 JWTClaimsSet claim = null;
		 try {
			 SignedJWT signedJWT = SignedJWT.parse(token);
			 JWSVerifier jwsVerifier = new MACVerifier(SIGNER_KEY);
			 if(signedJWT.verify(jwsVerifier)) {
				 claim = signedJWT.getJWTClaimsSet();
			 }
			 
		 }catch(Exception e) {
			 e.printStackTrace();
		 }
		return claim;
		 
	 }
	 
	 public String getUserNameFormToken(String token) {
		 String username = null;
		 try {
			 JWTClaimsSet claims = getClaimsFromToken(token);
			 username = claims.getStringClaim(USERNAME);
			 
		 }catch(Exception e) {
			 e.printStackTrace();
		 }
		return username;
		 
	 }
	 public Date getExpirationFromToken(String token) {
		 Date expiration = null;
		 JWTClaimsSet claims = getClaimsFromToken(token);
		 expiration = claims.getExpirationTime();
		 return expiration;
	 }
	 
	 public Boolean isTokenExpired(String token) {

		 Date expiration = getExpirationFromToken(token);
		return expiration.before(new Date());
	 }
	 
	 public Boolean validateTokenLogin(String token) {
		 if(token==null || token.trim().length() == 0) {
			 return false;
		 }
		 String username = getUserNameFormToken(token);
		 if(username==null || username.isEmpty()) {
			 return false;
		 }
		 if(isTokenExpired(token)) {
			 return false;
		 }
		 return true;
	 }
	 
	

}
