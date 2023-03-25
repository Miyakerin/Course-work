<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Document</title>
</head>
<body>
<div>
    <table>
        <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Author</th>
            <th>Genre</th>
            <th>Description</th>
            <th>Age restriction</th>
        </tr>
        <#list books as book>
            <tr>
                <td>${book.id}</td>
                <td>${book.name}</td>
                <td>${book.genre}</td>
                <td>${book.author}</td>
                <td>${book.description}</td>
                <td>${book.ageRestriction}</td>
            </tr>
        </#list>
    </table>
</div>
</body>
</html>