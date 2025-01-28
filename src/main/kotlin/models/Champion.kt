package models

import kotlin.math.max
import kotlin.random.Random

sealed class Champion(
    open val name: String,
    open var health: Long,
    open var armor: Int = 30,
    open var magicResist: Int = 20,
    open var kills: Int = 0,
    open var deaths: Int = 0,
    open var assists: Int = 0,
    open var minionsFarmed: Int = 0,
    open var gold: Long = 450,
    open var items: MutableList<Item> = mutableListOf(),
    open val role: PlayerRole? = null,
    open var adaptiveForce: Int = 0
) {
    fun attackEnemy(spellDamage: Long, enemy: Champion) {
        val isCritical = Random.nextDouble() < 0.2
        val totalDamage = (spellDamage + this.adaptiveForce)
        val damageDealt = if (isCritical) (totalDamage * 1.5).toLong() else totalDamage
        val reducedDamage = max(damageDealt - enemy.armor, 0)

        if (reducedDamage >= enemy.health) {
            this.kills++
            enemy.deaths++
            enemy.health = 0
        } else {
            enemy.health -= reducedDamage
        }
    }

    fun buyItem(item: Item) {
        if (gold >= item.price && items.size < 6) {
            gold -= item.price
            items.add(item)
            applyItemEffects(item)
        }
    }

    fun sellItem(item: Item) {
        if (items.contains(item)) {
            gold += (0.5 * item.price).toLong()
            items.remove(item)
            removeItemEffects(item)
        }
    }

    private fun applyItemEffects(item: Item) {
        this.health += item.bonusHealth
        this.armor += item.bonusArmor
        this.magicResist += item.bonusMagicResist
        this.adaptiveForce += item.bonusAdaptiveForce
    }

    private fun removeItemEffects(item: Item) {
        this.health -= item.bonusHealth
        this.armor -= item.bonusArmor
        this.magicResist -= item.bonusMagicResist
        this.adaptiveForce -= item.bonusAdaptiveForce
    }

    data class Assassin(
        override val name: String,
        override var health: Long = 1200,
        override var armor: Int = 25,
        override var magicResist: Int = 20,
        override var kills: Int = 0,
        override var deaths: Int = 0,
        override var assists: Int = 0,
        override var minionsFarmed: Int = 0,
        override var gold: Long = 450,
        override var items: MutableList<Item> = mutableListOf(),
        override val role: PlayerRole? = null,
        override var adaptiveForce: Int = 50
    ) : Champion(name, health, armor, magicResist, kills, deaths, assists, minionsFarmed, gold, items, role, adaptiveForce) {
        fun execute(target: Champion) {
            val threshold = target.health * 0.5
            if (target.health  - this.adaptiveForce <= threshold) {
                target.health = 0
                this.kills++
                target.deaths++
            }
        }
    }

    data class Mage(
        override val name: String,
        override var health: Long = 1000,
        override var armor: Int = 20,
        override var magicResist: Int = 30,
        override var kills: Int = 0,
        override var deaths: Int = 0,
        override var assists: Int = 0,
        override var minionsFarmed: Int = 0,
        override var gold: Long = 450,
        override var items: MutableList<Item> = mutableListOf(),
        override val role: PlayerRole? = null,
        override var adaptiveForce: Int = 40,
        var mana: Long = 500
    ) : Champion(name, health, armor, magicResist, kills, deaths, assists, minionsFarmed, gold, items, role, adaptiveForce) {
        fun castSpell(target: Champion, spellCost: Long, spellDamage: Long) {
            if (mana >= spellCost) {
                mana -= spellCost
                val magicResistFactor = 100.0 / (100 + target.magicResist)
                val reducedDamage = ((spellDamage + adaptiveForce) * magicResistFactor).toLong()

                attackEnemy(reducedDamage, target)
            }
        }
    }

    data class Fighter(
        override val name: String,
        override var health: Long = 1600,
        override var armor: Int = 40,
        override var magicResist: Int = 25,
        override var kills: Int = 0,
        override var deaths: Int = 0,
        override var assists: Int = 0,
        override var minionsFarmed: Int = 0,
        override var gold: Long = 450,
        override var items: MutableList<Item> = mutableListOf(),
        override val role: PlayerRole? = null,
        override var adaptiveForce: Int = 60
    ) : Champion(name, health, armor, magicResist, kills, deaths, assists, minionsFarmed, gold, items, role, adaptiveForce) {
        fun enrage() {
            this.health += 200
            this.armor += 10
        }
    }

    data class Tank(
        override val name: String,
        override var health: Long = 2000,
        override var armor: Int = 50,
        override var magicResist: Int = 40,
        override var kills: Int = 0,
        override var deaths: Int = 0,
        override var assists: Int = 0,
        override var minionsFarmed: Int = 0,
        override var gold: Long = 450,
        override var items: MutableList<Item> = mutableListOf(),
        override val role: PlayerRole? = null,
        override var adaptiveForce: Int = 30
    ) : Champion(name, health, armor, magicResist, kills, deaths, assists, minionsFarmed, gold, items, role, adaptiveForce) {
        private var shieldAvailable = true

        fun shield() {
            if (shieldAvailable) {
                this.health += 500
                shieldAvailable = false
            }
        }
    }

    data class Marksman(
        override val name: String,
        override var health: Long = 1100,
        override var armor: Int = 20,
        override var magicResist: Int = 15,
        override var kills: Int = 0,
        override var deaths: Int = 0,
        override var assists: Int = 0,
        override var minionsFarmed: Int = 0,
        override var gold: Long = 450,
        override var items: MutableList<Item> = mutableListOf(),
        override val role: PlayerRole? = null,
        override var adaptiveForce: Int = 55,
        var attackSpeed: Double = 1.5
    ) : Champion(name, health, armor, magicResist, kills, deaths, assists, minionsFarmed, gold, items, role, adaptiveForce) {
        fun rapidFire(target: Champion) {
            val damage = if (target.health < target.health * 0.5) (300 + adaptiveForce) else (200 + adaptiveForce)
            attackEnemy(damage.toLong(), target)
        }
    }

    data class Support(
        override val name: String,
        override var health: Long = 1300,
        override var armor: Int = 30,
        override var magicResist: Int = 35,
        override var kills: Int = 0,
        override var deaths: Int = 0,
        override var assists: Int = 0,
        override var minionsFarmed: Int = 0,
        override var gold: Long = 450,
        override var items: MutableList<Item> = mutableListOf(),
        override val role: PlayerRole? = null,
        override var adaptiveForce: Int = 20
    ) : Champion(name, health, armor, magicResist, kills, deaths, assists, minionsFarmed, gold, items, role, adaptiveForce) {
        fun heal(ally: Champion) {
            val healedAmount = 300
            ally.health = minOf(ally.health + healedAmount, 2000)
        }
    }
}
