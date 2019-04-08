import sys
from charts.TimeChart import TimeChart

REDDIT_CSV_ROUTE = "../../output/reddit/reddit.csv"
SOF_CSV_ROUTE = "../../output/stackoverflow/stackoverflow.csv"
SOF_CSV_OUTLIERS_ROUTE = "../../output/stackoverflow/stackoverflow_outliers.csv"
SOF_CSV_LIVEVARS_ROUTE = "../../output/stackoverflow/stackoverflow_livevars.csv"

def main():

  chartCreator = TimeChart(SOF_CSV_ROUTE)
  chartCreator.showCharts()

main()
