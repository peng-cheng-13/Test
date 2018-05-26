#include <stdio.h>
#include "gethost.h"
#include <jni.h>
#include <stdlib.h>
#include <assert.h>

char* gethost_c(char* argv){
    JavaVMOption options[1];
    JNIEnv *env;
    JavaVM *jvm;
    JavaVMInitArgs vm_args;
    long status;
    jclass cls; 
    jmethodID mid;
    jint square;
    jobjectArray applicationArgs;
    jstring appArg;
    jstring msg;
    char* test;
    
    options[0].optionString = "-Djava.class.path=/home/condor/alluxio/core/client/runtime/target/alluxio-core-client-runtime-1.6.1-jar-with-dependencies.jar:.";
    vm_args.version = 0x00010002;
    vm_args.nOptions = 1; 
    vm_args.options = options;

    status = JNI_CreateJavaVM(&jvm, (void**)&env, &vm_args); 
    if(status != JNI_ERR){
	//printf("create JVM successfully\n");
	cls = (*env)->FindClass(env,"GetDataHost");
	if(cls !=0){
	    //printf("find class successfully\n");

	    //get the jmethodID	    
	    mid=(*env)->GetStaticMethodID(env, cls, "gethost", "(Ljava/lang/String;)Ljava/lang/String;");
	    if(mid !=0){
		 //printf("get static int method successfully\n");

		    appArg = (*env)->NewStringUTF(env, argv);
		    msg = (jstring)(*env)->CallObjectMethod(env, cls, mid, appArg);
		    test = (char*)(*env)->GetStringUTFChars(env, msg, NULL);
		    printf("return string >%s<\n",test);
		    
	    }
	}
	(*jvm)->DestroyJavaVM(jvm); 
	return test;
    }else{
	return NULL;
    }

}

