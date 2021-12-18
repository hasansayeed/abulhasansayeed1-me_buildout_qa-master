#!/bin/bash

####################################################
# DO NOT CHANGE THE GRADLE OPTIONS IN THE BLOCK    #
# BELOW, IT WILL HAVE IMPACT ON THE PERFORMANCE    #
# OF YOUR APPLICATION                              #
####################################################
GRADLE_OPTS="-Dgradle.user.home=~/gradle_cache"    #
####################################################

if systemctl status mongodb.service | grep active > /dev/null; then
    echo "MongoDB is running..."
else
    echo "MongoDB not running; Exiting"
    exit -1
fi

# Ensure a clean slate & populate all collections
mongo quiz-database --eval "db.dropDatabase()"

cd ~/workspace/abulhasansayeed1-ME_BUILDOUT_QA
pip3 install pymongo

echo "uploading questions"
python3 upload_questions.py "initial_data_load.json"

gradlew bootrun &

while ! netstat -tna | grep 'LISTEN\>' | grep -q ':8081\>'; do
  echo "waiting for spring application to start"
  sleep 9 # time in seconds, tune it as needed
done

# If you have any script to load the data make sure that its part of this bash script.

