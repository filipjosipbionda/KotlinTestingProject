package models

class Match(
    val teamBlue: Team,
    val teamRed: Team,
    var duration: Int = 0,
    var winner: String? = null,
    var blueTowers: Int = 8,
    var redTowers: Int = 8
) {
    fun endMatch(winningTeam: String) {
        winner = winningTeam
    }

    fun captureBaron(team: Team) {
        team.captureBaron()
    }

    fun captureDrake(team: Team) {
        team.captureDrake()
    }

    fun destroyTower(team: Team) {
        if (team == teamBlue && redTowers > 0) {
            redTowers--
            team.destroyTower()
        } else if (team == teamRed && blueTowers > 0) {
            blueTowers--
            team.destroyTower()
        }
    }

    fun increaseTeamGold(team: Team, amount: Long) {
        team.addGold(amount)
    }

    fun incrementMatchTime(minutes: Int) {
        duration += minutes
    }
}
