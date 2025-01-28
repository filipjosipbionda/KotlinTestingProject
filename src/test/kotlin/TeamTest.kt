import models.Champion
import models.Team
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class TeamTest {

    @Test
    fun `Team should gain gold after capturing Baron`() {
        val team = Team("Blue Team", Champion.Tank("Ornn"), Champion.Assassin("Kha'Zix"), Champion.Mage("Syndra"), Champion.Marksman("Jinx"), Champion.Support("Lulu"))

        team.captureBaron()

        assertEquals(1, team.baronKills)
        assertEquals(300, team.totalGold)  // Baron buff dodaje zlato
    }

    @Test
    fun `Team should gain gold after destroying a tower`() {
        val team = Team("Red Team", Champion.Tank("Shen"), Champion.Fighter("Vi"), Champion.Mage("LeBlanc"), Champion.Marksman("Ezreal"), Champion.Support("Nami"))

        team.destroyTower()

        assertEquals(1, team.towersDestroyed)
        assertEquals(250, team.totalGold)  // Uništavanje tornja dodaje zlato
    }

    @Test
    fun `Team should farm minions and distribute gold among members`() {
        val team = Team("Green Team", Champion.Tank("Sion"), Champion.Fighter("Rengar"), Champion.Mage("Ahri"), Champion.Marksman("Vayne"), Champion.Support("Soraka"))

        team.farmMinions()

        assertEquals(250, team.totalGold)  // 50 zlata po članu = 250 ukupno
        assertEquals(500, team.top.gold)   // Svaki član dobija 50
        assertEquals(500, team.jungle.gold)
        assertEquals(500, team.mid.gold)
        assertEquals(500, team.adc.gold)
        assertEquals(500, team.support.gold)
    }

    @Test
    fun `Team should correctly count total kills`() {
        val team = Team("Blue Team",
            Champion.Tank("Ornn", kills = 2),
            Champion.Assassin("Kha'Zix", kills = 5),
            Champion.Mage("Syndra", kills = 3),
            Champion.Marksman("Jinx", kills = 7),
            Champion.Support("Lulu", kills = 1))

        assertEquals(18, team.teamKills())  // 2+5+3+7+1 = 18 killova
    }

    @Test
    fun `Team should win if they destroy 11 towers`() {
        val team = Team("Winning Team", Champion.Tank("Malphite"), Champion.Fighter("Jarvan"), Champion.Mage("Veigar"), Champion.Marksman("Kai'Sa"), Champion.Support("Braum"))

        repeat(11) { team.destroyTower() }

        assertTrue(team.hasWon())
    }

    @Test
    fun `Team should win if they capture 3 Barons`() {
        val team = Team("Baron Slayers", Champion.Tank("Ornn"), Champion.Assassin("Kha'Zix"), Champion.Mage("Syndra"), Champion.Marksman("Jinx"), Champion.Support("Lulu"))

        repeat(3) { team.captureBaron() }

        assertTrue(team.hasWon())
    }

    @Test
    fun `Team should not win if they have only 2 Barons and 10 towers`() {
        val team = Team("Almost Winners", Champion.Tank("Shen"), Champion.Fighter("Vi"), Champion.Mage("LeBlanc"), Champion.Marksman("Ezreal"), Champion.Support("Nami"))

        repeat(2) { team.captureBaron() }
        repeat(10) { team.destroyTower() }

        assertFalse(team.hasWon())  // Nema 3 Barona ili 11 tornjeva
    }
}
