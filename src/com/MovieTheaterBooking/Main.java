package com.MovieTheaterBooking;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import com.MovieTheaterBooking.ReadWriteFile;
import com.MovieTheaterBooking.TestDriver;
public class Main {

    public static void main(String[] args) throws IOException {
        // Edge case
        if (args.length == 0) {
            System.out.println("Proper Usage is: java program filename");
            System.exit(0);
        }

        // Read and process the input.txt
        ReadWriteFile FileDriver = new ReadWriteFile();
        FileDriver.ReadFile(args[0]);

        //Write to file output.txt
        FileDriver.writeToFile();

        // Test
//        TestDriver test = new TestDriver();
//        Path p1 = Paths.get("./correct.txt");
//        Path p2 = Paths.get("./output.txt");
//        if (test.sameContent(p1, p2))
//            System.out.println("All the test pass");
//        else
//            System.out.println("Not correct, please check again!");
    }
}
