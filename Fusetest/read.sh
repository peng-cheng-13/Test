#! /bin/bash 

function getTiming() {  
    start=$1  
    end=$2  
     
    start_s=$(echo $start | cut -d '.' -f 1)  
    start_ns=$(echo $start | cut -d '.' -f 2)  
    end_s=$(echo $end | cut -d '.' -f 1)  
    end_ns=$(echo $end | cut -d '.' -f 2)  
  
  
# for debug..  
#    echo $start  
#    echo $end  
  
  
    time=$(( ( 10#$end_s - 10#$start_s ) * 1000 + ( 10#$end_ns / 1000000 - 10#$start_ns / 1000000 ) ))  
  
  
    echo "$time ms"  
}

echo "This is only a test to get a ms level time duration..."

for((i=0;i<4;i++))
do    
	start=$(date +%s.%N)
	#cp -r /home/condor/alluxio-data/gather .
	cp -r /BIGDATA/nsccgz_pcheng_1/gather .
	end=$(date +%s.%N) 
	rm -rf gather
	echo 3 > /proc/sys/vm/drop_caches
	getTiming $start $end  
done
