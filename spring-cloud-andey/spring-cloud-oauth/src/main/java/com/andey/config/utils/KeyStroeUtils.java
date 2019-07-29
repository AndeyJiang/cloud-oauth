package com.andey.config.utils;

import org.springframework.core.io.ClassPathResource;
import org.springframework.security.oauth2.provider.token.store.KeyStoreKeyFactory;

import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;

/**
 * Created by jiangbin on 2018/12/5.
 */
public class KeyStroeUtils {

    private final static String KEY_STORE_NAME = "jwt_oauth2.jks";

    private final static String PASSWORD = "jwt-oauth";

    private final static String ALIAS = "andey@123";

    public static KeyPair getPairKey() {
        KeyStoreKeyFactory keyStoreKeyFactory = new KeyStoreKeyFactory(new ClassPathResource(KEY_STORE_NAME), PASSWORD.toCharArray());
        return keyStoreKeyFactory.getKeyPair(ALIAS);
    }

    //to obtain the public key
    public static PublicKey getPublicKey() {
        return getPairKey().getPublic();
    }

    //to obtain the private key
    public  static PrivateKey getPrivateKey(){return getPairKey().getPrivate(); }
}
