import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class GuiMain extends JPanel {
    TablePanel tablePanel;
    //ChartPanel chartPanel;
    //StatsPanel statsPanel;
    //DetailsPanel detailsPanel;

    public GuiMain(List<IntrestData> allofem) {
        setPreferredSize(new Dimension(1800, 1200));
        tablePanel = new TablePanel(new IntrestTableModel(allofem));
        add(tablePanel);
    }

    public static void main(String[] args) throws IOException {
        //Gotta make sure we don't have magic variables :)
        String FileName = "Data";
        int CountryNameIndex = 0;
        int FirstIndex = 0;
        int SeriesNameIndex = 2;
        int DataYearIndexStart = 4;
        int DatayearIndexEnd = 12;

        //2d list that holds each separate line and word of the Data
        //by word I mean grouping related to that field
        //ex. United States is 1 "word" because it's the field Country Name
        java.util.List<java.util.List<String>> words = new ArrayList<>();
        String contents = Files.readString(Path.of(FileName));
        java.util.List<String> lines = java.util.List.of(contents.split(System.lineSeparator()));
        for (String line : lines) {
            words.add(java.util.List.of(line.split("\t"))); // <--------This is NOT a magic variable!!!
        }

        //words.stream().forEach(w-> w.forEach(System.out::println)); <----- Template to get each individual field
        //Note: each line has 13 fields Name, 3letter, series, series code, 2015,..., 2023
        // We do not care about 3letter, and series code. They are redundant.


        //Here we create 3 parallel lists that represent the fields we care about for our records
        //We use a stream to parse through our word list to extract the specific fields we want
        //Since the fields are always in the same index we can just use their index positions to get what we want
        //IE. all Country Names are in the same index of word[x]
        //Had an issue with parsing armina (for some reason when I tab in the data file it dosnt pick up as a \t
        //so we have an exception to make sure we get the last datapoint in.
        java.util.List<String> CountryNames = new ArrayList<>();
        java.util.List<String> SeriesNames = new ArrayList<>();
        java.util.List<java.util.List<String>> DataYearLists = new ArrayList<>();
        words.stream().forEach(w->{
            CountryNames.add(w.get(CountryNameIndex));
            SeriesNames.add(w.get(SeriesNameIndex));
            java.util.List<String> list = new ArrayList<>();
            try {
                for (int i = DataYearIndexStart; i <= DatayearIndexEnd; i++) {
                    list.add(w.get(i));
                }
            } catch(IndexOutOfBoundsException e) {
                list.add(w.get(DataYearIndexStart -1));
            }
            DataYearLists.add(list);
        });


        //Setting up records
        List<IntrestData> IntrestDatalist = new ArrayList<>();
        for(int i = FirstIndex; i < CountryNames.size(); i++) {
            IntrestDatalist.add(new IntrestData(CountryNames.get(i), SeriesNames.get(i), DataYearLists.get(i)));
        }

        for (IntrestData jaja:IntrestDatalist) {
            System.out.println(jaja.toString());
        }

        JFrame frame = new JFrame();
        frame.setTitle("Visualization");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(new GuiMain(IntrestDatalist));
        frame.pack();
        frame.setVisible(true);

    }
}
