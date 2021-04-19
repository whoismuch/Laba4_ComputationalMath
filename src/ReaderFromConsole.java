import java.util.Scanner;

public class ReaderFromConsole extends Reader {
    private Reader reader;

    public ReaderFromConsole (Scanner scanner, Reader reader) {
        super(scanner);
        this.reader = reader;
    }

    public void readFromConsole() {
        int numberOfOption = readNumber(reader.getOptions(),
                "Выберите один из предложенных наборов данных:",
                "Ну вы чего, набора данных под таким номером нет",
                "Номер должен быть целым числом"
        );
        reader.setChosenXes(reader.getOptions()[numberOfOption-1]);
    }


    public int readNumber(Double[][] array, String startMessage, String boundaryError, String formatError) {
        int decision = 0;

        System.out.println(startMessage);
        for (Integer i=1; i<=array.length; i++) {
            System.out.print(i + ") ");
            for (int j=0; j<array[i-1].length; j++) {
                System.out.print(array[i-1][j] + " ");
            }
            System.out.println();
        }
        while (true) {
            try {
                String strDecision = getScanner( ).nextLine( );
                decision = Integer.parseInt(strDecision);
                if (decision < 1 || decision > array.length) {
                    System.out.println(boundaryError);
                    continue;
                }
                break;
            } catch (NumberFormatException e) {
                System.out.println(formatError);
                continue;
            }
        }

        return decision;
    }

    public double readDouble() {
        double x = 0;
        System.out.println("Введите X, а я найду для вас Y:");
        double max = HelperCalculator.getMax(reader.getChosenXes());
        double min = HelperCalculator.getMin(reader.getChosenXes());

        while (true) {
            try {
                String strDecision = getScanner( ).nextLine( );
                x = Double.parseDouble(strDecision);
                if (x < min || x > max) {
                    System.out.println("Предупреждаю, вы вышли за границы узлов интерполяции, за достоверность найденного Y я не отвечаю");
                }
                break;
            } catch (NumberFormatException e) {
                System.out.println("X - это число");
                continue;
            }
        }

        return x;
    }

    public double readXFromConsole ( ) {
        return readDouble();
    }
}
