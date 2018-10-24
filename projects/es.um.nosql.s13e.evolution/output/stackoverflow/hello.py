import numpy as np
import datetime as dt
import matplotlib.pyplot as plt
import matplotlib.font_manager as font_manager
import matplotlib.dates
from matplotlib.dates import WEEKLY,MONTHLY, DateFormatter, rrulewrapper

def _create_date(timestamp):
    """Creates the date"""
    time = dt.datetime.fromtimestamp(float(timestamp) // 1000000000)
    print(time)
    time2 = time.strftime('%Y-%m-%d %H:%M:%S')
    print(time2)

    mdate = matplotlib.dates.date2num(time)
    return mdate

def createGanttChart():

  yLabels = []
  customDates = []
  try:
    csvLines = open('stackoverflow_red.csv').readlines()
  except:
    return

  for line in csvLines:
    _, varId, _, firstTimestamp, lastTimestamp = line.split(',')
    yLabels.append(varId)
    customDates.append([_create_date(firstTimestamp), _create_date(lastTimestamp)])

  iLen = len(yLabels)
  pos = np.arange(0.5, iLen * 0.5 + 0.5, 0.5)
  taskDates = {}
  for i,task in enumerate(yLabels):
    taskDates[task] = customDates[i]
  fig = plt.figure(figsize=(20, 8))
  ax = fig.add_subplot(111)
  for i in range(len(yLabels)):
    start_date, end_date = taskDates[yLabels[i]]
    ax.barh((i*0.5)+0.5, end_date - start_date, left=start_date, height=0.3, align='center', edgecolor='lightgreen', color='orange', alpha = 0.8)

  locsy, labelsy = plt.yticks(pos,yLabels)
  plt.setp(labelsy, fontsize = 14)
  ax.set_ylim(ymin = -0.1, ymax = iLen*0.5+0.5)
  ax.grid(color = 'g', linestyle = ':')
  ax.xaxis_date()
  rule = rrulewrapper(WEEKLY, interval=1)
  loc = RRuleLocator(rule)
  formatter = DateFormatter("%d-%b")

  ax.xaxis.set_major_locator(loc)
  ax.xaxis.set_major_formatter(formatter)
  labelsx = ax.get_xticklabels()
  plt.setp(labelsx, rotation=30, fontsize=10)

  font = font_manager.FontProperties(size='small')
  ax.legend(loc=1,prop=font)

  ax.invert_yaxis()
  fig.autofmt_xdate()
  plt.show()

if __name__ == '__main__':
  createGanttChart()