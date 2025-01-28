package models

class Team(
    val name: String,
    val top: Champion,
    val jungle: Champion,
    val mid: Champion,
    val adc: Champion,
    val support: Champion,
    var totalGold: Long = 0,
    var baronKills: Int = 0,
    var drakeKills: Int = 0,
    var towersDestroyed: Int = 0
) {
    fun captureBaron() {
        baronKills++
        addGold(300)
    }

    fun captureDrake() {
        drakeKills++
    }

    fun destroyTower() {
        towersDestroyed++
        addGold(250)
    }

    fun addGold(amount: Long) {
        totalGold += amount
    }

    fun farmMinions() {
        val goldFromMinions = 50
        top.gold += goldFromMinions
        jungle.gold += goldFromMinions
        mid.gold += goldFromMinions
        adc.gold += goldFromMinions
        support.gold += goldFromMinions
        addGold((5 * goldFromMinions).toLong())
    }

    fun teamKills(): Int {
        return top.kills + jungle.kills + mid.kills + adc.kills + support.kills
    }

    fun hasWon(): Boolean {
        return towersDestroyed >= 11 || baronKills >= 3
    }
}
