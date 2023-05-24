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
      target: 'https://도메인/backend',
      pathRewrite: {
        '^/server':''
      },
      changeOrigin: true
    })
  )

};
// 그때도우리가