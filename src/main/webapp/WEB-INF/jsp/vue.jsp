<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Title</title>
    <script src="js/vue/vue.js"></script>
</head>
<body>
    <ul id="example">
        <li v-for="item in items" v-bind:id="item.message">
            <a v-bind:href="item.url">{{item.message}}</a>
        </li>
    </ul>

</body>
    <script>
        var a = 2;
        console.log(Math.round(a, "%5d"))
        var example = new Vue({
            el: "#example",
            data: {
                items:[
                ]
            }
        });
        for(var i=1;i<=10;i++){
            var num = i;
            if(i<10){
                num = "0"+i;
            }
            example.items.push({message: "demo"+i, url: "vue/demo"+num});
        };

    </script>
</html>
