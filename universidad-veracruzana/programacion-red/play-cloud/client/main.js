const {app, BrowserWindow} = require('electron')
const path = require('path')
const url = require('url')

function createWindow() {
    win = new BrowserWindow({icon: 'pages/images/logo.png', width: 800, height: 600})

    win.loadURL(url.format({
        pathname: path.join(__dirname, 'pages/login.html'),
        protocol: 'file:',
        slashes: true
    }))
}

app.on('ready', createWindow)