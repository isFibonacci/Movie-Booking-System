package com.MovieTheaterBooking;

import java.io.*;
import java.util.*;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

public class ReadWriteFile {
    private ArrayList<String> requests= new ArrayList<>();
    private Map<String, ArrayList<String>> result = new LinkedHashMap<>();

    MovieTheater movieTheater = new MovieTheater();

    public void ReadFile(String FileName) throws IOException {
        File file = new File(FileName);
        BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
        String request = bufferedReader.readLine();

        while (request != null) {
            requests.add(request);
            request = bufferedReader.readLine();
        }
        bufferedReader.close();
    }

    public void writeToFile() throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter("output.txt"));
        process(writer);

        for (Map.Entry<String, ArrayList<String>> entry : result.entrySet()) {
            String key = entry.getKey();
            ArrayList<String> value = entry.getValue();
            Collections.sort(value);
            String str = key + " " + value;
            writer.write(str + "\n");
        }
        writer.close();
        movieTheater.printResult();
    }

    public void process(BufferedWriter writer) {
        for (String request: requests) {
            int res = movieTheater.booking(request, result);
            if (res == -1) {
                ArrayList<String> list = new ArrayList<>();
                list.add("Invalid number of request seat");
                result.put(request, list);
            } else if (res == 1) {
                ArrayList<String> list = new ArrayList<>();
                list.add("Sorry, not enough available seat right now");
                result.put(request, list);
            }
        }
    }
}
