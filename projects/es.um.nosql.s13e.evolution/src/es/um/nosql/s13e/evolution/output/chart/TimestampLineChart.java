package es.um.nosql.s13e.evolution.output.chart;

import java.awt.Color;
import java.awt.Dimension;
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.time.Day;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.xy.XYDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

import es.um.nosql.s13e.NoSQLSchema.StructuralVariation;
// TODO: This may be configured to format by minute/hour/day/month/year...
// TODO: What about a simple UI?
public class TimestampLineChart extends ApplicationFrame
{
  private static final long serialVersionUID = 4771567076282726708L;

  private JFreeChart chart;

  private String schemaName;
  private String entityName;

  private Map<String, Integer> plotMap;

  private final static DateFormat DATE_FORMATTER = new SimpleDateFormat("yyyy-MM-dd");
  private final static int WIDTH = 640;
  private final static int HEIGHT = 480;

  public TimestampLineChart(String schemaName, String entityName, List<StructuralVariation> variations)
  {
    super(schemaName + " - " + entityName + " variations classified by timestamps");
    this.schemaName = schemaName;
    this.entityName = entityName;
    this.plotMap = new HashMap<String, Integer>();

    for (StructuralVariation var : variations)
    {
      if (var.getTimestamp() == 0)
        continue;

      String formattedTs = DATE_FORMATTER.format(new Date(var.getTimestamp()));
      if (plotMap.containsKey(formattedTs))
        plotMap.put(formattedTs, plotMap.get(formattedTs) + 1);
      else
        plotMap.put(formattedTs, 1);
    }

    if (!plotMap.isEmpty())
    {
      LocalDate startingDate = LocalDate.parse(DATE_FORMATTER.format(plotMap.keySet().stream().map(date -> parseDate(date))
        .min((date1, date2) -> { return Long.compare(date1.getTime(), date2.getTime());}).get()));

      LocalDate endingDate = LocalDate.parse(DATE_FORMATTER.format(plotMap.keySet().stream().map(date -> parseDate(date))
        .max((date1, date2) -> { return Long.compare(date1.getTime(), date2.getTime());}).get()));

      this.chart = ChartFactory.createTimeSeriesChart("\"" + entityName+ "\" variations",
          "Timestamps", "Variations", createDataset(startingDate, endingDate), false, true, false);

      XYPlot plot = (XYPlot)chart.getPlot();
      plot.setBackgroundPaint(Color.WHITE);
      plot.getRangeAxis().setStandardTickUnits(NumberAxis.createIntegerTickUnits());
    }
  }

  private Date parseDate(String date)
  {
    try
    {
      return DATE_FORMATTER.parse(date);
    } catch (ParseException e)
    {
      e.printStackTrace();
    }

    return null;
  }

  private XYDataset createDataset(LocalDate startingDate, LocalDate endingDate)
  {
    TimeSeriesCollection dataset = new TimeSeriesCollection();
    TimeSeries theSerie = new TimeSeries("Variations");

    for (LocalDate date = startingDate; date.isBefore(endingDate) || date.isEqual(endingDate); date = date.plusDays(1))
      theSerie.add(new Day(date.getDayOfMonth(), date.getMonthValue(), date.getYear()), plotMap.getOrDefault(date.toString(), 0));        

    dataset.addSeries(theSerie);

    return dataset;
  }

  public void showChart()
  {
    if (chart == null)
    {
      System.err.println(schemaName + " > " + entityName + " only contains variations without timestamps");
      return;
    }

    ChartPanel chartPanel = new ChartPanel(chart);
    chartPanel.setPreferredSize(new Dimension(WIDTH, HEIGHT));
    setContentPane(chartPanel);

    this.pack();
    RefineryUtilities.centerFrameOnScreen(this);
    this.setVisible(true);
  }

  public void saveChart(String route)
  {
    if (chart == null)
    {
      System.err.println(schemaName + " > " + entityName + " only contains variations without timestamps");
      return;
    }

    try
    {
      new File(route).mkdirs();
      ChartUtilities.saveChartAsPNG(new File(route + schemaName + "_" + entityName + ".png"), chart, WIDTH, HEIGHT);      
    } catch (IOException e)
    {
      e.printStackTrace();
    }
  }
}
