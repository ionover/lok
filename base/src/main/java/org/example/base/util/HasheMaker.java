package org.example.base.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Optional;

@Component
public class HasheMaker {

    private final Logger log = LoggerFactory.getLogger(HasheMaker.class);

    private final String BASE_HASE_ALGORITHM = "SHA-256";

    public Optional<String> createHash(Object data) {
        try {
            MessageDigest md = MessageDigest.getInstance(BASE_HASE_ALGORITHM);
            byte[] digest = md.digest(data.toString().getBytes(StandardCharsets.UTF_8));
            StringBuilder sb = new StringBuilder(digest.length * 2);
            for (byte b: digest) {
                sb.append(String.format("%02x", b));
            }

            return Optional.of(sb.toString());
        } catch (NoSuchAlgorithmException e) {
            log.warn("Алгоритм хеширования " + BASE_HASE_ALGORITHM + " не доступен", e);

            return Optional.empty();
        } catch (Exception e) {
            log.warn("Непредвиденная ошибка в процессе формирования хэша: {}", e.getMessage());

            return Optional.empty();
        }
    }
}
