import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import javax.imageio.ImageIO;

public class DisplayImage {

    // Reference to the previous frame
    public static JFrame previousFrame;

    // Method to display an overlay image on top of a background image
    public static void displayOverlayImage(String backgroundImagePath, String x, String y, String z, String a, HashMap<Integer, int[]> coord, Property[] gameboard, User[] player) {
        try {
            // Load the background image
            BufferedImage backgroundImage = ImageIO.read(new File(backgroundImagePath));

            // Load the overlay images
            BufferedImage overlayImage1 = ImageIO.read(new File(x));
            BufferedImage overlayImage2 = ImageIO.read(new File(y));
            BufferedImage overlayImage3 = ImageIO.read(new File(z));
            BufferedImage overlayImage4 = ImageIO.read(new File(a));

            // Create a Graphics object to draw on the background image
            Graphics2D g = backgroundImage.createGraphics();

            // Draw the overlay images on top of the background image at the specified coordinates
            for (int j = 0; j < gameboard.length; j++) {
                if (gameboard[j].getOwner().equalsIgnoreCase("p1")) {
                    g.drawImage(overlayImage1, coord.get(j)[0], coord.get(j)[1], null);
                }
                if (gameboard[j].getOwner().equalsIgnoreCase("p2")) {
                    g.drawImage(overlayImage2, coord.get(j)[0], coord.get(j)[1], null);
                }
                if (gameboard[j].getOwner().equalsIgnoreCase("p3")) {
                    g.drawImage(overlayImage3, coord.get(j)[0], coord.get(j)[1], null);
                }
                if (gameboard[j].getOwner().equalsIgnoreCase("p4")) {
                    g.drawImage(overlayImage4, coord.get(j)[0], coord.get(j)[1], null);
                }
            }

            // Draw the player pieces on their respective positions
            for (int h = 0; h < player.length; h++) {
                g.drawImage(player[h].playerPiece, coord.get(player[h].pos)[0], coord.get(player[h].pos)[1], null);
            }

            // Dispose of the Graphics object
            g.dispose();

            // Close the previous frame if it exists
            if (previousFrame != null) {
                previousFrame.dispose();
            }

            // Display the overlaid image using Swing
            JFrame frame = new JFrame();
            JLabel label = new JLabel(new ImageIcon(backgroundImage));
            frame.getContentPane().add(label, BorderLayout.CENTER);
            frame.pack();
            frame.setVisible(true);

            // Store the reference to the current frame
            previousFrame = frame;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}