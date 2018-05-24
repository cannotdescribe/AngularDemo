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
            <button @click="show = !show">提交</button>
            <transition name="fff">
                <p v-if="show">dsads</p>
            </transition>
        </div>
    </body>
    <script>
        new Vue({
            el: "#app",
            data: {
                show: true
            }
        })
    </script>
    <style>
        .fff-enter-active, .fff-leave-active {
            transition: opacity .5s;
        }
        .fff-enter, .fff-leave-to /* .fade-leave-active below version 2.1.8 */ {
            opacity: 0;
        }
    </style>
</html>
