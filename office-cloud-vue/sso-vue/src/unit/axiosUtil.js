// 引入 axios,与后端数据交互
import axios from 'axios';
Vue.prototype.axios = axios;

const qs = require("querystring");

const axiosUtil = axios.create({
     timeout:5000,
     withCredentials:true
 });

axiosUtil.interceptors.request.use((request) => {
     if (request.method.toUpperCase() === "GET") {
         request.params = {...request.data};
     } else if (request.method.toUpperCase() === "POST") {
         request.headers["content-type"] = "appliaction/x-www-form-urlencoded";
         request.data = qs.stringify(request.data);
     } else if (request.method.toUpperCase() === "DELETE") {
         request.params = {...request.data};
     } else if (request.method.toUpperCase() === "PUT") {
         request.headers["content-type"] = "appliaction/x-www-form-urlencoded";
         request.data = qs.stringify(request.data);
     }
     return request;
 },(error) => {
     Promise.reject(error).then(r => alert("请求失败!"));
 });

axiosUtil.interceptors.response.use((response) => {
    if (response.status === 200) {
        return {
            res: {
               data: response.data,
               msg: response.message
            }
        }
    }
},(error) => {
    /**
     * 状态码401代表无权限访问，权限失效，需要重新获取authToken
     * 状态码500代表REST服务器异常
     */
    const status = error.response.status;
    const message = error.response.message;
    if (status === 401) {
        /**
         * 登录授权token超时，提示
         */
        if (message.indexOf('ERROR_CODE_001') > -1) {
            this.$message.error("登录授权token超时");
        }

        window.location.href = `${window.location.origin}/login`;
        return Promise.reject(error);
    }

    /**
     * 状态码403未无权限访问
     */
    if (status === 403) {

    }

    /**
     * 状态码404代表未找到页面
     */
    if (status === 404) {

    }

    /**
     * 1.处理系统服务异常
     * 2.处理SoaException异常
     */
    if (status === 500) {
        if (message.indexOf('ERROR_CODE_003') > -1) {
            this.$message.error("errorCode.ERROR_CODE_003");
            return
        }

        this.$message.error(message);
    }

    return Promise.reject(error);
});

 export default (method, url, data={}) => {
     if (method.toUpperCase() === "GET") {
         return axiosUtil.get(url, data)
     } else if (method.toUpperCase() === "POST"){
         return axiosUtil.post(url, data);
     } else if (method.toUpperCase() === "DELETE") {
         return axiosUtil.delete(url, data);
     } else if (method.toUpperCase() === "PUT") {
         return axiosUtil.put(url, data);
     }
 }
