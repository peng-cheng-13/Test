#include <stdio.h>
#include <stdlib.h>
//extern "C" {
#include "gethost.h"

//}

int main(int argc, char* argv[]){
    char* path = argv[1];
    char* myhost = gethost_c(path);
    //printf("file path = %s \n",path);
    char* myhost = gethost_c(path);
    if(myhost != NULL)
        printf("My host is %s \n", myhost);
    return 0;
}

