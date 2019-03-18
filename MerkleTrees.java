import java.util.*;

public class MerkleTrees {

    List<String> transaction=new ArrayList<>();

        public MerkleTrees(List<String> transaction){
            this.transaction=transaction;
        }

        public List<String>  getMerkleRoot(){
            return construct(this.transaction);
        }

        private List<String>  construct(List<String> transaction) {

            if (transaction.size() == 1) return transaction;

            List<String>  updatedList = new ArrayList<>();

            for (int i = 0; i < transaction.size() - 1; i = i + 2)
                updatedList.add(mergeHash(transaction.get(i), transaction.get(i + 1)));

            if (transaction.size() % 2 == 1)
                updatedList.add(mergeHash(transaction.get(transaction.size() - 1), transaction.get(transaction.size() - 1)));


            return construct(updatedList);

        }

        private  String mergeHash(String hash1,String hash2){
            String mergedhash=hash1+hash2;
            return StringUtil.applySha256(mergedhash);
        }


}
