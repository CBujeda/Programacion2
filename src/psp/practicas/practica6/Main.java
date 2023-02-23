package psp.practicas.practica6;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Base64;

import psp.practicas.practica6.utils.IdGen;
import psp.practicas.practica6.utils.rsaUtil.Cifrator;
import psp.practicas.practica6.utils.rsaUtil.RSAKeyPairGenerator;
import psp.practicas.practica6.utils.rsaUtil.RSAUtil;

public class Main {

	public static void main(String[] args) {
		IdGen ig = new IdGen();
		
		for(int i = 0; i < 9000000;i++) {
			System.out.println(ig.getAleatorygen(20));
		}
		
		/*
		String keyPublic = "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";
		
		Cifrator cr = new Cifrator();
		cr.setPublicKey(keyPublic);
		cr.crypt("Hola");
		
		*//*
		Cifrator cr2 = new Cifrator();
		cr.genKeys();
		cr2.setPublicKey(cr.getPublickey());
		
		System.out.println(cr.getPrivatekey());
		String textoAcifrar = "Dormir";
		System.out.println(textoAcifrar);
		String crypt_text = cr2.crypt(textoAcifrar);
		System.out.println(crypt_text);
		String decrypt_text = cr.decrypt(crypt_text);
		System.out.println(decrypt_text);
		*/
		
	}
}