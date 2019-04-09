import numpy as np

import matplotlib.pyplot as pyplot
import matplotlib.font_manager as font_manager
from matplotlib.dates import WEEKLY,MONTHLY, DateFormatter, rrulewrapper, RRuleLocator
from .ChartData import ChartData

class GanttChart:

  __chartData = None

  def __init__(self, csvRoute):

    self.__chartData = {}

    try:
      csvContent = open(csvRoute).readlines()[1:]
    except IOError as ioerr:
      print(ioerr)

    entityDict = {}

    for line in csvContent:
      entityName, varId, count, firstTimestamp, lastTimestamp = line.rstrip().split(',')
      if firstTimestamp != "0" and lastTimestamp != "0":
        entityDict.setdefault(entityName,[]).append({"entityName": entityName, "varId": varId, "count": count, "firstTimestamp": firstTimestamp, "lastTimestamp": lastTimestamp})

    for key in entityDict:
      self.__chartData[key] = ChartData()
      self.__chartData[key].process(entityDict[key])

  def showCharts(self):
    for key in self.__chartData:
      self.__showChart(self.__chartData[key])

  def __showChart(self, chartData):

    figure1 = pyplot.figure()
    ax = figure1.add_subplot(111)
    for i in range(len(chartData.getYLabels())):
      startDate, endDate = chartData.getTaskDates()[chartData.getYLabels()[i]]
      ax.barh((i*0.5)+0.5, endDate - startDate, left=startDate, height=0.3, align='center', edgecolor='lightblue', color='blue', alpha = 1)
  
    iLen = len(chartData.getYLabels())
    pos = np.arange(0.5, iLen * 0.5 + 0.5, 0.5)
    locsy, labelsy = pyplot.yticks(pos, chartData.getYLabels())
    pyplot.setp(labelsy, fontsize = 8)
    ax.set_ylim(bottom = -0.1, top = iLen*0.5+0.5)
    ax.grid(color = 'lightblue', linestyle = ':')
    ax.xaxis_date()
  
    ax.xaxis.set_major_locator(RRuleLocator(rrulewrapper(MONTHLY, interval=4)))
    ax.xaxis.set_major_formatter(DateFormatter("%d-%b-%Y"))
    pyplot.setp(ax.get_xticklabels(), rotation=30, fontsize=8)
  
    font = font_manager.FontProperties(size='small')
    #ax.legend(loc=1,prop=font)
  
    ax.invert_yaxis()
    figure1.autofmt_xdate()

#  Works only on Windows.
#    pyplot.get_current_fig_manager().window.state('zoomed')
    pyplot.show()
