import java.awt.image.BufferedImage;

import java.io.File;

import java.io.IOException;

import java.util.ArrayList;

import java.util.HashMap;

import java.util.Map;

import javax.imageio.ImageIO;



public class User {

    String playerName;

    public int Bank;

    ArrayList<Property> Owned = new ArrayList<Property>();

    public int pos;

    public int gojCount;

    public int jailCount;

    public String decision;

    public int gameOver = 0;

    public BufferedImage playerPiece;



    // Constructor

    public User(String y, String f) {

        Bank = 1500;

        playerName = y;

        try {

            playerPiece = ImageIO.read(new File(f));

        } catch (IOException e) {

            e.printStackTrace();

        }

    }



    // Get current bank balance

    public int getBank() {

        return Bank;

    }



    // Add money to bank balance

    public void addBank(int x) {

        Bank += x;

    }



    // Deduct money from bank balance

    public void takeBank(int x) {

        Bank -= x;

        if (Bank <= 0) {

            gameOver = 1;

        }

    }



    // Buy a property

    public void Buy(Property x) {

        Owned.add(x);

    }



    // Check if it's possible to build on a property

    public boolean buildable(String x) {

        int fullSet = 3;

        int check = 0;

        if (x.equalsIgnoreCase("Blue") || x.equalsIgnoreCase("Purple")) {

            fullSet = 2;

        }

        for (int z = 0; z < Owned.size(); z++) {

            if (Owned.get(z).getName().equalsIgnoreCase(x)) {

                check++;

            }

        }

        if (!(check > fullSet)) {

            return false;

        }

        return true;

    }



    // Check the number of railroads owned and update their rents

    public void railroadCheck() {

        int count = 1;

        for (int x = 0; x < Owned.size(); x++) {

            if (Owned.get(x).getName().equalsIgnoreCase("Railroad")) {

                count++;

            }

        }

        if (count == 2) {

            for (int x = 0; x < Owned.size(); x++) {

                if (Owned.get(x).getName().equalsIgnoreCase("Railroad")) {

                    Owned.get(x).setRent(50);

                    ;

                }

            }

        }

        if (count == 3) {

            for (int x = 0; x < Owned.size(); x++) {

                if (Owned.get(x).getName().equalsIgnoreCase("Railroad")) {

                    Owned.get(x).setRent(100);

                    ;

                }

            }

        }

        if (count == 4) {

            for (int x = 0; x < Owned.size(); x++) {

                if (Owned.get(x).getName().equalsIgnoreCase("Railroad")) {

                    Owned.get(x).setRent(200);

                    ;

                }

            }

        }

    }



    // Print the properties owned by the player along with the count of each color

    public void printOwnedProperties() {

        HashMap<String, Integer> colorCount = new HashMap<>();



        System.out.println("Owned Properties for " + playerName + ":");



        for (Property property : Owned) {

            String color = property.getColor();

            if (colorCount.containsKey(color)) {

                colorCount.put(color, colorCount.get(color) + 1);

            } else {

                colorCount.put(color, 1);

            }



            System.out.println(property.getName() + " (Color: " + color + ")");

        }



        System.out.println("Color Count:");

        for (Map.Entry<String, Integer> entry : colorCount.entrySet()) {

            System.out.println(entry.getKey() + ": " + entry.getValue());

        }

    }



    // Get the count of turns spent in jail

    public int getJailCount() {

        return jailCount;

    }



    // Get the name of the player

    public String getName() {

        return playerName;

    }



    // Get the current position of the player

    public int getPos() {

        return pos;

    }



    // Set the position of the player

    public void setPosition(int i) {

        pos = i;

    }



    // Get the index of a user by name

    public static int getUser(User[] player, String name) {

        for (int x = 0; x < player.length; x++) {

            if (name.equalsIgnoreCase(player[x].getName())) {

                return x;

            }

        }

        return -1;

    }

    

    //return the property of the string searched

    

}