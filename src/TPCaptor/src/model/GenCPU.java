package model;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class GenCPU implements GenStrategy {

    @Override
    public double generate() {

        File file = new File("/sys/class/thermal/thermal_zone2/temp");
        Scanner scanner = null;
        String line = null;
        try {
            scanner = new Scanner(file);

            while (scanner.hasNextLine()) {
                line = scanner.nextLine();
                while (line.length() > 0 && line.charAt(line.length() - 1) == '0') {
                    line = line.substring(0, line.length() - 1);
                }

            }
            scanner.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        return Double.parseDouble(line);
    }
}
