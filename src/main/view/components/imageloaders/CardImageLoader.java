package view.components.imageloaders;

import javafx.scene.image.Image;
import view.components.card.model.CardNumber;
import view.components.card.model.Suit;

import java.io.FileNotFoundException;

public class CardImageLoader extends ImageLoader {

    public static final String assetsFolder = "assets";
    public static final String pngFolder = "png";

    private CardImageLoader() {
    }

    public static Image LoadCardImage(Suit suit, CardNumber num) {
        final String imagePath = String.format("%s/%s/%s_of_%s.png", assetsFolder, pngFolder, CardNumber.NumberToString(num), suit.name().toLowerCase());
        try {
            return loadImageFromFileSystem(imagePath);
        } catch (FileNotFoundException fnfe) {
            return null;
        }
    }

}
