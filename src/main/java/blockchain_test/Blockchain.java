package blockchain_test;

import java.util.ArrayList;
import java.util.List;

public class Blockchain {
    List<Bloc> chain;

    public Blockchain() {
        this.chain = new ArrayList<>();
        Bloc genesisBlock = createGenesisBlock();
        this.chain.add(genesisBlock);
    }

    private Bloc createGenesisBlock() {
        return new Bloc(0, "Genesis Block", "");
    }

    public void addBlock(Bloc newBlock) {
        Bloc previousBlock = chain.get(chain.size() - 1);
        if (newBlock.getPreviousHash().equals(previousBlock.getHash())) {
            chain.add(newBlock);
        } else {
            System.out.println("error hash");
        }
    }
    
    // Vérification de l'intégrité de la chaîne
    public boolean isChainValid() {
        for (int i = 1; i < chain.size(); i++) {
            Bloc currentBlock = chain.get(i);
            Bloc previousBlock = chain.get(i - 1);

            // Vérification du hash du bloc courant
            if (!currentBlock.getHash().equals(currentBlock.calculateHash()) || !currentBlock.getPreviousHash().equals(previousBlock.getHash())) {
                return false;
            }

        }
        return true;
    }
    
    public void printAllBlocks() {
        for (Bloc block : chain) {
        	System.out.println("Hash precedent: " + block.getPreviousHash());
            System.out.println("Block Index: " + block.getIndex());
            System.out.println("Block Data: " + block.getData());
            System.out.println("Block Hash: " + block.getHash());
            System.out.println("--------------");
        }
    }

    
}