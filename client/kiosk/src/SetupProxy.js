//setupProxy.js

const { createProxyMiddleware } = require('http-proxy-middleware');

module.exports = function(app){
app.use(
createProxyMiddleware('/server', {
    target: 'http://192.168.140.1/mainServo',
    pathRewrite: {
    '^/server':''
    },
    changeOrigin: true
})
)
};