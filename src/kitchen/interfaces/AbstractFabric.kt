package kitchen.interfaces


interface AbstractFabric {
    fun createFirstDish(): FirstDish
    fun createMainDish(): MainDish
    fun createDrink(): Drink
}