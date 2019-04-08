import matplotlib
import matplotlib.pyplot as plt
import numpy as np
import datetime

class TimeChart:

  __timeData = None
  x = None

  def __init__(self, csvRoute):
    #Do stuff w csvRoute
    self.__timeData = [ 2,4,6,8,10,12,14,16,18,20 ]
    self.x = [datetime.datetime.now() + datetime.timedelta(hours=i) for i in range(len(self.__timeData))]

    try:
      csvContent = open(csvRoute).readlines()[1:]
    except IOError as ioerr:
      print(ioerr)

#    entityDict = {}

#    for line in csvContent:
#      entityName, varId, _, firstTimestamp, lastTimestamp = line.rstrip().split(',')
#      if firstTimestamp != "0" and lastTimestamp != "0":
#        entityDict.setdefault(entityName,[]).append({"entityName": entityName, "varId": varId, "firstTimestamp": firstTimestamp, "lastTimestamp": lastTimestamp})

#    for key in entityDict:
#      self.__chartData[key] = ChartData()
#      self.__chartData[key].process(entityDict[key])


  def showCharts(self):
    for key in self.__timeData:
      self.__showChart(self.__timeData[key])

  def __showChart(self, chartData):

    plt.plot(self.x, self.__timeData)
    plt.gcf().autofmt_xdate()
    plt.show()
