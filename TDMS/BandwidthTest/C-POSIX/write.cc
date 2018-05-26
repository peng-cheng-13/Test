#include<stdio.h>
#include<stdlib.h>
#include <chrono>
#include <iostream>
#include <sys/xattr.h>
int main( int argc, char *argv[])
{
	char data[4194304];
	int i,j=0;
	for(i=0;i<4194304;i++)
		data[i]='0';
	FILE *fp;
	//fp=fopen("/home/condor/alluxio-data/testfile-c-posix","w+b");
	//fp=fopen("/ssd/testfile-c-posix","w+b");
	//fp=fopen("/mnt/ramdisk/testfile-c-posix","w+b");
	//Write BD of Lustre
	fp=fopen("/BIGDATA/nsccgz_pcheng_1/testfile-c-posix","w+b");
	std::chrono::duration<double> duration = std::chrono::duration<double>::zero();
	std::chrono::time_point<std::chrono::system_clock> startTime, stopTime;
	startTime = std::chrono::system_clock::now();
	for(i=0;i<4096;i++){
		fwrite(data,sizeof(char),4194304,fp);
	}
        stopTime =  std::chrono::system_clock::now();
	fclose(fp);
	duration = stopTime - startTime;
	std::cout << "Write 16GB to Lustre in " << duration.count() << " seconds" << std::endl;
	long wbd= 16384/duration.count();
	std::cout << "Write Bandwidth of Lustre is  " << wbd << " MB/s" << std::endl;
	/*
	//Write BD of TDMS-memory-tier
	fp=fopen("/home/condor/alluxio-data/testfile-c-posix","w+b");
        std::chrono::duration<double> duration = std::chrono::duration<double>::zero();
        std::chrono::time_point<std::chrono::system_clock> startTime, stopTime;
        startTime = std::chrono::system_clock::now();
        for(i=0;i<4096;i++){
                fwrite(data,sizeof(char),4194304,fp);
        }
        stopTime =  std::chrono::system_clock::now();
        fclose(fp);
        duration = stopTime - startTime;
        std::cout << "Write 4GB to TDMS-memory-tier in " << duration.count() << " seconds" << std::endl;
        long wbd= 4096/duration.count();
        std::cout << "Write Bandwidth of TDMS-memory-tier is  " << wbd << " MB/s" << std::endl;
	*/
	return 0;
}
