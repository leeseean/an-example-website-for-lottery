var webpack = require('webpack');
var ExtractTextPlugin = require('extract-text-webpack-plugin');
var helpers = require('./helpers');

module.exports = {
		entry: {
			'polyfills': helpers.root('app/polyfills.ts'),
			'app': helpers.root('app/app.ts'),
			'vendor': helpers.root('app/vendor.ts'),
		},
		resolve: {
			extensions: ['', '.js', '.ts'],
			modulesDirectories: ['node_modules', 'app']
		},
		module: {
			loaders: [{
				test: /\.ts$/,
				loaders: ['ts', 'angular2-template-loader']
			}, {
				test: /\.html$/,
				loader: 'html'
			}, {
				test: /\.css$/,
				exclude: helpers.root('app'),
				loader: ExtractTextPlugin.extract('style', 'css?sourceMap')
			}, {
				test: /\.css$/,
				include: helpers.root('app'),
				loader: 'raw'
			}, {
		        test: /\.(png|jpe?g|gif|svg|woff|woff2|ttf|eot|ico)$/,
		        loader: 'file?name=assets/[name].[hash].[ext]'
			}]
		},
		plugins: [
		          new webpack.optimize.CommonsChunkPlugin({
		        	  name: ['app', 'vendor', 'polyfills']
		          }),
		]
}

