#include <stdio.h>
#include <stdlib.h>
#include "gethost.h"
#include <string>
#include <unistd.h>

using std::string;
int main(int argc, char* argv[]){
    struct  timeval tv;
    char* path = argv[1];
    char* myhost =NULL;
    string spath=path; 
  


    //if(spath.find("/home/condor/alluxio-data")<spath.length()){
    //    if(access(path,0)==0)
            myhost = gethost_c(path);
    //}
    if(myhost != NULL)
        printf("My host is %s \n", myhost);
    return 0;
}
