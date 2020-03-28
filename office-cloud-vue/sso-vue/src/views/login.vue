<template>
    <div id="login">
        <el-form>
            <el-form-item>
                <label-wrap for="username">用户名：</label-wrap>
                <input type="text" v-model.trim="loginData.username" name="username"/>
            </el-form-item>
            <el-form-item>
                <label-wrap for="password">用户名：</label-wrap>
                <input type="password" v-model.trim="loginData.password" name="username"/>
            </el-form-item>
            <el-form-item>
                <el-button type="submit" v-on:cloak="doLogin(loginData)">登录</el-button>
            </el-form-item>
        </el-form>
    </div>
</template>

<script>
import axiosUtil from "../unit/axiosUtil";

    export default {
        name: "login",
        data() {
            return {
                loginData: {
                    username: '',
                    password: ''
                }
            }
        },
        methods: {
            doLogin(loginData) {
                axiosUtil('post', '/login', loginData);
            }
        }
    }
</script>

<style scoped>

</style>