const path = require('path')
const webpack = require('webpack')

module.exports = {
    entry: {
      auth: './public/js/auth.js',
      base: './public/js/base.js',
      about: './public/js/about.js',
      index: './public/js/index.js',
      files: './public/js/files.js',
      upload: './public/js/upload.js'
    },
    output: {
      path: path.resolve(__dirname, 'public/dist'),
      filename: '[name].js'
    },
    module: {
      rules: [
        {
          test: /\.css$/,
          use: ['style-loader', 'css-loader']
        },
        {
          test: require.resolve('jquery'),
          use: [{
            loader: 'expose-loader',
            options: '$'
          }]
        }
      ]
    }
  };