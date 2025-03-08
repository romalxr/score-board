<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ page isELIgnored="false" %>
<html lang="en">
<head>
    <title>Tennis Scoreboard | Current Match</title>
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
                <a href="#">Matches</a>
            </nav>
        </div>
    </section>
</header>
<main>
    <div>
        <h2>Current match</h1>
        <div>
            <img src="../images/scorekeeper.png">
        </div>
        <table>
            <tr>
                <th>Player</th>
                <th>Sets</th>
                <th>Games</th>
                <th>Points</th>
            </tr>
            <tr>
                <th>${currentMatch.player1.name}</th>
                <th>${currentMatch.getSets(1)}</th>
                <th>${currentMatch.getGames(1)}</th>
                <th>${currentMatch.getPoints(1)}</th>
                <c:if test="${!currentMatch.isFinished()}">
                    <th>
                        <form method="post">
                            <button class="btn" id="winnerInfo" name="winnerInfo" value="player-1-win">Score</button>
                        </form>
                    </th>
                </c:if>
                <c:if test="${currentMatch.isFinished() && currentMatch.winnerNumber() == 1}">
                    <th>Winner!</th>
                </c:if>
            </tr>
            <tr>
                <th>${currentMatch.player2.name}</th>
                <th>${currentMatch.getSets(2)}</th>
                <th>${currentMatch.getGames(2)}</th>
                <th>${currentMatch.getPoints(2)}</th>
                <c:if test="${!currentMatch.isFinished()}">
                    <th>
                        <form method="post">
                            <button class="btn" id="winnerInfo" name="winnerInfo" value=${currentMatch.player2.id}>Score</button>
                        </form>
                    </th>
                </c:if>
                <c:if test="${currentMatch.isFinished() && currentMatch.winnerNumber() == 2}">
                    <th>Winner!</th>
                </c:if>
            </tr>
        </table>
    </div>
</main>
<footer>
    <div>
        <p>&copy; Tennis Scoreboard, project from <a href="https://zhukovsd.github.io/java-backend-learning-course/">zhukovsd/java-backend-learning-course</a> roadmap.</p>
    </div>
</footer>
</html>