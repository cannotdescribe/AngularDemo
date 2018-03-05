<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Title</title>
    <script src="../js/vue/vue.js"></script>
    <style>
        .first{
            color: red;
        }
    </style>
</head>
<body>
    <div id="demo01">
        <span v-if="type === 'A'">A</span>
        <span v-else-if="type === 'B'">B</span>
        <span v-else="type === 'C'">C</span>
    </div>

    <div id="demo02">
        <ul>
            <li v-for="(k, v, i) in obj" v-bind:class="v">{{v}}</li>
        </ul>
    </div>

    <div id="demo03">
        <div v-bind:class="value"></div>
    </div>
</body>
    <script type="application/javascript">
        var demo01 = new Vue({
            el: "#demo01",
            data:{
                type: "A"
            }
        });
        var demo02 = new Vue({
            el: "#demo02",
            data:{
                obj:{
                    firstObject: "first",
                    secondObject: "second",
                    thirdObject: "third"
                }
            }
        })
        var demo03 = new Vue({
            el: "#demo03",
            data:{
                value:"class01"
            }
        })
    </script>
</html>
