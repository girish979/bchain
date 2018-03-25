//import com.google.gson.*;
import com.google.gson.GsonBuilder;
import java.util.ArrayList;

public class DonutChain {
	
	
	public static ArrayList<Block> blockchain = new ArrayList<Block>(); 

	public static void main(String[] args) {
		
		blockchain.add(new Block("Block1","00000000"));
		blockchain.add(new Block("Block2", blockchain.get(blockchain.size()-1).hash));
		blockchain.add(new Block("Block3", blockchain.get(blockchain.size()-1).hash));
		blockchain.add(new Block("Block4", blockchain.get(blockchain.size()-1).hash));
		
		String blockchainJson = new GsonBuilder().setPrettyPrinting().create().toJson(blockchain);		
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
