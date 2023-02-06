//setupProxy.js

const { createProxyMiddleware } = require('http-proxy-middleware');

module.exports = function(app){
  app.use(
    createProxyMiddleware('/naver', {
      target: 'https://m.search.naver.com',
      pathRewrite: {
        '^/naver':''
      },
      changeOrigin: true
    })
  )

  app.use(
    createProxyMiddleware('/server', {
      target: 'https://i8b201.p.ssafy.io/backend',
      pathRewrite: {
        '^/server':''
      },
      changeOrigin: true
    })
  )

};