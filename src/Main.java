import java.io.IOException;
import java.util.Scanner;
import java.util.function.Function;

public class Main {

    public static void main(String[] args) throws IOException {
        Double[] option1 = {0.3, 1.8, 2.5, 4.2};
        Double[] option2 = {2.5, 4.5, 5.8, 6.2, 6.5, 7.7, 7.9, 8.3};
        Double[] option3 = {1.7, 3.2, 5.7, 7.9, 9.4, 12.2, 15.4, 16.8};
        Double[][] options = {option1, option2, option3};
        MyFunction function = new MyFunction();

        Reader reader = new Reader(new Scanner(System.in), options);
        reader.read( );
        Writer writer = new Writer();

        InterpolationByNewton interpolationByNewton = new InterpolationByNewton(reader.getChosenXes(), function);
        interpolationByNewton.findInterpolatedFunction();

        DrawingGraphics drawingGraphics = new DrawingGraphics(interpolationByNewton, function);
        drawingGraphics.drawFunctions();

        while (true) {
            double x = reader.readX();
            writer.write(interpolationByNewton.resultFunction(x));
        }

    }
}
