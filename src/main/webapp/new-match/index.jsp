<!DOCTYPE html>
<html lang="en">
<head>
    <title>Tennis Scoreboard | New Match</title>
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
        <h1>Start new match</h1>
        <div>
            <img src="../images/racket.png">
        </div>
        <div>
            <form method="post" action="#">
                <label for="player1">Player one:</label>
                <input placeholder="Name" type="text" id="player1" name="player1" required title="Enter a name"><br>
                <label for="player2">Player two:</label>
                <input placeholder="Name" type="text" id="player2" name="player2" required title="Enter a name"><br>
                <input type="submit" value="Submit">
            </form>
        </div>
    </div>
</main>
<footer>
    <div>
        <p>&copy; Tennis Scoreboard, project from <a href="https://zhukovsd.github.io/java-backend-learning-course/">zhukovsd/java-backend-learning-course</a> roadmap.</p>
    </div>
</footer>
</html>