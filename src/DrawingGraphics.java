import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.ChartUtils;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.chart.util.ShapeUtils;
import org.jfree.data.statistics.HistogramDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.knowm.xchart.BitmapEncoder;
import org.knowm.xchart.QuickChart;
import org.knowm.xchart.SwingWrapper;
import org.knowm.xchart.XYChart;

import javax.swing.*;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.io.File;
import java.io.IOException;

import static java.lang.Math.*;

public class DrawingGraphics {

    Interpolation interpolation;
    Function function;


    public DrawingGraphics (Interpolation interpolation, Function function) {
        this.interpolation = interpolation;
        this.function = function;
    }

    public void drawFunctions ( ) throws IOException {

        double[] xData = new double[100];
        double[] yData = new double[100];
        double[] yData2 = new double[100];

        XYSeries series1 = new XYSeries("xySeries");
        XYSeries series2 = new XYSeries("xySeries2");

        double diff = (interpolation.getXes()[interpolation.getXes().length-1] - interpolation.getXes()[0]+10) / 100;
        double x = interpolation.getXes()[0] - 5;
        xData[0] = x;
        yData[0] = interpolation.resultFunction(x);
        yData2[0] = function.getValueOfFunction(x);
        for (int i = 1; i < 100; i++) {
            x += diff;
            xData[i] = x;
            yData[i] = interpolation.resultFunction(x);
            yData2[i] = function.getValueOfFunction(x);
            series1.add(xData[i], yData[i]);
            series2.add(xData[i], yData2[i]);
        }

        XYSeriesCollection dataset = new XYSeriesCollection();
        dataset.addSeries(series1);
        dataset.addSeries(series2);

        JFreeChart chart = ChartFactory.createXYLineChart(
                "y(x)", // chart title
                "x", // x axis label
                "y", // y axis label
                dataset, // data
                PlotOrientation.VERTICAL,
                false, // include legend
                false, // tooltips
                false // urls
        );

        XYPlot plot = chart.getXYPlot();
        XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
        renderer.setSeriesPaint(0, Color.BLACK);
        renderer.setSeriesStroke(0, new BasicStroke(5.0f));
        renderer.setSeriesShape(0, new Ellipse2D.Double(-2.5,-2.5,5,5)) ;
        plot.setDataset(0, dataset);

        for (int i=0; i<interpolation.getXes().length; i++) {
            XYSeries series = new XYSeries("xySeries3");
            XYSeriesCollection pointDataset = new XYSeriesCollection();
            pointDataset.addSeries(series);
            series.add(interpolation.getXes()[i], interpolation.getYes()[i]);
            plot.setDataset(i+1, pointDataset);
            plot.setRenderer(i+1, renderer);
        }


        ChartFrame frame = new ChartFrame("my picture", chart);
        frame.pack();
        frame.setVisible(true);

    }



}
