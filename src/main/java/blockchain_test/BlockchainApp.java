package blockchain_test;

import java.util.Scanner;

public class BlockchainApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Create a new blockchain
        Blockchain blockchain = new Blockchain();

        while (true) {
            System.out.println("Blockchain Menu:");
            System.out.println("1. Add a Block");
            System.out.println("2. View All Blocks");
            System.out.println("3. Exit");
            System.out.println("4. demo edit:");
            System.out.print("enter number: ");
            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume the newline character

            switch (choice) {
                case 1:
                    // Ask the user for block data
                    System.out.print("Enter data for the new block: ");
                    String data = scanner.nextLine();

                    // Get the previous block's hash
                    Bloc previousBlock = blockchain.chain.get(blockchain.chain.size() - 1);

                    // Create a new block
                    Bloc newBlock = new Bloc(previousBlock.getIndex() + 1, data, previousBlock.getHash());

                    // Add the new block to the blockchain
                    blockchain.addBlock(newBlock);

                    System.out.println("Block added to the blockchain.");
                    System.out.println("Block data: " + data);
                    System.out.println("Block hash: " + newBlock.getHash());
                    break;

                case 2:
                    // Display all available blocks in the blockchain
                    System.out.println("All Available Blocks:");
                    for (Bloc block : blockchain.chain) {
                    	System.out.println("Hash precedent: " + block.getPreviousHash());
                        System.out.println("Block Index: " + block.getIndex());
                        System.out.println("Block Data: " + block.getData());
                        System.out.println("Block Hash: " + block.getHash());
                        System.out.println("--------------");
                    }
                    break;

                case 3:
                    // Exit the program
                    System.out.println("Exiting the blockchain application.");
                    scanner.close();
                    System.exit(0);
                    break;
                case 4:
                	System.out.println("enter number of the block:");
                	int a = scanner.nextInt();
                	System.out.println("enter data: ");
                	blockchain.chain.get(a).setData(scanner.next());
                	System.out.println("verification du chain:");
                	boolean isChainValid = blockchain.isChainValid();
                	if(isChainValid == true) System.out.println("La chaine est validee");
                	else {
                		System.out.println("la chaine est non valicee, voici la comparaison du hash:");
                		blockchain.printAllBlocks();
                	}
                default:
                    System.out.println("Invalid choice. Please select 1, 2, or 3.");
            }
        }
    }
}
