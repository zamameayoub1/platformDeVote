package com.vote.votehomo.math;

import java.security.*;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

public class PaillierKeyGenerator {

    static {
        Security.addProvider(new BouncyCastleProvider());
    }

    public static KeyPair generateKeyPair() throws NoSuchAlgorithmException, NoSuchProviderException {
        KeyPairGenerator keyGen = KeyPairGenerator.getInstance("Paillier", "BC");
        keyGen.initialize(1024); // Key size - adjust as necessary
        return keyGen.generateKeyPair();
    }
}
