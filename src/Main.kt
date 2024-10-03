import kitchen.TypesOfFood
import kitchen.interfaces.AbstractFabric

fun main() {
    println("Choose your menu:")
    for (type in TypesOfFood.entries) {
        println(type)
    }

    var type = readlnOrNull() ?: ""
    val fabric: AbstractFabric

    while (true) {
        when (type) {
            TypesOfFood.Salty.name -> {
                println("You choose Salty")
                fabric = kitchen.classes.fabrics.SaltyFabric()
                break
            }
            TypesOfFood.Sweet.name -> {
                println("You choose Sweet")
                fabric = kitchen.classes.fabrics.SweetsFabric()
                break
            }
            TypesOfFood.Vegan.name -> {
                println("You choose Vegan")
                fabric = kitchen.classes.fabrics.VeganFabric()
                break
            }
            else -> {
                println("Invalid option. Please choose Salty, Sweet, or Vegan.")
                type = readlnOrNull() ?: ""
            }
        }
    }

    val firstDish = fabric.createFirstDish()
    val mainDish = fabric.createMainDish()
    val drink = fabric.createDrink()

    println("Your menu:")
    println(firstDish)
    println(mainDish)
    println(drink)

}