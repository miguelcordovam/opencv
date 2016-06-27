package sample;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import org.opencv.core.Mat;
import org.opencv.core.MatOfByte;
import org.opencv.core.Size;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

import java.io.ByteArrayInputStream;

public class FilterController {

    @FXML
    private Button reset;

    @FXML
    private Button addNoise;

    @FXML
    private ToggleGroup filters;

    @FXML
    private RadioButton none;

    @FXML
    private ImageView image;

    private Mat originalImage;
    private Mat currentImage;

    public void initialize() {
        originalImage = Imgcodecs.imread("resources/machupicchu.jpg");
        image.setImage(mat2Image(originalImage));

        currentImage = originalImage.clone();
    }

    private Image mat2Image(Mat frame) {
        MatOfByte buffer = new MatOfByte();
        Imgcodecs.imencode(".png", frame, buffer);

        return new Image(new ByteArrayInputStream(buffer.toArray()));
    }


    public void addNoise(Event event) {
        String filter = ((RadioButton) filters.getSelectedToggle()).getText();
        Mat modifiedImage = originalImage.clone();

        switch (filter.toLowerCase()) {
            case "blur":
                Imgproc.blur(currentImage, modifiedImage, new Size(10.0, 10.0));
                break;
            case "gaussian":
                Imgproc.GaussianBlur(currentImage, modifiedImage, new Size(9.0, 9.0), 0);
                break;
            case "median":
                Imgproc.medianBlur(currentImage, modifiedImage, 9);
                break;
        }

        currentImage = modifiedImage.clone();
        image.setImage(mat2Image(modifiedImage));

    }

    public void onReset(Event event) {
        image.setImage(mat2Image(originalImage));
        currentImage = originalImage.clone();

        none.setSelected(true);
    }
}
