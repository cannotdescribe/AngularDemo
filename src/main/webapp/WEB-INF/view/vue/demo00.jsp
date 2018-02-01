<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Title</title>
        <script src="../js/frame/vue/vue.js"></script>
        <link rel="stylesheet" href="https://unpkg.com/element-ui/lib/theme-chalk/index.css">
        <script src="https://unpkg.com/element-ui/lib/index.js"></script>
    </head>
    <body>
        <div id="app">
            <el-button @click="visible = true">按钮</el-button>
            <el-dialog :visible.sync="visible" title="Hello world">
                <p>欢迎使用 Element</p>
            </el-dialog>
        </div>
    </body>
    <script>
        new Vue({
            el: '#app',
            data: function() {
                return { visible: false }
            }
        })
    </script>
</html>
