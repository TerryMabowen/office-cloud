module.exports = {
    dev: {
        // path
        assetsSubDirectory: '/static',
        assetsPublicPath: '/',
        proxyTable: {
            // 配置代理接口，解决跨域问题
            // target：被代理的接口
            // changeOrigin: 如果接口跨域，设为true
            // secure：如果是https，设为true
            '/*': {
                target: 'http://127.0.0.1:8091/',
                changeOrigin: false,
                secure: false
            }
        }
    }
};