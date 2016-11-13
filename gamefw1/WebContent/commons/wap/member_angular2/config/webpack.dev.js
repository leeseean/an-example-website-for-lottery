var webpackMerge = require('webpack-merge');
var ExtractTextPlugin = require('extract-text-webpack-plugin');
var commomConfig = require('./webpack.common.js');
var helpers = require('./helpers');

module.exports = webpackMerge(commomConfig, {
	devtool: 'cheap-module-eval-source-map',
	output: {
		path: helpers.root('dist'),
		filename: '[name].js',
		chunkFilename: '[id].chunk.js',
	},
	plugins: [
		new ExtractTextPlugin('[name].css')
	],
});