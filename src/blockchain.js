class Blockchain {
    constructor() {
        this.chain = [this.createGenesis()];
    }

    createGenesis() {
        return new Blockchain(0, "02/09/2019", "Genesis block", 0);
    }

    latestBlock() {
        return this.chain[this.chain.length - 1];
    }

    addBlock(newBlock) {
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