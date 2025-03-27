package edu.icet.mos.service.impl;


import edu.icet.mos.entity.UserEntity;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class JwtService {


    private String secretKey="3cfa76ef14937c1c0ea519f8fc057a80fcd04a7420f8e8bcd0a7567c272e007b";
    public String generateToken(UserEntity userEntity){
        Map<String, Object> claims =new HashMap<>();
        return Jwts.builder()
                .subject(userEntity.getUsername())
                .issuedAt(new Date())
                .claims()
                .add(claims)
                .expiration(new Date(System.currentTimeMillis()+(1000*60*60)))
                .and()
                .signWith(getSignInKey())
                .compact();
    }


    private Key getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
