#include<stdio.h>
#include<stdlib.h>
#include <chrono>
#include <iostream>
#include<string.h>
int main( int argc, char *argv[])
{
        char data[4194304];
        int i,j=0;
	int rc;
        for(i=0;i<4194304;i++)
                data[i]=0;
        FILE *fp;
	//fp=fopen("/ssd/testfile-c-posix","r");
        //fp=fopen("/home/condor/alluxio-data/testfile-c-posix","r");
	//fp=fopen("/mnt/ramdisk/testfile-c-posix","r");
        fp=fopen("/BIGDATA/nsccgz_pcheng_1/testfile-c-posix","r");
	std::chrono::duration<double> duration = std::chrono::duration<double>::zero();
        std::chrono::time_point<std::chrono::system_clock> startTime, stopTime;
        startTime = std::chrono::system_clock::now();
        while(1){
	        //memset(data, 0, sizeof(data));
		rc = fread(data,sizeof(char),4194304,fp);
		if (rc <= 0) 
			break;
        }
	stopTime =  std::chrono::system_clock::now();
        fclose(fp);
        duration = stopTime - startTime;
        std::cout << "Read 16GB from TDMS in " << duration.count() << " seconds" << std::endl;
        long wbd= 16384/duration.count();
        std::cout << "Read Bandwidth is  " << wbd << " MB/s" << std::endl;
        return 0;
}

