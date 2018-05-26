#include "Alluxio.h"
#include "Util.h"
#include <stdlib.h>
#include <string.h>
#include <fstream>
#include <iostream>
#include <ctime>
#include <iomanip>
#include <chrono>
#include <functional>
#include <thread>
#include "JNIHelper.h"
using namespace tdms;

int main(int argc, char *argv[]){

	char* testfile = "/testfile-c-jni";
	TDMSClientContext acc;
	printf("Context successed \n");
	TDMSFileSystem stackFS(acc);
        jTDMSFileSystem client = &stackFS;
        printf("Init jTDMSFileSystem  successed \n");
  	// Create file output stream
	TDMSCreateFileOptions* options = TDMSCreateFileOptions::getCreateFileOptions();
	//options->setDataAccessPattern("SCATTER");
	if(client->exists(testfile))
                client->deletePath(testfile, false);
	jFileOutStream fileOutStream = client->createFile(testfile, options);
	//Write 4GB file 
	char mydata[131072];
  	std::chrono::duration<double> duration = std::chrono::duration<double>::zero();
  	std::chrono::time_point<std::chrono::system_clock> startTime, stopTime;
  	int i,j,k=0;
	char content[] = "hello, alluxio!!";
	fileOutStream->write(content, strlen(content));
  	for(i=0;i<131072;i++)
		mydata[i]='6';
	startTime = std::chrono::system_clock::now();
	for(j=0;j<131072;j++){
  	    	fileOutStream->write(mydata, strlen(mydata));
	}
  	fileOutStream->close();
	stopTime =  std::chrono::system_clock::now();
  	duration = stopTime - startTime;
  	std::cout << "Write 4GB to TDMS in " << duration.count() << " seconds" << std::endl;
	long bd = 16384/duration.count();
	std::cout << "Write Bandwidth is " <<  bd << "MB/s" << std::endl;
	delete fileOutStream;
	return 0;

}

