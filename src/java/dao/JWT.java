/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.JWSHeader;
import com.nimbusds.jose.JWSSigner;
import com.nimbusds.jose.JWSVerifier;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import entities.Role;
import entities.Users;
import java.util.Date;
import java.util.List;
import model.CurrentUser;

/**
 *
 * @author Admin
 */
public class JWT {

    String SECRET_KEY = "1111111111111111111111111111111111";
    String EXPIRE_TIME = "3600000"; // Expire date has value 1h

    public static final String USERNAME = "username";

    public String generateTokenLogin(Users user) {
        String token = null;
        try {
            JWSSigner signer = new MACSigner(generateShareSecret());
            JWTClaimsSet.Builder builder = new JWTClaimsSet.Builder();
            builder.subject(user.getUsername());
            builder.claim("id", user.getId());
            builder.claim("name", user.getName());
            builder.claim("password", user.getPassword());
            builder.claim("image", user.getImage());
            builder.claim("roles", user.getRoles());
            builder.expirationTime(generateExpirationDate());
            JWTClaimsSet claimsSet = builder.build();
            SignedJWT signedJWT = new SignedJWT(new JWSHeader(JWSAlgorithm.HS256), claimsSet);
            signedJWT.sign(signer);

            token = signedJWT.serialize();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return token;
    }

    private JWTClaimsSet getClaimsFromToken(String token) {
        JWTClaimsSet claims = null;
        try {
            SignedJWT signedJWT = SignedJWT.parse(token);
            JWSVerifier verifier = new MACVerifier(generateShareSecret());
            if (signedJWT.verify(verifier)) {
                claims = signedJWT.getJWTClaimsSet();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return claims;
    }

    private Date generateExpirationDate() {
        long exp = System.currentTimeMillis() + Long.parseLong(EXPIRE_TIME);
        return new Date(exp);
    }

    private Date getExpirationDateFromToken(String token) {
        Date expiration = null;
        JWTClaimsSet claims = getClaimsFromToken(token);
        expiration = claims.getExpirationTime();
        return expiration;
    }

    public CurrentUser getUserFromToken(String token) {
        CurrentUser user = new CurrentUser();
        try {
            JWTClaimsSet claims = getClaimsFromToken(token);
            user.setUserName(claims.getSubject());
            user.setId((String) claims.getClaim("id"));
            user.setName((String) claims.getClaim("name"));
            user.setPassWord((String) claims.getClaim("password"));
            user.setImage((String) claims.getClaim("image"));
            user.setRoles((List<String>) claims.getClaim("roles"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }
    
    public String getUsernameFromToken(String token) {
        String username = null;
        try {
            JWTClaimsSet claims = getClaimsFromToken(token);
            username = claims.getSubject();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return username;
    }

    private byte[] generateShareSecret() {
        byte[] sharedSecret = new byte[32];
        String secret_key = SECRET_KEY;
        sharedSecret = secret_key.getBytes();
        return sharedSecret;
    }

    private Boolean isTokenExpired(String token) {
        Date expiration = getExpirationDateFromToken(token);
        return expiration.before(new Date());
    }

    public Boolean validateTokenLogin(String token) {
        if (token == null || token.trim().length() == 0) {
            return false;
        }
        String username = getUsernameFromToken(token);
        if (username == null || username.isEmpty()) {
            return false;
        }
        if (isTokenExpired(token)) {
            return false;
        }
        return true;
    }

}
