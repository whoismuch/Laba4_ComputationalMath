public class InterpolationByNewton implements Interpolation {

    Double[] Xes;
    Double[] Yes;
    Double[] Bes;
    Function function;


    public Double[] getYes ( ) {
        return Yes;
    }

    @Override
    public Double[] getXes ( ) {
        return Xes;
    }

    public InterpolationByNewton (Double[] Xes, Function function) {
        this.Xes = Xes;
        Yes = new Double[Xes.length];
        Bes = new Double[Xes.length];
        this.function = function;
    }

    @Override
    public void findInterpolatedFunction() {
        findYes();
        findBes();
    }

    public double findTrash(int a) {
        double sumOfTrash = 0;
        for (int i=2; i<=a; i++) {
            double trash = 1;
            int p = 0;
            while (i - p >= 2) {
                trash *= (Xes[a] - Xes[p]);
                p++;
            }
            trash *= Bes[i-1];
            sumOfTrash += trash;
        }
        return sumOfTrash;
    }

    public double findDenominator(int a) {
        double denominator = 1;
        int p = 0;
        while (a - p >= 1) {
            denominator *= (Xes[a] - Xes[p]);
            p++;
        }
        return denominator;
    }

    private void findYes ( ) {
        for (int i=0; i< Xes.length; i++) {
            Yes[i] = function.getValueOfFunction(Xes[i]);
        }
    }
    private void findBes ( ) {
        Bes[0] = Yes[0];
        for (int i=1; i<Xes.length; i++) {
            Bes[i] = (Yes[i]-Bes[0] - findTrash(i))/(findDenominator(i));
        }
    }


    public double resultFunction(double x) {
        double result = 0;
        for (int i = 0; i<Xes.length; i++) {
            double resulti = Bes[i];
            for (int j=0; j<i; j++) {
                resulti*=(x-Xes[j]);
            }
            result+=resulti;
        }
        return result;
    }

}
