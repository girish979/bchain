 import java.util.ArrayList;
 
public class Block {
	
	public String hash;
	public String previousHash;
	public String merkleRoot;
	public ArrayList<Transaction> transactions = new ArrayList<Transaction>(); //our data will be a simple message.
	private int nonce;
	
	public Block(String previoushash)
	{
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
					merkleRoot
				);
	}
		
	//Mines block with given difficulty
	public void mineBlock(int difficulty) 
	{
		merkleRoot = Util.getMerkleRoot(transactions);
		//Create a string with difficulty * "0"
		String target = new String(new char[difficulty]).replace('\0', '0');  
		while(!hash.substring( 0, difficulty).equals(target)) {
			nonce ++;
			hash = calculateHash();
		}
		System.out.println("Block Mined : " + hash);
	}
	
	//Add transactions to this block
	public boolean addTransaction(Transaction transaction) {
		//process transaction and check if valid, unless block is genesis block then ignore.
		if(transaction == null) return false;		
		if((previousHash != "0")) {
			if((transaction.processTransaction() != true)) {
				System.out.println("Transaction failed to process. Discarded.");
				return false;
			}
		}
		transactions.add(transaction);
		System.out.println("Transaction Successfully added to Block");
		return true;
	}
}
