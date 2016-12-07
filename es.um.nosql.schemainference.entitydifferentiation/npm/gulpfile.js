'use strict'

var gulp = require('gulp'),
    source = require('vinyl-source-stream'),
    browserify = require('browserify')

gulp.task('browserify', function() {
    var b = browserify({entries: ['./app.js', './lib/**.js']});

    return b.bundle()
            .pipe(source('bundle.js'))
	    .pipe(gulp.dest('./dist/js'));
});

gulp.task('watch', ['browserify'], function() {
    gulp.watch('./lib/**/*.js', ['browserify'])
});

gulp.task('default', ['browserify']);
gulp.task('dev', ['watch']);
