from charts.GanttChart import GanttChart

CSV_ROUTE = "../output/stackoverflow/stackoverflow.csv"

def main():
  chartCreator = GanttChart(CSV_ROUTE)
  chartCreator.showCharts()

main()
