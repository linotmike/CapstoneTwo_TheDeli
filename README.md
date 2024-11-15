# DELI-cious Sandwich Shop 

![project license](https://img.shields.io/badge/license-MIT-blue.svg)

## Description
The Deli Ordering System is a Java-based console application that allows users to create and customize orders at a deli. Users can select sandwiches, drinks, and chips, with options to add toppings, sauces, and sides. The application features signature sandwiches with predefined ingredients as well as custom options, including various bread types, sandwich sizes, and toppings. Once an order is complete, users can review, edit, and finalize their selections, with an ASCII-style receipt generated at checkout. This project demonstrates object-oriented programming principles, such as inheritance for sandwich types and enums for sizes and topping types, making it modular and easy to expand.


## Table of Contents

- [Usage](#usage)
    - [Screenshot](#screenshot)
- [Features](#features)
- [How It Works](#How-It-Works)
- [Prerequisites](#prerequisites)
- [License](#license)

------------------

## Usage
To use this application:

1. Clone or download the project to your local machine.
2. Compile and run the main program using your IDE (e.g., IntelliJ IDEA).
3. Follow the on-screen menu prompts to navigate through the application's features, including adding, removing, and searching for vehicles in the dealership inventory.
## Screenshot

## Home Screen
![Screenshot ](./src/demo/Home.png)
## Order Screen
![Screenshot ](./src/demo/orderscreen.png)
## Add Sandwich Screen
![Screenshot ](./src/demo/sandwich.png)
![Screenshot ](./src/demo/sandwiches.png)
## Add Chips Screen
![Screenshot ](./src/demo/chips.png)
## Add Drinks Screen
![Screenshot ](./src/demo/drinks.png)
## Add Signature Screen
![Screenshot ](./src/demo/signature.png)
## Checkout Screen
![Screenshot ](./src/demo/checkout.png)
## View Receipt Screen
![Screenshot ](./src/demo/receipt.png)
## View Saved order screen
![Screenshot ](./src/demo/savedorder.png)







------------------

## Features
<ul>
<li>Add a Sandwich: Allows users to create a custom sandwich by choosing bread type, size, toppings, and optional toasting. Users can add various meats, cheeses, veggies, sauces, and sides to fully customize their sandwich
<li>Add Chips: Users can add a bag of chips to their order, each with a fixed price.
<li> Add a Drink: Enables users to select a drink size (Small, Medium, Large), type (e.g., Soda, Water, Juice), and flavor (e.g., Coca, Sprite, Pepsi). Prices vary depending on the drink size selected.
<li> Signature Sandwiches: Offers pre-defined signature sandwiches, like BLT and Philly Cheese Steak, each with unique ingredients and settings. These sandwiches are customizable, and users can add extra toppings or adjust the sandwich as desired
  <ul> 
<li>BLT </li>
<li>Philly Cheese Steak </li>
  </ul>
<li> Add Extra Toppings: Users can add additional meats, cheeses, and veggies to any sandwich, allowing for further customization. Extra toppings are available in various sizes (Small, Medium, Large), with corresponding prices for each size
<li> Checkout: Displays the current order details, including each item’s price and the total cost. Users can choose to confirm the order, cancel it, or edit it by returning to the ordering screen. Upon checkout, users can also view an ASCII art receipt of their order.
<li> Order Receipt with ASCII Art: The system generates a detailed ASCII art receipt summarizing the order, including sandwich details, drink, chips, and total cost. This receipt provides a clear and visually appealing representation of the order

## How It Works
1. Initialization: The application starts by setting up a menu-driven interface that guides users through available options.
2. User Interaction: Users can navigate the system to place orders, add items, view saved orders, or checkout. Each action prompts user input, ensuring an interactive and customizable experience.
3. File Management: Orders are managed with a DeliFileManager, which allows saving and loading order details to and from a CSV file, ensuring data persistence across sessions.
4. Checkout Process: The system calculates the total price, generates an ASCII-style receipt, and provides options to save the order, cancel, or edit before finalizing.
5. Error Handling: Built-in validations handle invalid inputs, ensuring a smooth and user-friendly experience.
6. Order Customization:
<ul>
<ul>
  <li>Users can customize sandwiches with various bread types, sizes, and toppings.</li>
  <li>Drinks and chips are easily added with clear prompts and validations.
  <li>Signature sandwiches can be selected and further customized with extra toppings.

</ul>
</ul>



------------------

## Prerequisites
<ul>
<li> Before running the DELI-cious Sandwich Shop application, ensure you have the following:
<ul>
<li>Java Development Kit (JDK) installed on your machine.</li>
<li>A command-line interface (CLI) to run the program.</li>
<li> A text editor or IDE (optional) to modify or view the source code.</li>
<li>The transactions.csv file in the project directory, or it will be created upon the first transaction.</li>
</ul>
</ul>

--------------------


## License
MIT License
