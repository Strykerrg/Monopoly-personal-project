import java.util.HashMap;
import java.util.Scanner;

public class game {

//image files for the board, the property ownership indicators, and the player pieces
    static String boardImage = "C:\\Users\\ravi_\\OneDrive\\Desktop\\MonopolyBoard.png";
    static String playerProp1 = "C:\\Users\\ravi_\\OneDrive\\Desktop\\player1owner.png";
    static String playerProp2 = "C:\\Users\\ravi_\\OneDrive\\Desktop\\player2.owner.png";
    static String playerProp3 = "C:\\Users\\ravi_\\OneDrive\\Desktop\\player3.owner.png";
    static String playerProp4 = "C:\\Users\\ravi_\\OneDrive\\Desktop\\player4.owner.png";
    static String[] playerPieces = {
            "C:\\Users\\ravi_\\OneDrive\\Desktop\\p1piece.png",
            "C:\\Users\\ravi_\\OneDrive\\Desktop\\p2.piece.png",
            "C:\\Users\\ravi_\\OneDrive\\Desktop\\p3.piece.png",
            "C:\\Users\\ravi_\\OneDrive\\Desktop\\p4piece.png"
        };

    public static void main(String[] args) {
        // Initialize chance cards
        Chance[] cards = chanceCards();
       
        // Scanner for player input
        Scanner into = new Scanner(System.in);
        System.out.println("The rules of this game are simple.");
        System.out.println("It is a version of monopoly where everything is the same except:");
        System.out.println("There are no mortgages, so you will run out of money faster;");
        System.out.println("You cannot trade entire sets of properties nor trade money for properties;");
        System.out.println("This is intended to streamline the gameplay and force players to make smarter decisions.");
        System.out.println();
        System.out.println("Player one (p1) is represented by a ship");
        System.out.println("Player two (p2) is represented by a car");
        System.out.println("Player three (p3) is represented by a penguin");
        System.out.println("Player four (p4) is represented by a duck");
        System.out.println();
        System.out.println("There is a color code to indicate which player owns which property:");
        System.out.println("Player one (p1) ownership of a property is represented by a red square.");
        System.out.println("Player two (p2) ownership of a property is represented by a blue square.");
        System.out.println("Player three (p3) ownership of a property is represented by a purple square.");
        System.out.println("Player four (p4) ownership of a property is represented by a yellow square.");
        System.out.println();
        System.out.println("How many players will be playing?");
        int playerNumber = into.nextInt();

        // Validate the number of players
        while (playerNumber < 1 || playerNumber > 4) {
            System.out.println("Invalid number of players");
            System.out.println("Please input a new number of players");
            playerNumber = into.nextInt();
        }


        // Initialize players
        User[] player = new User[playerNumber];
        for (int z = 0; z < playerNumber; z++) {
            player[z] = new User("p" + (z + 1), playerPieces[z]);
        }

        // Initialize image overlay and coordinates
        DisplayImage imageOverlay = new DisplayImage();
        HashMap<Integer, int[]> coord = Coordinates();

        // Initialize game properties
        Property[] x = gameBoard();
        int endgame = 0;
        //display image of board and pieces
        imageOverlay.displayOverlayImage(boardImage, playerProp1, playerProp2, playerProp3, playerProp4, coord, x, player);
       
        // Main game loop
        while (endgame == 0) {
            // Iterate through players' turns
            for (int i = 0; i < playerNumber; i++) {
                Turn(player, i, x, cards);
                imageOverlay.displayOverlayImage(boardImage, playerProp1, playerProp2, playerProp3, playerProp4, coord, x, player);
                System.out.println();
                System.out.println();
                System.out.println();
                System.out.println();
                if (gameOver(player)) {endgame++;}
            }
        }
    }
   
    
 // Method to handle paying rent to another player
    public static void takePlayerRent(User[] player, int turn, Property current) {
        int roll;
        // Check the current player's turn and deduct rent from their bank
        if (turn == 0) {
            if (!current.getOwner().equals("p1") && !current.getOwner().equals("")) {
                System.out.println("This property is owned by " + current.getOwner() + ", and you have to pay rent!");
                if (current.getName().equals("Electric Company") || current.getName().equals("Water Works")) {
                    System.out.println("This is a utilities property, therefore you must roll to pay rent");
                    roll = diceRoll();
                    System.out.println("You rolled a " + roll + " and have to pay $" + roll * 10);
                    player[0].takeBank(roll * 10);
                    if (current.owner.equals("p2")) {
                        player[1].addBank(roll * 10);
                    } else if (current.owner.equals("p3")) {
                        player[2].addBank(roll * 10);
                    } else if (current.owner.equals("p4")) {
                        player[3].addBank(roll * 10);
                    }
                } else {
                    System.out.println("There are " + current.houseNumber + " houses on this property.");
                    System.out.println("The rent for this property is $" + current.getRent());
                    player[0].takeBank(current.getRent());
                    if (current.owner.equals("p2")) {
                        player[1].addBank(current.getRent());
                    } else if (current.owner.equals("p3")) {
                        player[2].addBank(current.getRent());
                    } else if (current.owner.equals("p4")) {
                        player[3].addBank(current.getRent());
                    }
                }
            }
        } else if (turn == 1) {
            // Similar implementation for other players' turns
        if (!current.getOwner().equals("p2") && !current.getOwner().equals("")) {
                System.out.println("This property is owned by " + current.getOwner() + ", and you have to pay rent!");
                if (current.getName().equals("Electric Company") || current.getName().equals("Water Works")) {
                    System.out.println("This is a utilities property, therefore you must roll to pay rent");
                    roll = diceRoll();
                    System.out.println("You rolled a " + roll + " and have to pay $" + roll * 10);
                    player[1].takeBank(roll * 10);
                    if (current.owner.equals("p1")) {
                        player[0].addBank(roll * 10);
                    } else if (current.owner.equals("p3")) {
                        player[2].addBank(roll * 10);
                    } else if (current.owner.equals("p4")) {
                        player[3].addBank(roll * 10);
                    }
                } else {
                    System.out.println("There are " + current.houseNumber + " houses on this property.");
                    System.out.println("The rent for this property is $" + current.getRent());
                    player[1].takeBank(current.getRent());
                    if (current.owner.equals("p1")) {
                        player[0].addBank(current.getRent());
                    } else if (current.owner.equals("p3")) {
                        player[2].addBank(current.getRent());
                    } else if (current.owner.equals("p4")) {
                        player[3].addBank(current.getRent());
                    }
                }
            }

        } else if (turn == 2) {
            // Similar implementation for other players' turns
        if (!current.getOwner().equals("p3") && !current.getOwner().equals("")) {
                System.out.println("This property is owned by " + current.getOwner() + ", and you have to pay rent!");
                if (current.getName().equals("Electric Company") || current.getName().equals("Water Works")) {
                    System.out.println("This is a utilities property, therefore you must roll to pay rent");
                    roll = diceRoll();
                    System.out.println("You rolled a " + roll + " and have to pay $" + roll * 10);
                    player[2].takeBank(roll * 10);
                    if (current.owner.equals("p1")) {
                        player[0].addBank(roll * 10);
                    } else if (current.owner.equals("p2")) {
                        player[1].addBank(roll * 10);
                    } else if (current.owner.equals("p4")) {
                        player[3].addBank(roll * 10);
                    }
                } else {
                    System.out.println("There are " + current.houseNumber + " houses on this property.");
                    System.out.println("The rent for this property is $" + current.getRent());
                    player[2].takeBank(current.getRent());
                    if (current.owner.equals("p1")) {
                        player[0].addBank(current.getRent());
                    } else if (current.owner.equals("p2")) {
                        player[1].addBank(current.getRent());
                    } else if (current.owner.equals("p4")) {
                        player[3].addBank(current.getRent());
                    }
                }
            }

        } else if (turn == 3) {
            // Similar implementation for other players' turns
        if (!current.getOwner().equals("p4") && !current.getOwner().equals("")) {
                System.out.println("This property is owned by " + current.getOwner() + ", and you have to pay rent!");
                if (current.getName().equals("Electric Company") || current.getName().equals("Water Works")) {
                    System.out.println("This is a utilities property, therefore you must roll to pay rent");
                    roll = diceRoll();
                    System.out.println("You rolled a " + roll + " and have to pay $" + roll * 10);
                    player[3].takeBank(roll * 10);
                    if (current.owner.equals("p1")) {
                        player[0].addBank(roll * 10);
                    } else if (current.owner.equals("p2")) {
                        player[1].addBank(roll * 10);
                    } else if (current.owner.equals("p3")) {
                        player[2].addBank(roll * 10);
                    }
                } else {
                    System.out.println("There are " + current.houseNumber + " houses on this property.");
                    System.out.println("The rent for this property is $" + current.getRent());
                    player[3].takeBank(current.getRent());
                    if (current.owner.equals("p1")) {
                        player[0].addBank(current.getRent());
                    } else if (current.owner.equals("p2")) {
                        player[1].addBank(current.getRent());
                    } else if (current.owner.equals("p3")) {
                        player[2].addBank(current.getRent());
                    }
                }
            }

        }
    }

