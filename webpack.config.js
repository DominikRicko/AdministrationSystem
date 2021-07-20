// @ts-nocheck
// Generated using webpack-cli https://github.com/webpack/webpack-cli

const path = require("path");
const MiniCssExtractPlugin = require("mini-css-extract-plugin");
const WorkboxWebpackPlugin = require("workbox-webpack-plugin");
const isProduction = process.env.NODE_ENV == "production";

const stylesHandler = isProduction
  ? MiniCssExtractPlugin.loader
  : "style-loader";

const config = {
  entry: {
    index : "./src/main/ts/index.ts",
    header : "./src/main/ts/header.ts",
    footer : "./src/main/ts/footer.ts",
    login : "./src/main/ts/login.ts",
    register : "./src/main/ts/register.ts",
    reset_password : "./src/main/ts/reset_password.ts",
  },
  output: {
    filename: '[name].js',
    path: path.resolve(__dirname, "src/main/resources/static/js"),
  },

  devtool: "source-map",

  module: {
    rules: [
      {
        test: /\.(ts|tsx)$/i,
        use: {
          loader: "ts-loader",
          options: {
            
          }
        },
        exclude: ["/node_modules/"],
      },
      {
				test: /\.scss$/,
				use: [
					{
						loader: 'file-loader',
						options: {
							name: 'css/[name].blocks.css',
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
};

module.exports = () => {
  if (isProduction) {
    config.mode = "production";

    config.plugins.push(new MiniCssExtractPlugin());

    config.plugins.push(new WorkboxWebpackPlugin.GenerateSW());
  } else {
    config.mode = "development";
  }
  return config;
};
