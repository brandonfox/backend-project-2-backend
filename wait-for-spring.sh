#!/bin/bash

time=0;
timeout=20

while ! curl localhost:8080 | grep -q 'connection refused'  && [ $time -lt $timeout ] ; do
  ((time++))
  sleep 1
done ;

if [ $time -ge $timeout ] ; then
  echo 'Timed out while waiting for spring to start'
  exit 1
else exit 0
fi;