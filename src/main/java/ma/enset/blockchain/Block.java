package ma.enset.blockchain;

import java.util.Date;
import ma.enset.utils.HashUtil;
public class Block {
    private int index;
    private long timestamp;
    private String previousHash;
    private String currentHash;
    private String data;
    private int nonce;

    public Block(int index, String previousHash, String data) {
        this.index = index;
        this.previousHash = previousHash;
        this.data = data;
        this.timestamp = new Date().getTime();
        this.currentHash = calculateHash();
    }

    public String calculateHash() {
        return HashUtil.applySha256(index + Long.toString(timestamp) + previousHash + data + nonce);
    }

    public void mineBlock(int difficulty) {
        String target = new String(new char[difficulty]).replace('\0', '0');
        while (!currentHash.substring(0, difficulty).equals(target)) {
            nonce++;
            currentHash = calculateHash();
        }
        System.out.println("Block Mined!!! : " + currentHash);
    }

    public int getIndex() {
        return index;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public String getPreviousHash() {
        return previousHash;
    }

    public String getCurrentHash() {
        return currentHash;
    }

    public String getData() {
        return data;
    }
}
