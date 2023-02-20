package psp.practicas.practica6.utils.rsaUtil;

import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import javax.xml.bind.DatatypeConverter;

public class Cifrator {

	private String publickey;
	private String privatekey;
	private RSAUtil ru;
	private RSAKeyPairGenerator keyPairGenerator; 

	/*
	 * Pre:
	 * Post: Metodo constructor
	 * 		- Funciones
	 * 			* Crear una nueva KeyPairGenerator
	 */
	public Cifrator() {
		super();
		this.ru = new RSAUtil();
		try {
			this.keyPairGenerator = new RSAKeyPairGenerator();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		};
	}


	/*
	 * Pre:
	 * Post: Metodo con el cual generamos nuevas claves
	 * 	  	 y las guarda en memoria
	 */
	public boolean genKeys() {
		try {
			this.publickey = genPublicKey();
			this.privatekey = genPrivateKey();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	/*
	 * Pre:
	 * Post: Metodo con el cual regeneramos las claves
	 */
	public boolean regenKeys() {
		boolean infos = true;
		if(!regenKeyPairGenerator()) {infos = false;}
		if(!genKeys()) {infos = false;}
		return infos;
	}
	
	/*
	 * Pre:
	 * Post: Metodo el cual regenera las keys a usar
	 */
	public boolean regenKeyPairGenerator() {
		try {
			this.keyPairGenerator = new RSAKeyPairGenerator();
			return true;
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	/*
	 * Pre:
	 * Post: Metodo con el cual generamos una key Publica en base a keyPairGenerator en Base64
	 */
	private String genPublicKey() {
		String publickeyStr = Base64.getEncoder().encodeToString(keyPairGenerator.getPublicKey().getEncoded());
		return publickeyStr;
	}
	
	/*
	 * Pre:
	 * Post: Metodo con el cual generamos una key Privada en base a keyPairGenerator en Base64
	 */
	private String genPrivateKey() {
		String privateKeyStr = Base64.getEncoder().encodeToString(keyPairGenerator.getPrivateKey().getEncoded());
		return privateKeyStr;
	}
	
	/*
	 * Pre:
	 * Post: Metodo el cual encripta los datos y 
	 * 		 retorna un string en hexadecimal encriptado en RSA
	 * 		 Tamaño maximo datos input 373 bytes
	 */
	public String crypt(String textToCrypt) {return crypt(textToCrypt,this.publickey);}
	public String crypt(String textToCrypt, String publicKey) {
		try {
			if (textToCrypt.getBytes().length <= 373) {
				byte[] cyphertext = ru.encrypt(textToCrypt, publicKey);	 // Obteenmos string cifrado
				String s = DatatypeConverter.printHexBinary(cyphertext); // Pasamos binario a String hexadecimal
				return s;
			} else {
				System.err.println("[Cifrator] [ERROR] El texto a cifrar no pueden superar los 373 bytes y ocupa "
						+ textToCrypt.getBytes().length);
				return "";
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}
	
	/*
	 * Pre:
	 * Post: Metodo el cual desencripta un String en Exadecimal
	 * 		 y retorna el string desencriptado
	 */
	public String decrypt(String cryptText) {
		try {
			if(!cryptText.equals("")) {
				byte[] bytes = DatatypeConverter.parseHexBinary(cryptText); // cryptText.getBytes(StandardCharsets.UTF_8);
				byte[] decryptText = ru.decrypt(this.privatekey, bytes);
				String sdecript = new String(decryptText, StandardCharsets.UTF_8);
				return sdecript;
			}else {
				System.err.println("[Cifrator] [ERROR] El texto a descifrar no puede ser nulo");
				return "";
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}

	}
	
	/*
	 * Pre:
	 * Post: Metodo con el cual establecemos keys personalizadas
	 */
	public void setKeys(String publickey,String privatekey) {
		setPublicKey(publickey);
		setPrivateKey(privatekey);
	}
	
	/*
	 * Pre:
	 * Post: Metodo con el cual establecemos la key publica
	 */
	public void setPublicKey(String publickey) {
		this.publickey = publickey;
	}

	/*
	 * Pre:
	 * Post: Metodo con el cual establecemos la key Privada
	 */
	public void setPrivateKey(String privatekey) {
		this.privatekey = privatekey;
	}
	
	/*
	 * Pre:
	 * Post: Metodo con el cual nulificamos las keys
	 */
	public void clearKeys() {
		this.publickey = null;
		this.privatekey = null;
	}
	
	/*
	 * GETTERS
	 */
	public String getPublickey() {
		return publickey;
	}
	public String getPrivatekey() {
		return privatekey;
	}

}
