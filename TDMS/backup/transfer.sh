#!/bin/bash
for i in `seq 17634 17662`;do(scp alluxio-core-client-runtime-1.6.1-jar-with-dependencies.jar cn$i:/home/condor/alluxio/core/client/runtime/target);done
for i in `seq 17634 17662`;do(scp  alluxio-core-server-common-1.6.1.jar cn$i:/home/condor/alluxio/core/server/common/target/);done
for i in `seq 17634 17662`;do(scp  alluxio-core-server-worker-1.6.1.jar cn$i:/home/condor/alluxio/core/server/worker/target/);done

