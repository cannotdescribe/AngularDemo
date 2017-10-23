<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Title</title>
    <script src="js/vue/vue.js"></script>
</head>
<body>
    <ul id="example">
        <li v-for="item in items">
            {{item.message}}
        </li>
    </ul>
ssassaddsaddsasdasdasdsa
</body>
    <script>
        var example = new Vue({
            el: "#example",
            data: {
                items:[
                    {
                        message:"vue1"
                    },
                    {
                        message:"vue2"
                    }
                ]
            }
        })
    </script>
</html>
