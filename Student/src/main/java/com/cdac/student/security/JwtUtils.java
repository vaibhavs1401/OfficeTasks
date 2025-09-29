/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.cdac.student.security;

import com.cdac.student.entity.Admin;
import com.cdac.student.entity.BaseEntity;
import com.cdac.student.entity.Student;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import java.util.Date;
import javax.crypto.SecretKey;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

/**
 *
 * @author hcdc
 */
@Component
public class JwtUtils {
    
    private final String jwtSecret = "aiuwqdiqwidu";
    
    private final int jwtExpirationMs = 3600000;
    
    private SecretKey key;
    
    private void init(){
        key = Keys.hmacShaKeyFor(jwtSecret.getBytes());
    }
    
    public  String  generateJwtToken(Authentication authentication){
        BaseEntity userPrincipal =  new BaseEntity();
        if(authentication.getPrincipal() instanceof Student){
            userPrincipal = (Student) authentication.getPrincipal();
        }
        else if(authentication.getPrincipal() instanceof Admin){
            userPrincipal = (Admin) authentication.getPrincipal();
        }
        
        
        return Jwts.builder()
                .setSubject(userPrincipal.getEmail())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + jwtExpirationMs))
               // .claim("authorities", getAuthoritiesInString(userPrincipal.getAuthorities()))
                .signWith(SignatureAlgorithm.HS256, jwtSecret)
                .compact();
                
    }
    
    
    
    
}
