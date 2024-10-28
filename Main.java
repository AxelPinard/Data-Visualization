import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {

        //Gotta make sure we don't have magic variables :)
        String FileName = "Data";
        int CountryNameIndex = 0;
        int SeriesNameIndex = 2;
        int DataYearIndexStart = 4;
        int DatayearIndexEnd = 12;

        //2d list that holds each separate line and word of the Data
        //by word I mean grouping related to that field
        //ex. United States is 1 "word" because it's the field Country Name
        List<List<String>> words = new ArrayList<>();
        String contents = Files.readString(Path.of(FileName));
        List<String> lines = List.of(contents.split(System.lineSeparator()));
        for (String line : lines) {
           words.add(List.of(line.split("\t"))); // <--------This is NOT a magic variable!!!
        }

        //words.stream().forEach(w-> w.forEach(System.out::println)); <----- Template to get each individual field
        //Note: each line has 13 fields Name, 3letter, series, series code, 2015,..., 2023
        // We do not care about 3letter, and series code. They are redundant.


        //Here we create 3 parallel lists that represent the fields we care about for our records
        //We use a stream to parse through our word list to extract the specific fields we want
        //Since the fields are always in the same index we can just use their index positions to get what we want
        //IE. all Country Names are in the same index of word[x]
        //For some reason we had to have a try/catch to get the for loop to work to get the list of Datayear info
        List<String> CountryNames = new ArrayList<>();
        List<String> SeriesNames = new ArrayList<>();
        List<List<String>> DataYearLists = new ArrayList<>();
        words.stream().forEach(w->{
            CountryNames.add(w.get(CountryNameIndex));
            SeriesNames.add(w.get(SeriesNameIndex));
            List<String> list = new ArrayList<>();
            try {
                for (int i = DataYearIndexStart; i <= DatayearIndexEnd; i++) {
                    list.add(w.get(i));
                }
            } catch(IndexOutOfBoundsException e) {
                System.out.println("Index out of bounds");
            }
            DataYearLists.add(list);
        });
    }
}
