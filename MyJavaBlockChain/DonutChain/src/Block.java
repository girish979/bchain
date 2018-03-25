 
public class Block {
	
	public String hash;
	public String previousHash;
	private String data;
	private int nonce;
	
	public String calculateHash()
	{
		return Util.getSHA256Digest(
					Integer.toString(nonce) +
					previousHash + 
					data
				);
	}
	
	public Block(String data, String previoushash)
	{
		this.data = data;
		this.previousHash = previoushash;		
		this.hash = calculateHash();
	}
	
	public void mineBlock(int difficulty) 
	{
		//Create a string with difficulty * "0"
		String target = new String(new char[difficulty]).replace('\0', '0');  
		while(!hash.substring( 0, difficulty).equals(target)) {
			nonce ++;
			hash = calculateHash();
		}
		System.out.println("Block Mined : " + hash);
	}
}