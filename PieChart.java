import java.awt.*;
import java.util.Arrays;

public class PieChart {
    double[] value;
    Color[] colors = {Color.BLUE, Color.GREEN, Color.RED, Color.MAGENTA};

    public PieChart(double[] value) {
        this.value = value;
    }
    public void paint(Graphics g) {
        int pies = value.length;
        double total;
        total = Arrays.stream(value).reduce(0.0,Double::sum);
        int startAngle = 0;

        for (int i = 0; i < pies; i++) {
            g.setColor(colors[i % colors.length]);
            g.fillArc(0,0,400, 400 ,startAngle,(int)Math.round(value[i]/total)*360);
            startAngle += (int)Math.round(value[i]/total)*360;
        }
    }
}
