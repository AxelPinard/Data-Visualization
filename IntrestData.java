import java.util.List;

public record IntrestData(String CountryName, String SeriesName, List<String> DataYear) {

    public IntrestData(String CountryName, String SeriesName, List<String> DataYear) {
        this.CountryName = CountryName;
        this.SeriesName = SeriesName;
        this.DataYear = DataYear;
    }
}
