package com.example.mamfoods.userscreen

import androidx.compose.runtime.MutableState
import com.example.mamfoods.R

data class OrderStatus(
    val driverName: String,
    val estimatedTime: String,
    val isOrderInProgress: Boolean
)

val dummyOrderStatus = OrderStatus(
    driverName = "Mr Kemplas",
    estimatedTime = "20 minutes on the way",
    isOrderInProgress = true
)

// Data class untuk menyimpan data profil
data class ProfileData(
    val name: String,
    val address: String,
    val phone: String,
    val email: String,
    val password: String
)

data class CartItem(
    val foodItem: FoodItem,
    val quantity: MutableState<Int> // Gunakan MutableState untuk quantity
)

// Data untuk makanan
data class FoodItem(
    val name: String,
    val restaurant: String,
    val price: Int,
    val img: Int,
    val description: String,
    val ingredients: List<String>
)

// Data untuk restoran
data class Restaurant(
    val name: String,
    val description: String,
    val img: Int, // Gambar restoran
    val menu: List<FoodItem>
)

// Data makanan
val foodItems = listOf(
    FoodItem(
        name = "Pancake",
        restaurant = "Cake Castle",
        price = 20000,
        img = R.drawable.menu,
        description = "Soft and fluffy pancakes topped with fresh fruit and whipped cream.",
        ingredients = listOf("Flour", "Eggs", "Milk", "Strawberries", "Whipped Cream")
    ),
    FoodItem(
        name = "Chocolate Cake",
        restaurant = "Cake Castle",
        price = 35000,
        img = R.drawable.menu,
        description = "A rich and moist chocolate cake topped with silky chocolate ganache.",
        ingredients = listOf("Flour", "Cocoa Powder", "Butter", "Eggs", "Sugar")
    ),
    FoodItem(
        name = "Matcha Latte",
        restaurant = "Wijie Resto",
        price = 15000,
        img = R.drawable.menu,
        description = "A creamy latte made with premium matcha powder and fresh milk.",
        ingredients = listOf("Matcha Powder", "Milk", "Sugar")
    ),
    FoodItem(
        name = "Iced Americano",
        restaurant = "Wijie Resto",
        price = 12000,
        img = R.drawable.menu,
        description = "A refreshing cold brew of rich espresso and water over ice.",
        ingredients = listOf("Espresso", "Water", "Ice")
    ),
    FoodItem(
        name = "Green Noodles",
        restaurant = "Warung Mie",
        price = 25000,
        img = R.drawable.menu,
        description = "Spinach-infused noodles served with savory broth and toppings.",
        ingredients = listOf("Spinach Noodles", "Chicken Broth", "Vegetables", "Egg")
    ),
    FoodItem(
        name = "Ayam Geprek",
        restaurant = "AGP",
        price = 25000,
        img = R.drawable.menu,
        description = "Crispy fried chicken smashed with spicy sambal, served with rice.",
        ingredients = listOf("Chicken", "Rice", "Sambal", "Cucumber")
    ),
    FoodItem(
        name = "Soft Serve Ice Cream",
        restaurant = "Mixue",
        price = 15000,
        img = R.drawable.menu,
        description = "Creamy vanilla soft serve in a crispy waffle cone.",
        ingredients = listOf("Milk", "Sugar", "Vanilla Extract")
    ),
    FoodItem(
        name = "Burger Deluxe",
        restaurant = "Burger Bros",
        price = 30000,
        img = R.drawable.menu,
        description = "A juicy beef patty with fresh lettuce, tomato, cheese, and a toasted bun.",
        ingredients = listOf("Beef Patty", "Bun", "Lettuce", "Tomato", "Cheese")
    ),
    FoodItem(
        name = "Margherita Pizza",
        restaurant = "Pizza Palace",
        price = 45000,
        img = R.drawable.menu,
        description = "Classic Italian pizza with fresh tomatoes, mozzarella, and basil.",
        ingredients = listOf("Pizza Dough", "Tomato Sauce", "Mozzarella", "Basil")
    ),
    FoodItem(
        name = "Spaghetti Bolognese",
        restaurant = "Pasta Perfecto",
        price = 40000,
        img = R.drawable.menu,
        description = "Traditional Italian pasta with rich and flavorful meat sauce.",
        ingredients = listOf("Spaghetti", "Ground Beef", "Tomato Sauce", "Parmesan")
    ),
    // Menu untuk restoran "Cake Castle"
    FoodItem(
        name = "Cupcake",
        restaurant = "Cake Castle",
        price = 15000,
        img = R.drawable.menu,
        description = "Delicious cupcakes with a variety of flavors and colorful frosting.",
        ingredients = listOf("Flour", "Sugar", "Butter", "Eggs", "Vanilla")
    ),

    // Menu untuk restoran "Wijie Resto"
    FoodItem(
        name = "Matcha Latte",
        restaurant = "Wijie Resto",
        price = 15000,
        img = R.drawable.menu,
        description = "A creamy latte made with premium matcha powder and fresh milk.",
        ingredients = listOf("Matcha Powder", "Milk", "Sugar")
    ),
    FoodItem(
        name = "Iced Americano",
        restaurant = "Wijie Resto",
        price = 12000,
        img = R.drawable.menu,
        description = "A refreshing cold brew of rich espresso and water over ice.",
        ingredients = listOf("Espresso", "Water", "Ice")
    ),
    FoodItem(
        name = "Cheesecake",
        restaurant = "Wijie Resto",
        price = 25000,
        img = R.drawable.menu,
        description = "A creamy and rich cheesecake served with a fresh strawberry topping.",
        ingredients = listOf("Cream Cheese", "Sugar", "Eggs", "Strawberries")
    ),

    // Menu untuk restoran "Pizza Palace"
    FoodItem(
        name = "Margherita Pizza",
        restaurant = "Pizza Palace",
        price = 45000,
        img = R.drawable.menu,
        description = "Classic Italian pizza with fresh tomatoes, mozzarella, and basil.",
        ingredients = listOf("Pizza Dough", "Tomato Sauce", "Mozzarella", "Basil")
    ),
    FoodItem(
        name = "Pepperoni Pizza",
        restaurant = "Pizza Palace",
        price = 50000,
        img = R.drawable.menu,
        description = "A fan-favorite pizza topped with spicy pepperoni and melted mozzarella.",
        ingredients = listOf("Pizza Dough", "Tomato Sauce", "Mozzarella", "Pepperoni")
    ),
    FoodItem(
        name = "Four Cheese Pizza",
        restaurant = "Pizza Palace",
        price = 55000,
        img = R.drawable.menu,
        description = "A delightful pizza topped with a blend of four types of cheese.",
        ingredients = listOf("Pizza Dough", "Mozzarella", "Parmesan", "Cheddar", "Blue Cheese")
    ),

    // Menu untuk restoran "AGP"
    FoodItem(
        name = "Ayam Geprek",
        restaurant = "AGP",
        price = 25000,
        img = R.drawable.menu,
        description = "Crispy fried chicken smashed with spicy sambal, served with rice.",
        ingredients = listOf("Chicken", "Rice", "Sambal", "Cucumber")
    ),
    FoodItem(
        name = "Geprek Mozzarella",
        restaurant = "AGP",
        price = 30000,
        img = R.drawable.menu,
        description = "Crispy fried chicken topped with melted mozzarella cheese and sambal.",
        ingredients = listOf("Chicken", "Rice", "Sambal", "Mozzarella")
    ),
    FoodItem(
        name = "Ayam Geprek Crispy",
        restaurant = "AGP",
        price = 27000,
        img = R.drawable.menu,
        description = "Extra crispy fried chicken with a side of sambal and fresh vegetables.",
        ingredients = listOf("Chicken", "Rice", "Sambal", "Vegetables")
    )
)

