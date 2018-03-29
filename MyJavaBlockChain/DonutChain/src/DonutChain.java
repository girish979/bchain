import com.google.gson.GsonBuilder;
import java.util.ArrayList;

public class DonutChain {
	
	
	public static ArrayList<Block> blockchain = new ArrayList<Block>(); 
	public static int difficulty = 5;//Setting difficulty to 5 manually

	public static void main(String[] args) {
		
		blockchain.add(new Block("Block1","00000000"));
		System.out.println("Trying to Mine block "+Integer.toString(blockchain.size()-1) +"... ");
		blockchain.get(blockchain.size()-1).mineBlock(difficulty);
		
		blockchain.add(new Block("Block2", blockchain.get(blockchain.size()-1).hash));
		System.out.println("Trying to Mine block "+Integer.toString(blockchain.size()-1) +"... ");
		blockchain.get(blockchain.size()-1).mineBlock(difficulty);
		
		blockchain.add(new Block("Block3", blockchain.get(blockchain.size()-1).hash));
		System.out.println("Trying to Mine block "+Integer.toString(blockchain.size()-1) +"... ");
		blockchain.get(blockchain.size()-1).mineBlock(difficulty);
		
		blockchain.add(new Block("Block4", blockchain.get(blockchain.size()-1).hash));
		System.out.println("Trying to Mine block "+Integer.toString(blockchain.size()-1) +"... ");
		blockchain.get(blockchain.size()-1).mineBlock(difficulty);
		
		System.out.println("\nBlockchain is Valid: " + isChainValid());
		
		String blockchainJson = new GsonBuilder().setPrettyPrinting().create().toJson(blockchain);		
		System.out.println("Blockchain");
		System.out.println(blockchainJson);
		
		
	}
	
	public static Boolean isChainValid()
	{
		Block currentBlock, previousBlock;
		
		for(int i=1; i < blockchain.size(); i++) {
			previousBlock = blockchain.get(i-1);
			currentBlock = blockchain.get(i);

			
			if(!currentBlock.hash.equals(currentBlock.calculateHash()) ){
				System.out.println("Current Hashes not equal");			
				return false;
			}

			if(!previousBlock.hash.equals(currentBlock.previousHash) ) {
				System.out.println("Previous Hashes not equal");
				return false;
			}
		}
		return true;
	}

}
