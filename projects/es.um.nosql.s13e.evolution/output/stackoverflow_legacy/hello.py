import numpy as np
import datetime as dt
import matplotlib.pyplot as pyplot
import matplotlib.font_manager as font_manager
import matplotlib.dates
from matplotlib.dates import WEEKLY,MONTHLY, DateFormatter, rrulewrapper, RRuleLocator

def _create_date(timestamp):
    """Creates the date"""
    time = dt.datetime.fromtimestamp(float(timestamp) // 1000)
    return matplotlib.dates.date2num(time)

def createGanttChart():

  try:
    csvLines = open('stackoverflow_red.csv').readlines()[1:]
  except:
    return

  yLabels = []
  customDates = []

  for line in csvLines:
    entityName, varId, _, firstTimestamp, lastTimestamp = line.split(',')
    yLabels.append(entityName + '_' + varId)
    customDates.append([_create_date(firstTimestamp), _create_date(lastTimestamp)])

  taskDates = {}
  for i,task in enumerate(yLabels):
    taskDates[task] = customDates[i]

  figure1 = pyplot.figure(figsize=(20, 8))
  ax = figure1.add_subplot(111)
  for i in range(len(yLabels)):
    startDate, endDate = taskDates[yLabels[i]]
    ax.barh((i*0.5)+0.5, endDate - startDate, left=startDate, height=0.3, align='center', edgecolor='lightblue', color='blue', alpha = 1)

  iLen = len(yLabels)
  pos = np.arange(0.5, iLen * 0.5 + 0.5, 0.5)
  locsy, labelsy = pyplot.yticks(pos,yLabels)
  pyplot.setp(labelsy, fontsize = 8)
  ax.set_ylim(ymin = -0.1, ymax = iLen*0.5+0.5)
  ax.grid(color = 'lightblue', linestyle = ':')
  ax.xaxis_date()

  ax.xaxis.set_major_locator(RRuleLocator(rrulewrapper(MONTHLY, interval=4)))
  ax.xaxis.set_major_formatter(DateFormatter("%d-%b-%Y"))
  pyplot.setp(ax.get_xticklabels(), rotation=30, fontsize=8)

  font = font_manager.FontProperties(size='small')
  ax.legend(loc=1,prop=font)

  ax.invert_yaxis()
  figure1.autofmt_xdate()
  pyplot.show()

if __name__ == '__main__':
  createGanttChart()