    //checks the status of the game to determine when the game ends.
    public static boolean gameOver(User[] player) {
    int count = 0;
    for (int x = 0; x<player.length; x++) {
    if (player[x].Bank<=0) {
    count++;
    }
    }
    // displays the win message to the winning player
    if (count==player.length-1) {
    System.out.println("The game is over, all other players except one has money left.");
    System.out.println("And to the winner, you know who you are, because you're the only not sad loser looking at this screen.");
    return true;
   
    }
    return false;
    }

    // Method to facilitate building on properties
    public static void build(User[] player, int turn, Property[] x) {
        int houseNumber;
        Scanner into = new Scanner(System.in);
        Scanner in = new Scanner(System.in);
        int[] buildArray = new int[3];
        int z = 0;
       
        // Select the property color to build on
        System.out.println("What color property would you like to build on?");
        player[turn].decision = in.nextLine();
        if (player[turn].buildable(player[turn].decision)) {
            // Choose the number of houses to build
            System.out.println("How many houses would you like to build?");
            houseNumber = into.nextInt();
            for (int count = 0; count < x.length; count++) {
                if (x[count].getColor().equalsIgnoreCase(player[turn].decision)) {
                    buildArray[z] = count;
                    z++;
                }
            }
            // Check if player can afford to build
            if (player[turn].Bank > (x[buildArray[0]].getHouseCost() * houseNumber * z)) {
                for (int j = 0; j < z; j++) {
                    x[buildArray[j]].houseNumber = houseNumber;
                }
                player[turn].takeBank(x[buildArray[0]].getHouseCost() * houseNumber * z);
            }
            z = 0;
        }
        else { System.out.println("You do not own all properties of this color, you cannot build here!");
        }
    }

