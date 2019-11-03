package ar.edu.itba.sia.gae.helpers;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.ui.ApplicationFrame;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class Plotter extends ApplicationFrame {
    public Plotter(String title) {
        super(title);
    }

    public void plotFitness(List<Double> fitnesses, String title){
        final XYSeries series = new XYSeries("Data");
        final AtomicInteger count = new AtomicInteger(1);
        fitnesses.forEach(fitness -> series.add(count.getAndIncrement(), fitness));
        final XYSeriesCollection data = new XYSeriesCollection(series);
        final JFreeChart chart = ChartFactory.createXYLineChart(
                title,
                "Generations",
                "Fitness",
                data,
                PlotOrientation.VERTICAL,
                true,
                true,
                false
        );

        final ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new java.awt.Dimension(500, 270));
        setContentPane(chartPanel);
    }
}
