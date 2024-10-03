package kitchen.classes.fabrics

import kitchen.classes.food.vegan.Matcha
import kitchen.classes.food.vegan.VeganSoup
import kitchen.classes.food.vegan.Vegetables
import kitchen.interfaces.AbstractFabric
import kitchen.interfaces.Drink
import kitchen.interfaces.FirstDish
import kitchen.interfaces.MainDish

class VeganFabric : AbstractFabric {
    override fun createFirstDish(): FirstDish {
        return VeganSoup()
    }

    override fun createMainDish(): MainDish {
        return Vegetables()
    }

    override fun createDrink(): Drink {
        return Matcha()
    }
}