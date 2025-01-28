import models.Champion
import models.Match
import models.Team
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class MatchTest {

    @Test
    fun `Match should set winner when endMatch is called`() {
        val match = Match(
            Team("Blue Team", Champion.Tank("Ornn"), Champion.Fighter("Vi"), Champion.Mage("Syndra"), Champion.Marksman("Jinx"), Champion.Support("Lulu")),
            Team("Red Team", Champion.Tank("Malphite"), Champion.Fighter("Jarvan"), Champion.Mage("Veigar"), Champion.Marksman("Kai'Sa"), Champion.Support("Braum"))
        )

        match.endMatch("Blue Team")

        assertEquals("Blue Team", match.winner)
    }

    @Test
    fun `Destroying a tower should reduce enemy tower count`() {
        val match = Match(
            Team("Blue Team", Champion.Tank("Shen"), Champion.Fighter("Darius"), Champion.Mage("Ahri"), Champion.Marksman("Vayne"), Champion.Support("Soraka")),
            Team("Red Team", Champion.Tank("Sion"), Champion.Fighter("Rengar"), Champion.Mage("Zoe"), Champion.Marksman("Jhin"), Champion.Support("Rakan"))
        )

        match.destroyTower(match.teamBlue)

        assertEquals(7, match.redTowers)
        assertEquals(1, match.teamBlue.towersDestroyed)
    }

    @Test
    fun `Capturing a Baron should increase Baron count for the team`() {
        val match = Match(
            Team("Blue Team", Champion.Tank("Ornn"), Champion.Fighter("Vi"), Champion.Mage("Syndra"), Champion.Marksman("Jinx"), Champion.Support("Lulu")),
            Team("Red Team", Champion.Tank("Malphite"), Champion.Fighter("Jarvan"), Champion.Mage("Veigar"), Champion.Marksman("Kai'Sa"), Champion.Support("Braum"))
        )

        match.captureBaron(match.teamBlue)

        assertEquals(1, match.teamBlue.baronKills)
    }

    @Test
    fun `Capturing a Drake should increase Drake count for the team`() {
        val match = Match(
            Team("Blue Team", Champion.Tank("Garen"), Champion.Fighter("Olaf"), Champion.Mage("Lux"), Champion.Marksman("Tristana"), Champion.Support("Morgana")),
            Team("Red Team", Champion.Tank("Singed"), Champion.Fighter("Udyr"), Champion.Mage("Zyra"), Champion.Marksman("Jinx"), Champion.Support("Leona"))
        )

        match.captureDrake(match.teamRed)

        assertEquals(1, match.teamRed.drakeKills)
    }

    @Test
    fun `Increasing team gold should update the total gold`() {
        val match = Match(
            Team("Blue Team", Champion.Tank("Ornn"), Champion.Fighter("Vi"), Champion.Mage("Syndra"), Champion.Marksman("Jinx"), Champion.Support("Lulu")),
            Team("Red Team", Champion.Tank("Malphite"), Champion.Fighter("Jarvan"), Champion.Mage("Veigar"), Champion.Marksman("Kai'Sa"), Champion.Support("Braum"))
        )

        match.increaseTeamGold(match.teamBlue, 500)

        assertEquals(500, match.teamBlue.totalGold)
    }

    @Test
    fun `Match should increase duration when time is added`() {
        val match = Match(
            Team("Blue Team", Champion.Tank("Ornn"), Champion.Fighter("Vi"), Champion.Mage("Syndra"), Champion.Marksman("Jinx"), Champion.Support("Lulu")),
            Team("Red Team", Champion.Tank("Malphite"), Champion.Fighter("Jarvan"), Champion.Mage("Veigar"), Champion.Marksman("Kai'Sa"), Champion.Support("Braum"))
        )

        match.incrementMatchTime(10)

        assertEquals(10, match.duration)
    }
}
