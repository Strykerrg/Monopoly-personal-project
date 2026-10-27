// Property Class for Monopoly

public class Property {

    // Member variables

    int cost;

    public int mortgageVal;

    String color;

    int place;

    String name;

    String owner = "";

    boolean buyable = true;

    int rent;

    int setNumber;

    int houses;

    int houseCost;

    int houseRent1;

    int houseRent2;

    int houseRent3;

    int houseRent4;

    int hotelRent;

    int houseNumber;

    boolean mortgage;



    // Constructor

    public Property(int x, int y, String z, int b, String c, int k, int l, int m, int u, int i, int o, int p, int q) {

        cost = x;

        mortgageVal = y;

        color = z;

        place = b;

        name = c;

        rent = k;

        setNumber = l;

        houseCost = m;

        houseRent1 = u;

        houseRent2 = i;

        houseRent3 = o;

        houseRent4 = p;

        hotelRent = q;

        if (cost == 0) {

            buyable = false;

        }

    }



    // Getter method for buyable status

    public boolean getBuyable() {

        return buyable;

    }



    // Getter method for owner

    public String getOwner() {

        return owner;

    }



    // Getter method for rent based on house number

    public int getRent() {

        if (houseNumber == 1) {

            return houseRent1;

        }

        if (houseNumber == 2) {

            return houseRent2;

        }

        if (houseNumber == 3) {

            return houseRent3;

        }

        if (houseNumber == 4) {

            return houseRent4;

        }

        if (houseNumber == 5) {

            return hotelRent;

        } else {

            return rent;

        }

    }



    // Method to allow player to purchase the house

    public void purchase(User newOwner, int Bank) {

        if (Bank >= cost) {

            owner = newOwner.playerName;

            newOwner.Bank -= cost;

        } else {

            System.out.println("Insufficient funds");

        }

    }



    // Getter method for cost

    public int getCost() {

        return cost;

    }



    // Getter method for mortgage

    public int getMortgageVal() {

        return mortgageVal;

    }

    

    //getter method for mortgage state

    public boolean getMortgage() {

    	return mortgage;

    }



    // Getter method for house cost

    public int getHouseCost() {

        return houseCost;

    }



    // Getter method for place

    public int getPlace() {

        return place;

    }



    // Getter method for name

    public String getName() {

        return name;

    }



    // Setter method for owner

    public void setOwner(String x) {

        owner = x;

    }



    // Setter method for rent

    public void setRent(int x) {

        rent = x;

    }



    // Getter method for color

    public String getColor() {

        return color;

    }

    

    //mortages a property, giving money to the user but rendering the property useless

    public void giveMortgage(User x) {

    	if (mortgage==true) {

    		System.out.println("This property is already under mortgage, you cannot do that");

    	}else {

    		System.out.println("You have put "+name+" under mortgage, you have recieved $"+mortgageVal);

    		x.addBank(mortgageVal);

    		rent=0;

    		}

    }

    

    //takes the property our of mortgage, bringing back its original value

    public void resetMortgage(User x) {

    	if (mortgage==true && x.Bank>mortgageVal) {

    		mortgage=false;

    		System.out.println("Congratulations, you have recovered your property: "+name);

    	} else { System.out.println("You cannot take your property out of mortgage just yet");}

    }

    // Method to set the number of houses

    public void build(int x) {

        houseNumber = x;

    }

}