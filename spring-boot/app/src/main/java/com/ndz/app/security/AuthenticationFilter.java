package com.ndz.app.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ndz.app.utils.NDZUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.interfaces.RSAKey;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.time.Instant;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;

    public AuthenticationFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username, password);
        return authenticationManager.authenticate(authenticationToken);
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authentication) throws IOException {
        User user = (User) authentication.getPrincipal();
        PublicKey publicKey = this.loadPublicKeyFromResource();
        PrivateKey privateKey = this.loadPrivateKeyFromResource();
        Algorithm algorithm = Algorithm.RSA512((RSAPublicKey) publicKey, (RSAPrivateKey) privateKey);
        String token = JWT.create()
                .withSubject(user.getUsername())
                .withIssuedAt(Date.from(Instant.now()))
                .withExpiresAt(new Date(System.currentTimeMillis() + NDZUtils.TIME_OUT_TOKEN * 60 * 60 * 1000))
                .withClaim("roles", user.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()))
                .sign(algorithm);
        String refreshToken = JWT.create()
                .withSubject(user.getUsername())
                .withIssuedAt(Date.from(Instant.now()))
                .withExpiresAt(new Date(System.currentTimeMillis() + NDZUtils.TIME_OUT_REFRESH_TOKEN * 60 * 1000))
                .sign(algorithm);
        Map<String, Object> result = new HashMap<>();
        result.put("username", user.getUsername());
        result.put("access_token", token);
        result.put("refresh_token", refreshToken);
        response.setContentType(APPLICATION_JSON_VALUE);
        new ObjectMapper().writeValue(response.getOutputStream(), result);
    }

    private PublicKey loadPublicKeyFromResource() throws IOException {
        try (InputStream inputStream = new ClassPathResource("keystore/public_key.pem").getInputStream()) {
            byte[] keyBytes = inputStream.readAllBytes();
            String publicKeyPEM = new String(keyBytes, StandardCharsets.UTF_8);
            publicKeyPEM = publicKeyPEM
                    .replaceAll("-----BEGIN PUBLIC KEY-----", "")
                    .replaceAll("-----END PUBLIC KEY-----", "")
                    .replaceAll("\\s", "");
            byte[] decodedKey = java.util.Base64.getDecoder().decode(publicKeyPEM);
            X509EncodedKeySpec endCode = new X509EncodedKeySpec(decodedKey);
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            return  keyFactory.generatePublic(endCode);
        } catch (Exception e) {
            throw new IOException("Failed to load public key", e);
        }
    }

    private PrivateKey loadPrivateKeyFromResource() throws IOException {
        try (InputStream inputStream = new ClassPathResource("keystore/private_key.pem").getInputStream()) {
            byte[] keyBytes = inputStream.readAllBytes();
            String privateKeyPem = new String(keyBytes, StandardCharsets.UTF_8);
            privateKeyPem = privateKeyPem
                    .replaceAll("-----BEGIN PRIVATE KEY-----", "")
                    .replaceAll("-----END PRIVATE KEY-----", "")
                    .replaceAll("\\s", "");
            byte[] decodedKey = java.util.Base64.getDecoder().decode(privateKeyPem);
            PKCS8EncodedKeySpec endCode = new PKCS8EncodedKeySpec(decodedKey);
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            return keyFactory.generatePrivate(endCode);
        } catch (Exception e) {
            throw new IOException("Failed to load private key", e);
        }
    }
}
