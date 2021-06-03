import datetime
import matplotlib.dates

class ChartData:

  __yLabels = None
  __taskDates = None
  __outliers = None

  def __init__(self):
    self.__yLabels = []
    self.__taskDates = {}
    self.__outliers = []

  def getYLabels(self):
    return self.__yLabels

  def getTaskDates(self):
    return self.__taskDates

  def isOutlier(self, index):
    return self.__outliers[index]

  def process(self, variations):

    customDates = []
    for var in variations:
      self.__yLabels.append(var["entityName"] + '_' + var["varId"])
      customDates.append([self.__createDate(var["firstTimestamp"]), self.__createDate(var["lastTimestamp"])])

    for i, task in enumerate(self.__yLabels):
      self.__taskDates[task] = customDates[i]

  def __createDate(self, timestamp):
    time = datetime.datetime.fromtimestamp(float(timestamp))
#    time = datetime.datetime.fromtimestamp(float(timestamp) // 1000)

    return matplotlib.dates.date2num(time)
