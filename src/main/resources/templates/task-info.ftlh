<!DOCTYPE html>
<html>
<head>
    <title>TM</title>
</head>
<body>
<h1>TM</h1><hr>
<h4>Task-info:</h4>
<b>Name</b>${task.name}<br>
<b>Description</b>${task.description}<br>
<b>Time</b>${task.time}<br>
<hr>
<form action="/task/delete/${task.id}" method="post">
    <input type="submit" name="delete"/>
</form>
</body>
</html>