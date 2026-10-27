import java.util.Random;



public class Chance {

    // Member variables

    public boolean gain;

    public int val;

    public boolean getOutOfJail;

    public String text;

    

    // Constructor

    public Chance(boolean x, int y, boolean z) {

        // Initialize arrays for gain and loss texts

        String[] textGain = chanceTextforGain();

        String[] textLoss = chanceTextforLoss();

        // Generate a random index for selecting a text

        int rand = (int)(Math.random()*6);

        

        // If the card is for getting out of jail

        if (z) {

            getOutOfJail = z;

            text = "Get out of jail free - this card may be kept until needed";

            return;

        } else {

            // If the card involves gaining or losing money

            gain = x;

            val = y;

        }

        // Select a random gain or loss text

        if (gain) {

            text = textGain[rand];

        } else {

            text = textLoss[rand];

        }

    }

    

    // Method to generate chance texts for gaining money

    public static String[] chanceTextforGain() {

        // Create a string array with Monopoly Chance texts for gaining money

        String[] chanceTexts = {

            "Free Money",

            "Bank error in your favor",

            "You're a charity case", 

            "You started a fake GoFundMe and it works...", 

            "The stock market crashed in your favor",

            "You found money on the ground"

            // Add more Chance texts as needed

        };

        return chanceTexts;

    }

    

    // Method to generate chance texts for losing money

    public static String[] chanceTextforLoss() {

        // Create a string array with Monopoly Chance texts for losing money

        String[] chanceTexts = {

            "The IRS called",

            "You got mugged",

            "American Doctor fees",

            "Market crashed not so in your favor",

            "I don't make the rules but I can take your money.",

            "I definitely make the rules and I'm taking your money."

        };

        return chanceTexts;

    }



    // Method to get a random chance text

    public static String randomChance(String[] chanceTexts) {

        Random random = new Random();

        int randomIndex = random.nextInt(chanceTexts.length);

        return "Random Chance Card: " + chanceTexts[randomIndex];

    }

    

    // Getter method for getting the text of the chance card

    public String getText() {

        return text;

    }

}