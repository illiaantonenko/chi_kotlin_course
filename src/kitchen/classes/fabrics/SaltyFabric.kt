package kitchen.classes.fabrics

import kitchen.classes.food.salty.Shrimps
import kitchen.classes.food.salty.SoupWithSalmon
import kitchen.classes.food.salty.Wine
import kitchen.interfaces.AbstractFabric
import kitchen.interfaces.Drink
import kitchen.interfaces.FirstDish
import kitchen.interfaces.MainDish

class SaltyFabric : AbstractFabric {
    override fun createFirstDish(): FirstDish {
        return SoupWithSalmon()
    }

    override fun createMainDish(): MainDish {
        return Shrimps()
    }

    override fun createDrink(): Drink {
        return Wine()
    }
}