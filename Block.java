import java.util.Date;
import java.util.*;
import java.util.ArrayList;


public class Block {


    private ArrayList<String> transactions;
    public String hash;
    public String previousHash;
    private String data; //our data will be a simple message.
    private long timeStamp; //as number of milliseconds since 1/1/1970.
    private int nonce;
    private String merkleroot;



    //Block Constructor.
    public Block(String data,String previousHash ) {
        this.data = data;
        this.previousHash = previousHash;
        this.timeStamp = new Date().getTime();
        this.hash = calculateHash();//Making sure we do this after we set the other values.
        //transaction.add(this.hash);
    }
    public Block(ArrayList<String> transactions,String previousHash ) {
        this.previousHash = previousHash;
        this.timeStamp = new Date().getTime();
        this.transactions=new ArrayList<>();
        this.transactions.addAll(transactions);
        MerkleTrees mk=new MerkleTrees(transactions);
        this.merkleroot=mk.getMerkleRoot().get(0);
        this.hash = calculateHash();//Making sure we do this after we set the other values.
    }

    //Calculate new hash based on blocks contents
    public String calculateHash() {
        String calculatedhash = StringUtil.applySha256(
                previousHash +
                        Long.toString(timeStamp) +
                        Integer.toString(nonce) +
                        data
        );
        return calculatedhash;
    }

    public void mineBlock(int difficulty) {
        String target = new String(new char[difficulty]).replace('\0', '0'); //Create a string with difficulty * "0"
        while(!hash.substring( 0, difficulty).equals(target)) {
            nonce ++;
            hash = calculateHash();
        }
        System.out.println("Block Mined!!! : " + hash);
        //transaction.add(hash);
        //System.out.println("hash:"+transaction);
    }


}
