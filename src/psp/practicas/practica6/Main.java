package psp.practicas.practica6;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Base64;

import psp.practicas.practica6.utils.rsaUtil.Cifrator;
import psp.practicas.practica6.utils.rsaUtil.RSAKeyPairGenerator;
import psp.practicas.practica6.utils.rsaUtil.RSAUtil;

public class Main {

	public static void main(String[] args) {
		
		Cifrator cr = new Cifrator();
		cr.genKeys();
		
		System.out.println(cr.getPrivatekey());
		String textoAcifrar = "Dormir";
		System.out.println(textoAcifrar);
		String crypt_text = cr.crypt(textoAcifrar);
		System.out.println(crypt_text);
		String decrypt_text = cr.decrypt(crypt_text);
		System.out.println(decrypt_text);

		
	}
}
