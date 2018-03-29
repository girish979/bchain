 
public class Block {
	
	public String hash;
	public String previousHash;
	private String data;
	private int nonce;
	
	public Block(String data, String previoushash)
	{
		this.data = data;
		this.previousHash = previoushash;		
		this.hash = calculateHash();
	}
	
	//Get the hash for the block
	public String calculateHash()
	{
		//Includes previous nonce, block's hash and current blocks data(transactions,etc.,)
		return Util.getSHA256Digest(
					Integer.toString(nonce) +
					previousHash + 
					data
				);
	}
		
	//Mines block with given difficulty
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
