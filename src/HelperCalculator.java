public class HelperCalculator {
    public static double getMax(Double[] xes) {
        double max = xes[0];
        for (int i=1; i<xes.length; i++) {
            if (xes[i] > max) max = xes[i];
        }
        return max;
    }

    public static double getMin(Double[] xes) {
        double min = xes[0];
        for (int i=1; i<xes.length; i++) {
            if (xes[i] < min) min = xes[i];
        }
        return min;
    }
}