    // Method to facilitate property trade between players
    public static boolean tradeProperties(User user1, User user2, Property propertyUser1, Property propertyUser2) {
        // Check if both users own the respective properties
        if (user1.Owned.contains(propertyUser1) && user2.Owned.contains(propertyUser2)) {
            // Perform the trade
            user1.Owned.remove(propertyUser1);
            user2.Owned.add(propertyUser1);

            user2.Owned.remove(propertyUser2);
            user1.Owned.add(propertyUser2);

            // Print a message indicating successful trade
            System.out.println(user1.playerName + " traded " + propertyUser1.getName() + " with " +
                user2.playerName + " for " + propertyUser2.getName());
            return true;
        } else {
            // Print a message indicating the properties are not owned by the respective users
            System.out.println("Trade failed. One or more properties not owned by the respective users.");
            return false;
        }
    }


    // Method to initiate a trade between players
    public static void playerTrade(User[] player, int turn, Property[] x) {
        Scanner in = new Scanner(System.in);
        String tradePlayer;
        String tradeProp1;
        String tradeProp2;
        Property tradeProperty1;
        Property tradeProperty2;

        // Prompting the user to select a player to trade with
        System.out.println("Who would you like to trade with?");
        tradePlayer = in.nextLine();

        // Asking the user what they want to trade
        System.out.println("What would you like to trade?");
        tradeProp1 = in.nextLine();

        // Asking the user what they want in exchange for their trade
        System.out.println("What would you like to trade for?");
        tradeProp2 = in.nextLine();

        // Confirming the trade offer with the other player
        System.out.println(tradePlayer + ", do you accept this trade offer? (yes or no)");
        String offer = in.nextLine();
        if (player[turn].buildable(tradeProp1) || player[getUser(player,tradePlayer)].buildable(tradeProp2)){
        System.out.println("You cannot trade these properties as one or more of them are part of a full set.");
        return;
        }
        // If the offer is accepted
        if (offer.equalsIgnoreCase("yes")) {
            System.out.println("Fantastic, " + player[turn].getName() + " has successfully traded " + tradeProp1 +
                    " for " + tradeProp2 + " from " + tradePlayer);
        }

        // Finding the properties involved in the trade
        tradeProperty1 = findProp(tradeProp1, x);
        tradeProperty2 = findProp(tradeProp2, x);

        // Executing the trade if both properties are owned by respective players
        if (tradeProperties(player[turn], player[getUser(player, tradePlayer)], tradeProperty1, tradeProperty2)) {
            x[tradeProperty1.place].owner = tradePlayer;
            x[tradeProperty2.place].owner = player[turn].getName();
        }
    }


   
       
