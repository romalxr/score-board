<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ page isELIgnored="false" %>
<html lang="en">
<head>
    <title>Tennis Scoreboard | Matches</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>

</body>
<header>
    <section>
        <div>
            <span>Tennis Scoreboard</span>
        </div>
        <div>
            <nav>
                <a href="/">Home</a>
                <a href="/matches">Matches</a>
            </nav>
        </div>
    </section>
</header>
<main>
    <div>
        <h2>Matches</h1>
        <div>
            <form method="post" style="display: flex; align-items: center; gap: 10px;">
                <input placeholder="Name" type="text" id="playerName" name="playerName" title="Enter a name" value=${filter}><br>
                <input type="hidden" name="pageNumber" value="${pageNumber}">
                <input type="submit" value="Search">
            </form>
        </div>
        <table>
            <tr>
                <th>Player One</th>
                <th>Player Two</th>
                <th>Winner</th>
            </tr>
            <c:forEach var="match" items="${matchesPortion}">
                <tr>
                    <th>${match.getPlayer1().getName()}</th>
                    <th>${match.getPlayer2().getName()}</th>
                    <th>${match.getWinner().getName()}</th>
                </tr>
            </c:forEach>
        </table>
        <div style="display: flex; align-items: center; gap: 10px;">
            <button type="button" onclick="location.href='/matches?page=${pageNumber - 1}&filter_by_player_name=${filter}';"
                ${hasLeft ? '' : 'disabled'}><</button>
            <p>${pageNumber}</p>
            <button type="button" onclick="location.href='/matches?page=${pageNumber + 1}&filter_by_player_name=${filter}';"
                ${hasRight ? '' : 'disabled'}>></button>
        </div>
    </div>
</main>
<footer>
    <div>
        <p>&copy; Tennis Scoreboard, project from <a href="https://zhukovsd.github.io/java-backend-learning-course/">zhukovsd/java-backend-learning-course</a> roadmap.</p>
    </div>
</footer>
</html>