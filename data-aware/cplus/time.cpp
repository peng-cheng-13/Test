#include <stdio.h>
#include <stdlib.h>
#include <string>
#include <unistd.h>
#include<sys/time.h>
using std::string;
int main(int argc, char* argv[]){
    struct  timeval tv;
    struct  timeval te;
    gettimeofday(&tv,NULL);
    printf("tv_sec:%d \n",tv.tv_sec);
    printf("tv_usec:%d \n",tv.tv_usec);
    
    gettimeofday(&te,NULL);
    printf("tv_sec:%d \n",te.tv_sec);
    printf("tv_usec:%d \n",te.tv_usec);
    return 0;
    }
