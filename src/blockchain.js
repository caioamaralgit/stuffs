const Block = require("./block").Block;

class Blockchain {
    constructor() {
        this.chain = [this.createGenesis()];
    }

    createGenesis() {
        return new Block(0, "02/09/2019", "Genesis block", "0");
    }

    latestBlock() {
        return this.chain[this.chain.length - 1];
    }

    addBlock(newBlock) {
        newBlock.index = this.chain.length;
        newBlock.previousHash = this.latestBlock().hash;
        newBlock.hash = newBlock.calculateHash();
        this.chain.push(newBlock);
    }

    checkValid() {
        for (let i = 1; i < this.chain.length; i++) {
            const currentBlock = this.chain[i];
            const previousBlock = this.chain[i - 1];

            if (currentBlock.hash !== currentBlock.calculateHash()) return false;

            if (currentBlock.previouHash !== previousBlock.hash) return false;
        }

        return true;
    }
}

module.exports = { Blockchain };