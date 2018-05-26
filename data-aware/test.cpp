#include <stdio.h>
#include <stdlib.h>
extern "C" {
#include "gethost.h"
}

int main(int argc, char* argv[]){
    char* myhost = gethost_c("/home/condor/alluxio-data/testfile");
    if(myhost != NULL)
        printf("My host is %s \n", myhost);
    return 0;
}
