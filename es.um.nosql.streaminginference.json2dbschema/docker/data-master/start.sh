#!/bin/bash
su hadoop -c "echo 'export HADOOP_CONF_DIR=/usr/local/hadoop/etc/hadoop' >> ~/.profile " &&
su hadoop -c "$HADOOP_HOME/bin/hdfs namenode -format -force"
service hadoop start &&
service hadoop stop &&
service hadoop start
