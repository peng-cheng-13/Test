#!/bin/bash
for i in `seq 0 1000`
do
echo `expr $i \* 10`
MEM_TIER=`du -s /mnt/ramdisk/alluxioworker`

SSD_TIER=`du -s /ssd/alluxio-data/alluxioworker`
#echo $MEM_TIER
#echo $SSD_TIER
./storage "$MEM_TIER" "$SSD_TIER"
sleep 10

done
