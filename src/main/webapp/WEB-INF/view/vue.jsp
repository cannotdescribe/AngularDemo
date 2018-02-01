<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Title</title>
    <script src="js/frame/vue/vue.js"></script>
</head>
<body>
    <ul id="example">
        <li v-for="item in items">
            <a v-bind:href="item.href">{{item.message}}</a>
        </li>
    </ul>
ss
</body>
    <script>

        var example = new Vue({
            el: "#example",
            data: {
                items:[]
            }
        })

        var items = [];
        for(var i=0;i<10;i++){
            var num ;
            if(i<10){
                num = "0"+i;
            }else{
                num = i;
            }
            items[i]={
                href: "vue/demo"+num,
                message: "vue/demo"+num
            };
        }
        example.items = items;
    </script>
</html>
