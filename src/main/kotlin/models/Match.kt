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

    fun captureElderDrake(team: Team) {
        team.captureElderDrake()
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

    fun getTotalTeamGold(teamName: String): Long? {
        return when (teamName) {
            teamBlue.name -> teamBlue.totalGold
            teamRed.name -> teamRed.totalGold
            else -> null
        }
    }

    fun incrementMatchTime(minutes: Int) {
        duration += minutes
    }

    fun checkForWinner() {
        if (teamBlue.towersDestroyed >= 11 || teamBlue.baronKills >= 3 || teamBlue.elderDrakeKills >= 2) {
            winner = teamBlue.name
        } else if (teamRed.towersDestroyed >= 11 || teamRed.baronKills >= 3 || teamRed.elderDrakeKills >= 2) {
            winner = teamRed.name
        }
    }
}
