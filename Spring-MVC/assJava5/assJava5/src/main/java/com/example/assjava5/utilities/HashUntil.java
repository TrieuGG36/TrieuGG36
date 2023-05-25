package com.example.assjava5.utilities;

import org.mindrot.jbcrypt.BCrypt;

public class HashUntil {
    public static String hash(String plain) {
        String salt = BCrypt.gensalt();
        return BCrypt.hashpw(plain, salt);
    }

    public static boolean verify(String plain, String hashed) {
        return BCrypt.checkpw(plain, hashed);
    }
}
