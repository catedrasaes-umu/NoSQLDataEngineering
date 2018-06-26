#!/bin/bash
#su hadoop -c "echo 'export HADOOP_HOME=/usr/local/hadoop' >> ~/.profile " &&
su hadoop -c "$HADOOP_HOME/bin/hdfs namenode -format -force" &&
service hadoop start &&
service hadoop stop &&
service hadoop start