    //simulates rolling two fair dice
    public static int diceRoll() {
        return (int) (Math.random()*5+1  + Math.random()*5+1);
    }

    //finds a property owned by a given user, returns null if property is not found
    public static Property findProp(String name, Property[] x) {
        for (int z = 0; z < x.length; z++) {
            if (x[z].name.equalsIgnoreCase(name)) {
                return x[z];
            }
        }
        return null;
    }
   

    // Method to find the index of a user in the player array
    public static int getUser(User[] player, String name) {
        for (int x = 0; x < player.length; x++) {
            if (name.equalsIgnoreCase(player[x].getName())) {
                return x;
            }
        }
        return -1;
    }



    // Method to create an array of Chance cards with random values
    public static Chance[] chanceCards() {
        Chance[] cards = new Chance[14]; // Assuming there are 14 chance cards
        for (int i = 0; i < 14; i++) {
            // Generating random values for each chance card
            cards[i] = new Chance(true, (int) (Math.random() * 200), Math.random() < 0.5);
        }
        return cards;
    }


   
   

   
 // Method for a player's turn
    public static int Turn(User[] player, int turn, Property[] x, Chance[] cards) {
        // Initialization of scanners
        Scanner in = new Scanner(System.in);
        Scanner into = new Scanner(System.in);
        Property current;
        int roll;
        int chance;
        int check=0;
        String tradePlayer; 
        String tradeProp1;
        String tradeProp2;
        Property tradeProperty1;
        Property tradeProperty2;

        DisplayImage imageOverlay = new DisplayImage();
        HashMap<Integer, int[]> coord = Coordinates();

        // Check if the player is not bankrupt
        if (player[turn].gameOver == 0) {
            System.out.println("You have $" + player[turn].Bank + " in your account");
            player[turn].printOwnedProperties();

            // Check if the player is not in jail
            if (player[turn].jailCount < 1) {
                roll = diceRoll();
                System.out.println(player[turn].getName() + " rolls a " + roll);
                // Check if the player passes GO
                if (player[turn].pos + roll > 39) {
                    player[turn].pos = (player[turn].pos + roll) - 39;
                    System.out.println("You passed GO, you got $200");
                    player[turn].addBank(200);
                } else
                    player[turn].pos += roll;
                imageOverlay.displayOverlayImage(boardImage, playerProp1, playerProp2, playerProp3, playerProp4, coord, x, player);
               
                current = x[player[turn].pos];
                System.out.println("You landed on " + current.getName());

                // Checks property status to see if it is buyable
                if (current.getBuyable() && current.owner.equals("")) {
                    System.out.println("This is a buyable property at $" + current.getCost() + ", would you like to purchase it? yes or no");
                    while (check==0) {
                    player[turn].decision = in.nextLine();
                    if (player[turn].decision.equalsIgnoreCase("yes")) {
                    x[player[turn].pos].purchase(player[turn], player[turn].Bank);
                    player[turn].Owned.add(x[player[turn].pos]);
                    System.out.println("You now have $" + player[turn].Bank + " in your bank account");
                    check++;
                    }else if (player[turn].decision.equalsIgnoreCase("no")) {
                    System.out.println("You have chosen not to buy "+current.getName());
                    check++;
                    }else {System.out.println("Your input was invalid, please try again.");}
                    }
                    check=0;
                    }
                //checks if the property is a chance spot and gives the player a chance card
                else if (current.getName().equalsIgnoreCase("Chance")) {
                    System.out.println("You landed on a chance, let's see what happens!");
                    chance = (int) (Math.random() * 10);
                    //chance card get out of jail
                    if (cards[chance].getOutOfJail) {
                        player[turn].gojCount += 1;
                        System.out.println(cards[chance].getText());
                    }
                    //chance card gain
                    else if (cards[chance].gain) {
                        player[turn].addBank(cards[chance].val);
                        System.out.println(cards[chance].getText());
                        System.out.println("You gained $" + cards[chance].val);
                    }
                    //chance card loss
                    else if (!cards[chance].gain) {
                        player[turn].takeBank(cards[chance].val);
                        System.out.println(cards[chance].getText());
                        System.out.println("You lost $" + cards[chance].val);
                    }
                }
                //checks if player landed on income tax and takes their money
                else if (current.getName().equalsIgnoreCase("Income Tax")) {
                    System.out.println("You landed on Income Tax.");
                    System.out.println("The IRS is calling... you lose $200");
                    player[turn].takeBank(200);
                }
                //checks if player landed on luxury tax and takes their money
                else if (current.getName().equalsIgnoreCase("Luxury Tax")) {
                    System.out.println("You landed on Luxury Tax, imagine suffering from success.");
                    System.out.println("You lose $75.");
                    player[turn].takeBank(75);
                }
                //checks for extraneous spots on the board that have no effect
                else if (current.getName().equalsIgnoreCase("Just Visiting")) {
                    System.out.println("You are visiting jail...don't make eye contact");
                } else if (current.getName().equalsIgnoreCase("Free Parking")) {
                    System.out.println("You're in free parking.");
                }
                //sends the player to jail if they land on go to jail
                else if (current.getName().equalsIgnoreCase("Jail")) {
                    System.out.println("You landed on Go To Jail.");
                    player[turn].jailCount = 3;
                    player[turn].pos = 10;
                    System.out.println("You are now in jail, you cannot roll the dice for 3 turns");
                } else if (!current.getOwner().equals(player[turn].getName()) && !current.getOwner().equals("")) {
                    takePlayerRent(player, turn, current);
                }
            }
           
            //bonus actions
           
            System.out.println();
            System.out.println();
            // Offer player options
            System.out.println("What else would you like to do for your turn? (Choose one)");
            System.out.println("1. Build");
            System.out.println("2. Trade");
            System.out.println("3. End Turn");
            while (check==0) {
            player[turn].decision = in.nextLine();
           
            // Execute player's decision
            if (player[turn].decision.equalsIgnoreCase("Build")) {
                build(player, turn, x);
                player[turn].decision = "End";
                check++;
            }
            if (player[turn].decision.equalsIgnoreCase("Trade")) {
                playerTrade(player, turn, x);
                player[turn].decision = "End";
                check++;
            }
            if (player[turn].decision.equalsIgnoreCase("End Turn")) {
                if (player[turn].jailCount > 0) {
                    player[turn].jailCount--;
                }
                check++;
            }
            else {System.out.println("Your input was invalid, please try again");}
            player[turn].railroadCheck();
            }
        }
        return turn++;
    }    
   
   
   
   
  //method to initialize the game board for the game and fill it with properties
    public static Property[] gameBoard() {
    //initialization of array
        Property[] gameBoard = new Property[40];
        Property GO = new Property(0, 0, "GO", 0, "GO", 0, 0, 0, 0, 0, 0, 0, 0);
       
        //brown property creation
        Property Mediterranean = new Property(60, 30, "Brown", 1, "Mediterranean Avenue", 2, 2, 50, 10, 30, 90, 160, 250);
        Property Baltic = new Property(60, 30, "Brown", 3, "Baltic Avenue", 4, 2, 50, 20, 60, 180, 320, 450);

        //light blue properties
        Property Oriental = new Property(100, 50, "Light Blue", 6, "Oriental Avenue", 6, 3, 50, 30, 90, 270, 400, 550);
        Property Vermont = new Property(100, 50, "Light Blue", 8, "Vermont Avenue", 6, 3, 50, 30, 90, 270, 400, 550);
        Property Connecticut = new Property(120, 60, "Light Blue", 9, "Connecticut Avenue", 8, 3, 50, 40, 100, 300, 450, 600);

        //pink properties
        Property Charles = new Property(140, 70, "Pink", 11, "St.Charles Place", 10, 3, 100, 50, 150, 450, 625, 750);
        Property States = new Property(140, 70, "Pink", 13, "States Avenue", 10, 3, 100, 50, 150, 450, 625, 750);
        Property Virginia = new Property(160, 80, "Pink", 14, "Virginia Avenue", 12, 3, 100, 60, 180, 500, 700, 950);

        //orange properties
        Property James = new Property(180, 90, "Orange", 16, "St. James Place", 14, 3, 100, 70, 200, 550, 750, 950);
        Property Tennessee = new Property(180, 90, "Orange", 18, "Tennessee Avenue", 14, 3, 100, 70, 200, 550, 750, 950);
        Property York = new Property(200, 100, "Orange", 19, "New York Avenue", 16, 3, 100, 80, 220, 600, 800, 1000);

        //red properties
        Property Kentucky = new Property(220, 110, "Red", 21, "Kentucky Avenue", 18, 3, 150, 90, 250, 700, 875, 1050);
        Property Indiana = new Property(220, 110, "Red", 23, "Indiana Avenue", 18, 3, 150, 90, 250, 700, 875, 1050);
        Property Illinois = new Property(240, 120, "Red", 24, "Illinois Avenue", 20, 3, 150, 100, 300, 750, 925, 1100);

        //yellow properties
        Property Atlantic = new Property(260, 130, "Yellow", 26, "Atlantic Avenue", 22, 3, 150, 110, 330, 800, 975, 1150);
        Property Ventnor = new Property(260, 130, "Yellow", 27, "Ventnor Avenue", 22, 3, 150, 110, 330, 800, 975, 1150);
        Property Marvin = new Property(280, 140, "Yellow", 29, "Marvin Gardens", 24, 3, 150, 120, 360, 850, 1025, 1200);

        //green properties
        Property Pacific = new Property(300, 150, "Green", 31, "Pacific Avenue", 26, 3, 200, 130, 390, 900, 1100, 1275);
        Property Carolina = new Property(300, 150, "Green,", 32, "North Carolina Avenue", 26, 3, 200, 130, 390, 900, 1100, 1275);
        Property Pennsylvania = new Property(320, 160, "Green", 34, "Pennsylvania Avenue", 28, 3, 200, 150, 450, 1000, 1200, 1400);

        //blue properties
        Property Park = new Property(350, 175, "Blue", 37, "Park Place", 35, 2, 200, 175, 500, 1100, 1300, 1500);
        Property Boardwalk = new Property(400, 200, "Blue", 39, "Boardwalk", 50, 2, 200, 200, 600, 1400, 1700, 2000);

        //railroads
        Property Reading = new Property(200, 100, "Railroad", 5, "Reading Railroad", 25, 4, 0, 25, 50, 100, 200, 0);
        Property PennsylvaniaRail = new Property(200, 100, "Railroad", 15, "Pennsylvania Railroad", 25, 4, 0, 25, 50, 100, 200, 0);
        Property BO = new Property(200, 100, "Railroad", 25, "B.&O. Railroad", 25, 4, 0, 25, 50, 100, 200, 0);
        Property Short = new Property(200, 100, "Railroad", 35, "Short Line", 25, 4, 0, 25, 50, 100, 200, 0);

        //utilities
        Property Electric = new Property(150, 75, "Utility", 12, "Electric Company", 0, 2, 0, 0, 0, 0, 0, 0);
        Property Water = new Property(150, 75, "Utility", 28, "Water Works", 0, 2, 0, 0, 0, 0, 0, 0);

        //chance cards
        Property Chance1 = new Property(0, 0, "Chance", 2, "Chance", 0, 0, 0, 0, 0, 0, 0, 0);
        Property Chance2 = new Property(0, 0, "Chance", 7, "Chance", 0, 0, 0, 0, 0, 0, 0, 0);
        Property Chance3 = new Property(0, 0, "Chance", 17, "Chance", 0, 0, 0, 0, 0, 0, 0, 0);
        Property Chance4 = new Property(0, 0, "Chance", 22, "Chance", 0, 0, 0, 0, 0, 0, 0, 0);
        Property Chance5 = new Property(0, 0, "Chance", 33, "Chance", 0, 0, 0, 0, 0, 0, 0, 0);
        Property Chance6 = new Property(0, 0, "Chance", 36, "Chance", 0, 0, 0, 0, 0, 0, 0, 0);

        //other corner spots
        Property JVisit = new Property(0, 0, "Open", 10, "Just Visiting", 0, 0, 0, 0, 0, 0, 0, 0);
        Property Parking = new Property(0, 0, "Open", 20, "Free PArking", 0, 0, 0, 0, 0, 0, 0, 0);
       
        //taxes
        Property IncomeTax = new Property(0, 0, "Tax", 4, "Income Tax", 0, 0, 0, 0, 0, 0, 0, 0);
        Property LuxuryTax = new Property(0, 0, "Tax", 38, "Luxury Tax", 0, 0, 0, 0, 0, 0, 0, 0);

        //jail spot
        Property Jail = new Property(0, 0, "Jail", 30, "Jail", 0, 0, 0, 0, 0, 0, 0, 0);

        //adding properties in their correct order to the data representation of the monopoly game board
        gameBoard[0] = GO;
        gameBoard[1] = Mediterranean;
        gameBoard[2] = Chance1;
        gameBoard[3] = Baltic;
        gameBoard[4] = IncomeTax;
        gameBoard[5] = Reading;
        gameBoard[6] = Oriental;
        gameBoard[7] = Chance2;
        gameBoard[8] = Vermont;
        gameBoard[9] = Connecticut;
        gameBoard[10] = JVisit;
        gameBoard[11] = Charles;
        gameBoard[12] = Electric;
        gameBoard[13] = States;
        gameBoard[14] = Virginia;
        gameBoard[15] = PennsylvaniaRail;
        gameBoard[16] = James;
        gameBoard[17] = Chance3;
        gameBoard[18] = Tennessee;
        gameBoard[19] = York;
        gameBoard[20] = Parking;
        gameBoard[21] = Kentucky;
        gameBoard[22] = Chance4;
        gameBoard[23] = Indiana;
        gameBoard[24] = Illinois;
        gameBoard[25] = BO;
        gameBoard[26] = Atlantic;
        gameBoard[27] = Ventnor;
        gameBoard[28] = Water;
        gameBoard[29] = Marvin;
        gameBoard[30] = Jail;
        gameBoard[31] = Pacific;
        gameBoard[32] = Carolina;
        gameBoard[33] = Chance5;
        gameBoard[34] = Pennsylvania;
        gameBoard[35] = Short;
        gameBoard[36] = Chance6;
        gameBoard[37] = Park;
        gameBoard[38] = LuxuryTax;
        gameBoard[39] = Boardwalk;

        return gameBoard;
    }
   
