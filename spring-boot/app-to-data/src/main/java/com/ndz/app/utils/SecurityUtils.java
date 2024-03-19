package com.ndz.app.utils;

import org.springframework.core.io.ClassPathResource;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

/*
    author: NMDuc
    created_at: 3/9/2024
    github: https://github.com/NDZwei
*/
public class SecurityUtils {
    public static String getHashPassword(String password) {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return passwordEncoder.encode(password);
    }

    public static boolean matchesPassword(String oldPassword, String newPassword) {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return passwordEncoder.matches(newPassword, oldPassword);
    }

    public static PublicKey loadPublicKeyFromResource() throws IOException {
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

    public static PrivateKey loadPrivateKeyFromResource() throws IOException {
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
