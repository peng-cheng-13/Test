#include <stdio.h>
#include "gethost.h"
#include <jni.h>
#include <stdlib.h>
#include <assert.h>

int main(){
    JavaVMOption options[3];
    JNIEnv *env=NULL;
    JavaVM *jvm=NULL;
    JavaVMInitArgs vm_args;
    long status=NULL;
    jclass cls=NULL; 
    jmethodID mid=NULL;
    jint square=NULL;
    jobjectArray applicationArgs=NULL;
    jstring appArg=NULL;
    jstring msg=NULL;
    char* test=NULL;
    
    options[0].optionString = "-Djava.class.path=/home/condor/alluxio/core/client/runtime/target/alluxio-core-client-runtime-1.6.1-jar-with-dependencies.jar:.";
    options[1].optionString = "-Xmx64m";
    options[2].optionString = "-Xms32m";
    vm_args.version = 0x00010002;
    vm_args.nOptions = 1; 
    vm_args.options = options;

    status = JNI_CreateJavaVM(&jvm, (void**)&env, &vm_args); 
    if(status != JNI_ERR){
	//printf("create JVM successfully\n");
	//jvm->DestroyJavaVM();
	printf("JVM create succedd! \n");
    }else{	
	printf("Error: JVM create failed! \n");
    }
    return 0;
}

