//setupProxy.js

const { createProxyMiddleware } = require('http-proxy-middleware');

module.exports = function(app){
app.use(
createProxyMiddleware('/server', {
    target: 'http://i8b201.p.ssafy.io:8081/backend',
    pathRewrite: {
    '^/server':''
    },
    changeOrigin: true
})
)
};