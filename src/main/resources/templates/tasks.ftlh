<!DOCTYPE html>
<html>
<head>
<title>123</title>
</head>
<body>
<h1>TM</h1><hr>
<h4>Tasks:</h4>
<hr>
<#list tasks as task>
<div>
    <p><b>${task.name}</b> ${task.description} | <a href="/task/${task.id}">More...</a>
</div>
</#list>
<hr>
<h3>new task</h3>
<form action="/task/create" method="post">
    Name: <input type="text" name="name"><br><br>
    Description: <input type="text" name="description"><br><br>
<input type="submit" value="add task"/>
</form>
</body>
</html>