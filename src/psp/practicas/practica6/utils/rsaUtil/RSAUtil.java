package psp.practicas.practica6.utils.rsaUtil;

import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

public class RSAUtil {

    private PublicKey publicKey;
    private PrivateKey privateKey;
    
	public RSAUtil() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	/*
	 * Pre:
	 * Post: Metodo el cual devuelve una (PublicKey) Key publica
	 */
	public PublicKey getPublicKey(String base64PublicKey){
        PublicKey publicKey = null;
        try{
            X509EncodedKeySpec keySpec = new X509EncodedKeySpec(Base64.getDecoder().decode(base64PublicKey.getBytes()));
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            publicKey = keyFactory.generatePublic(keySpec);
            return publicKey;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
        }
        return publicKey;
    }
	
	/*
	 * Pre:
	 * Post: Metodo el cual establece una clave publica 
	 */
	public void setPublicKey(String base64PublicKey){setPublicKey(getPublicKey(base64PublicKey));}
	public void setPublicKey(PublicKey publicKey) {
		this.publicKey = publicKey;
	}
	
	/*
	 * Pre:
	 * Post: Metodo el cual encripta una cadena
	 */
	public byte[] encrypt(String data, String publicKey) throws BadPaddingException, IllegalBlockSizeException, InvalidKeyException, NoSuchPaddingException, NoSuchAlgorithmException {
		Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
		cipher.init(Cipher.ENCRYPT_MODE, getPublicKey(publicKey));
		return cipher.doFinal(data.getBytes());
	}
	
	/*
	 * Pre:
	 * Post: Metodo el cual desencripta una cadena
	 */
	public byte[] decrypt(String privateKey, byte[] encryptedData) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException {		
			final Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
			cipher.init(Cipher.DECRYPT_MODE, getPrivateKey(privateKey));
			return cipher.doFinal(encryptedData);
	}
	
	/*
	 * Pre:
	 * Post: Metodo el cual devuelve una (PrivateKey) Key privada
	 */
	public PrivateKey getPrivateKey(String base64PrivateKey){
        PrivateKey privateKey = null;
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(Base64.getDecoder().decode(base64PrivateKey.getBytes()));
        KeyFactory keyFactory = null;
        try {
            keyFactory = KeyFactory.getInstance("RSA");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        try {
            privateKey = keyFactory.generatePrivate(keySpec);
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
        }
        return privateKey;
    }
	
	/*
	 * Pre:
	 * Post: Metodo el cual establece una clave privada 
	 */
	public void setPrivateKey(String base64PrivateKey){setPrivateKey(getPrivateKey(base64PrivateKey));}
	public void setPrivateKey(PrivateKey privateKey) {
		this.privateKey = privateKey;
	}
	
	
	
}
