const builder = require('botbuilder')
const restify = require('restify')
const cognitiveService = require('botbuilder-cognitiveservices')
const utf8 = require('utf8')

const server = restify.createServer()
const port = process.env.port || 3978

server.listen(port, () => {
    console.log(`Servidor rodando em @{server.url}`)
})

const connector = new builder.ChatConnector({
    appId: process.env.MICROSOFT_APP_ID,
    appPassword: process.env.MICROSOFT_APP_PASSWORD
})

server.post('/api/messages', connector.listen())

const bot = new builder.UniversalBot(connector) 
bot.set('storage', new builder.MemoryBotStorage())

const recognizer = new cognitiveService.QnAMakerRecognizer({
    knowledgeBaseId: 'bd05f4fc-5723-47ce-82a0-0e2553bd8d1d',
    subscriptionKey: '4035ef21bd284e6180e832be7537d4c0',
    top: 3
})

const qnaMakerTools = new cognitiveService.QnAMakerTools()
bot.library(qnaMakerTools.createLibrary())

const qnaMakerDialog = new cognitiveService.QnAMakerDialog({
    recognizers: [recognizer],
    defaultMessage: 'Desculpe, não entendi...',
    oneThreshold: 0.5,
    feedbackLib: qnaMakerTools
})

qnaMakerDialog.respondFromQnAMakerResult = (session, result) => {
    session.send(primeira)
}

bot.dialog('/', qnaMakerDialog)