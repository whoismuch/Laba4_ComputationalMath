import java.util.Scanner;

public class Reader {

    private Scanner scanner;
    private Double[][] options;
    private Double[] chosenXes;


    public Double[] getChosenXes ( ) {
        return chosenXes;
    }

    public void setChosenXes (Double[] chosenXes) {
        this.chosenXes = chosenXes;
    }


    public Reader (Scanner scanner, Double[][] options) {
        this.scanner = scanner;
        this.options = options;
    }

    public Double[][] getOptions ( ) {
        return options;
    }

    public Reader (Scanner scanner) {
        this.scanner = scanner;
    }

    public Scanner getScanner ( ) {
        return scanner;
    }

    public void read() {
        ReaderFromConsole readerFromConsole = new ReaderFromConsole(scanner, this);
        readerFromConsole.readFromConsole( );
    }


    public double readX ( ) {
        ReaderFromConsole readerFromConsole = new ReaderFromConsole(scanner, this);
        return readerFromConsole.readXFromConsole( );
    }
}
