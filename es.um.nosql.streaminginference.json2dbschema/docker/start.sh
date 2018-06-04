#!/bin/bash
./clean-hdfs-directories.sh
docker-compose up -d
docker exec -i mongodb bash <<'EOF'
#mongo airport --eval "rs.initiate()"
#/deployments/benchmark/benchmark.sh
EOF
docker exec -i hadoop-namenode bash <<'EOF'
/data/start.sh
EOF
docker exec -it hadoop-namenode bash
