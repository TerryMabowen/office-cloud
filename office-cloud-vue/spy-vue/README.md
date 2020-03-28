# spy-vue
```
监测系统vue
vue项目各文件夹解释
├── build # webpack文件，配置参数,打包的代码存放在这里。

  ├── config          # vue项目的基本配置文件

  ├── node_modules    # 项目中安装的依赖模块

  ├── src            # 源代码文件夹

      └── assets      # 资源文件夹，里面放一些静态资源

      └── components  # 放置各个vue组件文件

      └── data        # 资源文件，如引用的json文件

      └── http        # http请求配置文件

      └── router      # vue-router 路由配置文件

      └── style      # 项目主题以及less配置文件

      └── unit        # 项目公用的js文件

      └── App.vue    # App.vue组件，项目入口文件

      └── main.js    # 项目的核心入口文件

      └── state.js    # 项目的状态管理文件，包括登录，用户信息和token等

  ├── static          # 静态资源目录，如图片、字体等

      └── rsa        # 登录加密配置文件

  ├── test            # 测试文件，初始化测试都写在这里,可以删除

  ├── .babelrc        # babel编译参数，vue开发需要babel编译

  ├── .editorconfig  # 代码编辑环境配置文件

  ├── .eslintignore  # eslint检查忽略文件

  ├── .eslintrc.js    # eslint检查配置文件

  ├── .gitignore      # git提忽略文件

  ├── .postcssrc.js  # css配置文件,自动补齐浏览器css前缀

  ├── index.html      # 主页，项目入口文件

  ├── package.json    # 项目配置文件，，描述项目信息和依赖

  ├── README.md      # 项目的说明文档，markdown 格式

  ├── server          # node后端文件，可以忽视或删除

```
## Project setup
```
npm install
```

### Compiles and hot-reloads for development
```
npm run serve
```

### Compiles and minifies for production
```
npm run build
```

### Run your unit tests
```
npm run test:unit
```

### Lints and fixes files
```
npm run lint
```

### Customize configuration
See [Configuration Reference](https://cli.vuejs.org/config/).