  //creates hashmap with integer key correlating to the position of each property with value indicating coordinates of each property
    public static HashMap<Integer, int[]> Coordinates(){
    HashMap<Integer, int[]> coord = new HashMap<>();

        // Add values to the HashMap with keys from 0 to 39
        coord.put(0, new int[]{585, 602});
        coord.put(1, new int[]{519, 602});
        coord.put(2, new int[]{468, 602});
        coord.put(3, new int[]{414, 602});
        coord.put(4, new int[]{360, 602});
        coord.put(5, new int[]{310, 602});
        coord.put(6, new int[]{258, 602});
        coord.put(7, new int[]{204, 602});
        coord.put(8, new int[]{152, 602});
        coord.put(9, new int[]{100, 602});
        coord.put(10, new int[]{10, 618});
        coord.put(11, new int[]{18, 519});
        coord.put(12, new int[]{18, 466});
        coord.put(13, new int[]{18, 414});
        coord.put(14, new int[]{18, 362});
        coord.put(15, new int[]{18, 310});
        coord.put(16, new int[]{18, 260});
        coord.put(17, new int[]{18, 206});
        coord.put(18, new int[]{18, 154});
        coord.put(19, new int[]{18, 102});
        coord.put(20, new int[]{30, 30});
        coord.put(21, new int[]{100, 18});
        coord.put(22, new int[]{152, 18});
        coord.put(23, new int[]{204, 18});
        coord.put(24, new int[]{256, 18});
        coord.put(25, new int[]{308, 18});
        coord.put(26, new int[]{360, 18});
        coord.put(27, new int[]{402, 18});
        coord.put(28, new int[]{464, 18});
        coord.put(29, new int[]{520, 18});
        coord.put(30, new int[]{585, 30});
        coord.put(31, new int[]{602, 100});
        coord.put(32, new int[]{602, 152});
        coord.put(33, new int[]{602, 206});
        coord.put(34, new int[]{602, 256});
        coord.put(35, new int[]{602, 308});
        coord.put(36, new int[]{602, 362});
        coord.put(37, new int[]{602, 414});
        coord.put(38, new int[]{602, 466});
        coord.put(39, new int[]{602, 520});
return coord;
    }
    }


