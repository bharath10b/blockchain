import java.util.ArrayList;
import com.google.gson.GsonBuilder;
import java.util.*;

public class BlockChain {

    public static ArrayList<Block> blockchain = new ArrayList<Block>();
    private static ArrayList<String> transaction1=new ArrayList<>();


    public static int difficulty = 2;

    public static void main(String[] args) {
        //add our blocks to the blockchain ArrayList:

        blockchain.add(new Block("Hi im the first block", "0"));
        System.out.println("Trying to Mine block 1... ");
        blockchain.get(0).mineBlock(difficulty);

        blockchain.add(new Block("Yo im the second block",blockchain.get(blockchain.size()-1).hash));
        System.out.println("Trying to Mine block 2... ");
        blockchain.get(1).mineBlock(difficulty);

        blockchain.add(new Block("Hey im the third block",blockchain.get(blockchain.size()-1).hash));
        System.out.println("Trying to Mine block 3... ");
        blockchain.get(2).mineBlock(difficulty);



        transaction1.add(" a paid b 10");
        transaction1.add(" b paid c 10");
        transaction1.add(" c paid d 10");
        transaction1.add(" d paid a 10");


        blockchain.add(new Block(transaction1 , blockchain.get(blockchain.size()-1).hash));
        System.out.println("Trying to Mine block 1... ");
        blockchain.get(3).mineBlock(difficulty);


        System.out.println("\nBlockchain is Valid: " + isChainValid());

        String blockchainJson = new GsonBuilder().setPrettyPrinting().create().toJson(blockchain);
        System.out.println("\nThe block chain: ");
        System.out.println(blockchainJson);
       /* List<String> transaction=new ArrayList<>();
        for(int i=0;i<blockchainJson.size()-1;i++)
        {
            String j=blockchain.get("hash");
            transaction.add(j);
        }*/
       //System.out.println("hashes"+transaction);

       //MerkleTrees mk=new MerkleTrees(transaction);
       //System.out.println("Merkle tree root is:"+mk.getMerkleRoot().get(0));

    }

    public static Boolean isChainValid() {
        Block currentBlock;
        Block previousBlock;
        String hashTarget = new String(new char[difficulty]).replace('\0', '0');

        //loop through blockchain to check hashes:
        for(int i=1; i < blockchain.size(); i++) {
            currentBlock = blockchain.get(i);
            previousBlock = blockchain.get(i-1);
            //compare registered hash and calculated hash:
            if(!currentBlock.hash.equals(currentBlock.calculateHash()) ){
                System.out.println("Current Hashes not equal");
                return false;
            }
            //compare previous hash and registered previous hash
            if(!previousBlock.hash.equals(currentBlock.previousHash) ) {
                System.out.println("Previous Hashes not equal");
                return false;
            }
            //check if hash is solved
            if(!currentBlock.hash.substring( 0, difficulty).equals(hashTarget)) {
                System.out.println("This block hasn't been mined");
                return false;
            }
        }
        return true;
    }
}
