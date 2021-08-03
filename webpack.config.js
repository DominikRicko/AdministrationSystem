// @ts-nocheck
// Generated using webpack-cli https://github.com/webpack/webpack-cli

const path = require("path");
const MiniCssExtractPlugin = require("mini-css-extract-plugin");
const isProduction = process.env.NODE_ENV == "production";
const RemovePlugin = require('remove-files-webpack-plugin');

const sassConfig = {
  entry: {
    base : "./src/main/style/base.scss",
  },
  output: {
    path: path.resolve(__dirname, "src/main/resources/static/css"),
    filename: "junk.js"
  },
  devtool: false,

  module: {
    rules: [
      {
        test: /\.scss$/,
        use: [
          {
            loader: 'file-loader',
            options: {
              name: 'style.css',
            }
          },
          {
            loader: 'extract-loader'
          },
          {
            loader: 'css-loader'
          },
          {
            loader: 'sass-loader'
          }
        ]
      },
    ]
  },

  plugins: [
    new RemovePlugin({
      after: {
        // expects what your output folder is `dist`.
        include: [
          './src/main/resources/static/css/junk.js',
        ]
      }
    })
  ]
}

const typescriptConfig = {
  entry: {
    index: "./src/main/ts/index.ts",
    header: "./src/main/ts/header.ts",
    footer: "./src/main/ts/footer.ts",
    login: "./src/main/ts/login.ts",
    register: "./src/main/ts/register.ts",
    reset_password: "./src/main/ts/reset_password.ts",
    users: "./src/main/ts/users.ts",
    reset_password_started: "./src/main/ts/reset_password_started.ts",
	errorbox: "./src/main/ts/errorbox.ts",
	infobox: "./src/main/ts/infobox.ts"
  },
  output: {
    filename: '[name].js',
    path: path.resolve(__dirname, "src/main/resources/static/js"),
  },
  module: {
    rules: [
      {
        test: /\.(ts|tsx)$/i,
        loader: "ts-loader",
        exclude: ["/node_modules/"],
      },
      {
        test: /\.(eot|svg|ttf|woff|woff2|png|jpg|gif)$/i,
        type: "asset",
      },

      // Add your rules for custom modules here
      // Learn more about loaders from https://webpack.js.org/loaders/
    ],
  },
  resolve: {
    extensions: [".tsx", ".ts", ".js"],
  },
  plugins: [],
};

module.exports = () => {
  if (isProduction) {

    sassConfig.mode = "production";
    typescriptConfig.mode = "production";
    sassConfig.plugins.push(new MiniCssExtractPlugin());

  } else {
    sassConfig.mode = "development";
    typescriptConfig.mode = "development";
    typescriptConfig.devtool = "source-map";
  }
  
  return [typescriptConfig, sassConfig];
};
