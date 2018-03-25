import java.security.MessageDigest;

public class Util {
	
	public static String getSHA256Digest(String input){		
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

	private static String bytesToHex(byte[] hash) {		
	    StringBuffer hexString = new StringBuffer();
	    for (int i = 0; i < hash.length; i++) {
	    String hex = Integer.toHexString(0xff & hash[i]);
	    if(hex.length() == 1) hexString.append('0');
	        hexString.append(hex);
	    }
	    return hexString.toString();
	}

}