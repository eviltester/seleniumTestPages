package com.seleniumsimplified.seleniumtestpages;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

/**
 * Created by Alan on 07/07/2016.
 */
public class ResourceReader {


    public String asString(String resourceName){

        StringBuilder output = new StringBuilder();

        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource(resourceName).getFile());

        try (Scanner scanner = new Scanner(file)) {

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                output.append(line);
                output.append(System.lineSeparator());
            }

            scanner.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

        return output.toString();

    }
}
