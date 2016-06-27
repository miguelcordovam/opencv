package blur;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import org.opencv.core.Mat;
import org.opencv.core.MatOfByte;
import org.opencv.core.Size;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

import java.io.ByteArrayInputStream;

public class ImageController {

    @FXML
    private Slider blurLevel;

    @FXML
    private ImageView imageFrame;

    @FXML
    private Label levelValue;

    public void initialize() {

        final Mat image = Imgcodecs.imread("resources/machupicchu.jpg");
        imageFrame.setImage(mat2Image(image));
        levelValue.setText("0");

        blurLevel.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {

                levelValue.setText(String.valueOf(newValue.intValue()));

                Mat blurredImg = image.clone();
                Mat currentMat = image.clone();

                for(int i = 0; i< newValue.intValue(); i++) {
                    Imgproc.blur(currentMat, blurredImg, new Size(10.0, 10.0));
                    currentMat = blurredImg.clone();
                }

                imageFrame.setImage(mat2Image(blurredImg));
            }
        });
    }

    private Image mat2Image(Mat frame)
    {
        MatOfByte buffer = new MatOfByte();
        Imgcodecs.imencode(".png", frame, buffer);

        return new Image(new ByteArrayInputStream(buffer.toArray()));
    }
}