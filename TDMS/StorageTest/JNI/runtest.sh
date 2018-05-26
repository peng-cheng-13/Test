#!/bin/bash
for i in `seq 0 1000`
do
echo `expr $i \* 10`
java -cp /home/condor/alluxio/core/client/runtime/target/alluxio-core-client-runtime-1.6.1-jar-with-dependencies.jar:/home/condor/alluxio/core/server/worker/target/alluxio-core-server-worker-1.6.1.jar:/home/condor/alluxio/core/server/common/target/alluxio-core-server-common-1.6.1.jar:. StorageTest
sleep 10

done
