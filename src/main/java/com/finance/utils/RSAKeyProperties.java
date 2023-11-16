package com.finance.utils;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.security.KeyPair;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

@Data
@Component
@RequiredArgsConstructor
public class RSAKeyProperties {

  private final RSAPublicKey publicKey;
  private final RSAPrivateKey privateKey;

  public RSAKeyProperties(){
    KeyPair pair = KeyGeneratorUtility.generateRsaKey();
    this.publicKey = (RSAPublicKey) pair.getPublic();
    this.privateKey = (RSAPrivateKey) pair.getPrivate();
  }

}
