from charts.GanttChart import GanttChart

CSV_ROUTE = "../output/reddit/reddit.csv"

def main():
  chartCreator = GanttChart(CSV_ROUTE)
  chartCreator.showCharts()

main()
