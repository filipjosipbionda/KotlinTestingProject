package models

data class Item(
    val name: String,
    val price: Long,
    val bonusHealth: Long = 0,
    val bonusArmor: Int = 0,
    val bonusMagicResist: Int = 0,
    val bonusAdaptiveForce: Int = 0
)

