package kitchen.classes.fabrics

import kitchen.classes.food.sweet.Coffee
import kitchen.classes.food.sweet.MilkSoup
import kitchen.classes.food.sweet.Pancakes
import kitchen.interfaces.AbstractFabric
import kitchen.interfaces.Drink
import kitchen.interfaces.FirstDish
import kitchen.interfaces.MainDish

class SweetsFabric : AbstractFabric {
    override fun createFirstDish(): FirstDish {
        return MilkSoup()
    }

    override fun createMainDish(): MainDish {
        return Pancakes()
    }

    override fun createDrink(): Drink {
        return Coffee()
    }
}