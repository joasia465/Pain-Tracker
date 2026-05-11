import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelReader;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import java.util.Map;
import java.util.HashMap;
import java.util.Objects;


public class BodyPartDetector
{

    private static final Map<String, Image> bodyPartMasks = new HashMap<>();

    static
    {
        bodyPartMasks.put("Głowa", new Image(Objects.requireNonNull(BodyPartDetector.class.getResourceAsStream("/resources/female_body/female_head.png"))));
        bodyPartMasks.put("Tułów", new Image(Objects.requireNonNull(BodyPartDetector.class.getResourceAsStream("/resources/female_body/female_torso.png"))));
        bodyPartMasks.put("Lewa ręka", new Image(Objects.requireNonNull(BodyPartDetector.class.getResourceAsStream("/resources/female_body/female_left_arm.png"))));
        bodyPartMasks.put("Prawa ręka", new Image(Objects.requireNonNull(BodyPartDetector.class.getResourceAsStream("/resources/female_body/female_right_arm.png"))));
        bodyPartMasks.put("Lewa noga", new Image(Objects.requireNonNull(BodyPartDetector.class.getResourceAsStream("/resources/female_body/female_left_leg.png"))));
        bodyPartMasks.put("Prawa noga", new Image(Objects.requireNonNull(BodyPartDetector.class.getResourceAsStream("/resources/female_body/female_right_leg.png"))));
    }

    public static String getBodyPart(double x, double y, double imageWidth, double imageHeight)
    {
        for (Map.Entry<String, Image> entry : bodyPartMasks.entrySet())
        {
            String part = entry.getKey();
            Image mask = entry.getValue();
            PixelReader reader = mask.getPixelReader();
            if (reader == null) continue;

            int[] scaled = scaleCoordinates(x, y, imageWidth, imageHeight, mask);
            int imageX = scaled[0];
            int imageY = scaled[1];

            if (imageX < 0 || imageY < 0 || imageX >= mask.getWidth() || imageY >= mask.getHeight()) continue;

            Color color = reader.getColor(imageX, imageY);
            if (color.getOpacity() > 0.1) return part;
        }
        return "Poza ciałem";
    }

    private static int[] scaleCoordinates(double x, double y, double imageWidth, double imageHeight, Image mask)
    {
        double scaleX = mask.getWidth() / imageWidth;
        double scaleY = mask.getHeight() / imageHeight;
        int imageX = (int) (x * scaleX);
        int imageY = (int) (y * scaleY);
        return new int[] { imageX, imageY };
    }

    public static double[] getRelativeClick(MouseEvent e, ImageView imageView)
    {
        double relativeX = e.getX() - imageView.getLayoutX();
        double relativeY = e.getY() - imageView.getLayoutY();
        return new double[] { relativeX, relativeY };
    }

}
