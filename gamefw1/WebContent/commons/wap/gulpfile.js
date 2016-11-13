var gulp = require('gulp');
var minify = require('gulp-minify');
var concat = require('gulp-concat');
var streamqueue = require('streamqueue');
var uglify = require('gulp-uglify');
var jshint = require('gulp-jshint');
var templateCache = require('gulp-angular-templatecache');

gulp.task('default', ['template', 'app.module'] ,function () {
	
	return streamqueue({
		objectMode: true
	},
		gulp.src(['vender/angular.js',  'vender/!(angular).js']),
		gulp.src(['app/directives/*.js']),
		gulp.src(['app/services/CaipiaoPeiyu.js', 'app/services/!(CaipiaoPeiyu).js', ]),
		gulp.src(['app/filters/*.js']),
		gulp.src(['app/controllers/HomeCtrl.js',  'app/controllers/!(HomeCtrl).js']),
		gulp.src(['app/dist/templates.js'])
	)
    .pipe(uglify({
    	mangle: true,
    	compress: false
    }))
	.pipe(concat('main-min.js'))
    .pipe(gulp.dest('app/dist'));
});

gulp.task('app.module', function () {
	gulp.src(['app/app.module.js'])
		.pipe(uglify({
			mangle: false,
		}))
		.pipe(concat('app.module-min.js'))
		.pipe(gulp.dest('app/dist'));
});

gulp.task('template', function () {
	gulp.src(['app/templates/**/*.html'])
		.pipe(templateCache({
			module: 'AppTemplate',
			transformUrl: function (url) {
				return  url;
			},
			templateBody: "$templateCache.put(templateBaseURI + '/<%= url %>','<%= contents %>');",
			standalone: true
		}))
		.pipe(gulp.dest('app/dist'));
});

gulp.task('lint', function () {
	gulp.src(['app/directives/*.js', 'app/services/CaipiaoPeiyu.js', 'app/services/!(CaipiaoPeiyu).js','app/filters/*.js','app/controllers/HomeCtrl.js',  'app/controllers/!(HomeCtrl).js'
		,'app/app.module.js'])
		.pipe(jshint())
		.pipe(jshint.reporter('default'));
	
	
});