// Data restoran
val restaurants = listOf(
    Restaurant(
        name = "Cake Castle",
        description = "A cozy bakery specializing in cakes, pastries, and desserts.",
        img = R.drawable.toko,
        menu = foodItems.filter { it.restaurant == "Cake Castle" }
    ),
    Restaurant(
        name = "Wijie Resto",
        description = "A modern cafe offering premium coffee and tea beverages.",
        img = R.drawable.toko,
        menu = foodItems.filter { it.restaurant == "Wijie Resto" }
    ),
    Restaurant(
        name = "Warung Mie",
        description = "Famous for its unique and healthy noodle dishes.",
        img = R.drawable.toko,
        menu = foodItems.filter { it.restaurant == "Warung Mie" }
    ),
    Restaurant(
        name = "AGP",
        description = "A popular spot for traditional Indonesian dishes.",
        img = R.drawable.toko,
        menu = foodItems.filter { it.restaurant == "AGP" }
    ),
    Restaurant(
        name = "Mixue",
        description = "Affordable and delicious ice cream and dessert shop.",
        img = R.drawable.toko,
        menu = foodItems.filter { it.restaurant == "Mixue" }
    ),
    Restaurant(
        name = "Burger Bros",
        description = "Home of the best gourmet burgers in town.",
        img = R.drawable.toko,
        menu = foodItems.filter { it.restaurant == "Burger Bros" }
    ),
    Restaurant(
        name = "Pizza Palace",
        description = "Authentic Italian pizzas made with fresh ingredients.",
        img = R.drawable.toko,
        menu = foodItems.filter { it.restaurant == "Pizza Palace" }
    ),
    Restaurant(
        name = "Pasta Perfecto",
        description = "Specializing in homemade pasta dishes with traditional flavors.",
        img = R.drawable.toko,
        menu = foodItems.filter { it.restaurant == "Pasta Perfecto" }
    ),
)


