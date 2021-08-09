let WriteFilePlugin = require("write-file-webpack-plugin")

var config = {
  entry: {
    path: "./client/main.js"
  },
  output: {
    path: __dirname + "../../resources/static",
    filename: "bundle.js"
  },
  module: {
    loaders: [
      {
        test: /\.css$/,
        loader: "style-loader!css-loader"
      },
      {
        test: /\.jsx?$/,
        exclude: /node_modules/,
        loader: "babel-loader"
      }
    ]
  },
  plugins: [new WriteFilePlugin()],
  devtool: "eval-source-map"
}

module.exports = config
