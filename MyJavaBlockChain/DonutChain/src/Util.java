import java.security.*;
import java.util.Base64;

public class Util {
	
	public static String getSHA256Digest(String input)
	{		
		try {
			MessageDigest digest = MessageDigest.getInstance("SHA-256");	         
			byte[] encodedhash = digest.digest(input.getBytes("UTF-8"));
			
			//we have to use a custom byte to hex converter to get the hashed value in hexadecimal:			
			String hexString = bytesToHex(encodedhash);
			
			return hexString.toString();
		}
		catch(Exception e) {
			throw new RuntimeException(e);
		}
	}

	private static String bytesToHex(byte[] hash) 
	{		
	    StringBuffer hexString = new StringBuffer();
	    for (int i = 0; i < hash.length; i++) {
	    String hex = Integer.toHexString(0xff & hash[i]);
	    if(hex.length() == 1) hexString.append('0');
	        hexString.append(hex);
	    }
	    return hexString.toString();
	}
	
	//Applies ECDSA Signature and returns the result ( as bytes ).
	public static byte[] applyECDSASig(PrivateKey privateKey, String input) 
	{
		Signature dsa;
		byte[] output = new byte[0];
		try {
			dsa = Signature.getInstance("ECDSA", "BC");
			dsa.initSign(privateKey);
			byte[] strByte = input.getBytes();
			dsa.update(strByte);
			byte[] realSig = dsa.sign();
			output = realSig;
		} 
		catch (Exception e) {
			throw new RuntimeException(e);
		}
		return output;
	}
	
	//Verifies a String signature 
	public static boolean verifyECDSASig(PublicKey publicKey, String data, byte[] signature) {
		try {
			Signature ecdsaVerify = Signature.getInstance("ECDSA", "BC");
			ecdsaVerify.initVerify(publicKey);
			ecdsaVerify.update(data.getBytes());
			return ecdsaVerify.verify(signature);
		}
		catch(Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	public static String getStringFromKey(Key key)
	{
		return Base64.getEncoder().encodeToString(key.getEncoded());
	}

}
