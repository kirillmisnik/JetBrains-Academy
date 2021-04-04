package machine;

import java.util.Scanner;

public class CoffeeMachine {

    final public static Scanner scanner = new Scanner(System.in);

    public static CoffeeTypes coffeeType;

    int water = 400;
    int milk = 540;
    int coffee = 120;
    int cups = 9;
    int money = 550;

    public static void main(String[] args) {

        CoffeeMachine coffeeMachine = new CoffeeMachine();

        coffeeMachine.startWorking();

    }

    public void startWorking() {

        boolean exit = false;

        while (!exit) {
            System.out.println("Write action (buy, fill, take, remaining, exit):");

            String request = scanner.next();

            switch (request) {
                case "fill":
                    fillCoffeeMachine();
                    break;
                case "buy":
                    System.out.println("\nWhat do you want to buy?" +
                            "1 - espresso, 2 - latte, 3 - cappuccino, back - to main menu:");
                    String coffeeName = scanner.next();

                    switch (coffeeName) {
                        case "1":
                            coffeeType = CoffeeTypes.ESPRESSO;
                            break;
                        case "2":
                            coffeeType = CoffeeTypes.LATTE;
                            break;
                        case "3":
                            coffeeType = CoffeeTypes.CAPPUCCINO;
                            break;
                    }

                    if (coffeeName.equals("back")) {break;}

                    buyCoffee(coffeeType);

                    break;
                case "take":
                    takeMoney();
                    break;
                case "remaining":
                    remainingResources();
                    break;
                case "exit":
                    exit = true;
                    break;
            }
        }
    }

    public void fillCoffeeMachine() {
        System.out.println("Write how many ml of water do you want to add: ");
        this.water += scanner.nextInt();
        System.out.println("Write how many ml of milk do you want to add: ");
        this.milk += scanner.nextInt();
        System.out.println("Write how many grams of coffee beans do you want to add: ");
        this.coffee += scanner.nextInt();
        System.out.println("Write how many disposable cups of coffee do you want to add:");
        this.cups += scanner.nextInt();
    }

    public void buyCoffee(CoffeeTypes coffeeType) {
        if (this.water >= coffeeType.getWater() && this.milk >= coffeeType.getMilk()
                && this.coffee >= coffeeType.getCoffee() && this.cups >= 1) {
            this.water -= coffeeType.getWater();
            this.milk -= coffeeType.getMilk();
            this.coffee -= coffeeType.getCoffee();
            this.cups--;
            this.money += coffeeType.getMoney();
            System.out.println("I have enough resources, making you a coffee!\n");
        } else {
            System.out.println("Sorry, not enough resources!\n");
        }
    }

    public void takeMoney() {
        System.out.printf("\nI gave you $%d\n\n", this.money);
        this.money = 0;
    }

    public void remainingResources() {
        System.out.println("\nThe coffee machine has:");
        System.out.printf("%d of water\n", this.water);
        System.out.printf("%d of milk\n", this.milk);
        System.out.printf("%d of coffee beans\n", this.coffee);
        System.out.printf("%d of disposable cups\n", this.cups);
        System.out.printf("$%d of money\n\n", this.money);
    }

    public enum CoffeeTypes {
        ESPRESSO(250, 0, 16, 4),
        LATTE(350, 75, 20, 7),
        CAPPUCCINO(200, 100, 12, 6);

        int water;
        int milk;
        int coffee;
        int money;

        CoffeeTypes(int water, int milk, int coffee, int money) {
            this.water = water;
            this.milk = milk;
            this.coffee = coffee;
            this.money = money;
        }

        public int getWater() {
            return water;
        }

        public int getMilk() {
            return milk;
        }

        public int getCoffee() {
            return coffee;
        }

        public int getMoney() {
            return money;
        }

    }
}
