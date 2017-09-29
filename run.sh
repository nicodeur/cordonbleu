#!/bin/sh

cd ${0%/*}

pkill -f cordonbleu-main || true

echo "Starting up..."
nohup java -jar cordonbleu-main-0.0.1-SNAPSHOT.jar server conf/configuration.json >> log/cordonbleu.log &
echo "Started!"
