const Blockchain = require("./blockchain").Blockchain;
const Block = require("./block").Block;

console.log(Blockchain);
const jsChain = new Blockchain();

jsChain.addBlock(new Block("20/09/2019", { amount: 5 }));
jsChain.addBlock(new Block("21/09/2019", { amount: 15 }));

console.log(JSON.stringify(jsChain, null, 4));
console.log("Is blockchain valid? ", + jsChain.checkValid());