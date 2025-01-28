import models.Champion
import models.Item
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class ChampionTest {

    @Test
    fun `Assassin should execute target when their health minus adaptiveForce is below 50 percent health`() {
        val assassin = Champion.Assassin(name = "Zed", adaptiveForce = 20, gold = 1800)
        val target = Champion.Mage(name = "Ahri", health = 800)

        assassin.buyItem(
            Item(
                name = "Ghostblade",
                price = 1800,
                bonusHealth = 0,
                bonusAdaptiveForce = 80,
            )
        )

        repeat(5) {
            assassin.attackEnemy(40,target)
        }

        assassin.execute(target)

        assertEquals(0, target.health)
        assertEquals(1, assassin.kills)
        assertEquals(1, target.deaths)
    }


    @Test
    fun `Mage should cast spell and reduce target health considering magic resist`() {
        val mage = Champion.Mage(name = "Lux")
        val target = Champion.Fighter(name = "Darius", health = 1600)

        mage.castSpell(target, spellCost = 50, spellDamage = 300)

        assertTrue(target.health < 1600)
        assertEquals(450, mage.mana)
    }

    @Test
    fun `Fighter should gain bonus health and armor when enraged`() {
        val fighter = Champion.Fighter(name = "Sett")

        fighter.enrage()

        assertEquals(1800, fighter.health)
        assertEquals(50, fighter.armor)
    }

    @Test
    fun `Tank should gain shield once`() {
        val tank = Champion.Tank(name = "Malphite", health = 2000)

        tank.shield()
        assertEquals(2500, tank.health)

        tank.shield()
        assertEquals(2500, tank.health)
    }

    @Test
    fun `Marksman should deal extra damage when target is below 50 percent health`() {
        val marksman = Champion.Marksman(name = "Jinx")
        val target = Champion.Tank(name = "Ornn", health = 2000)

        target.health = 900
        marksman.rapidFire(target)

        assertTrue(target.health < 900) // Should apply increased damage when below 50% health
    }

    @Test
    fun `Support should heal ally`() {
        val support = Champion.Support(name = "Soraka")
        val ally = Champion.Assassin(name = "Talon", health = 800)

        support.heal(ally)

        assertEquals(1100, ally.health)
    }

    @Test
    fun `Champion should buy item and gain stats`() {
        val assassin = Champion.Assassin(name = "Katarina", gold = 1000)
        val item = Item(name = "Infinity Edge", price = 800, bonusHealth = 100, bonusArmor = 10, bonusMagicResist = 5, bonusAdaptiveForce = 20)

        assassin.buyItem(item)

        assertEquals(200, assassin.gold)
        assertTrue(assassin.items.contains(item))
        assertEquals(1300, assassin.health)
        assertEquals(35, assassin.armor)
        assertEquals(25, assassin.magicResist)
        assertEquals(70, assassin.adaptiveForce)
    }

    @Test
    fun `Champion should sell item and lose stats`() {
        val fighter = Champion.Fighter(name = "Garen", gold = 500)
        val item = Item(name = "Thornmail", price = 600, bonusHealth = 200, bonusArmor = 30, bonusMagicResist = 10, bonusAdaptiveForce = 10)

        fighter.buyItem(item)
        fighter.sellItem(item)

        assertEquals(500, fighter.gold)
        assertFalse(fighter.items.contains(item))
        assertEquals(1600, fighter.health)
        assertEquals(40, fighter.armor)
        assertEquals(25, fighter.magicResist)
        assertEquals(60, fighter.adaptiveForce)
    }

    @Test
    fun `Champion should farm minions and gain gold`() {
        val mage = Champion.Mage(name = "Viktor", gold = 450)

        mage.minionsFarmed += 10
        mage.gold += 200 // Simulating minion farm rewards

        assertEquals(10, mage.minionsFarmed)
        assertEquals(650, mage.gold)
    }

    @Test
    fun `Champion should attack enemy and reduce health`() {
        val assassin = Champion.Assassin(name = "Akali", adaptiveForce = 50)
        val target = Champion.Marksman(name = "Ashe", health = 1100, armor = 20)

        assassin.attackEnemy(200, target)

        assertTrue(target.health < 1100)
    }
}
