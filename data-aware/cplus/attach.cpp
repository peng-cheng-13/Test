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
    
    JavaVMAttachArgs attach_args;
    attach_args.version = 0x00010002;
    status = AttachCurrentThread((void**)&env,&attach_args);
    //status = JNI_CreateJavaVM(&jvm, (void**)&env, &vm_args); 
    if(status != JNI_ERR){
	printf("get JVM successfully\n");
	cls = env->FindClass("GetDataHost");
	if(cls !=0){
	    printf("find class successfully\n");

	    //get the jmethodID	    
	    mid=env->GetStaticMethodID(cls, "gethost", "(Ljava/lang/String;)Ljava/lang/String;");
	    if(mid !=0){
		 //printf("get static int method successfully\n");

		    appArg = env->NewStringUTF("/home/condor/alluxio-data/1-merged.bam");
		    msg = (jstring)env->CallObjectMethod(cls, mid, appArg);
		    test = (char*)env->GetStringUTFChars(msg, NULL);
		    printf("return string >%s<\n",test);
		    
	    }
	}
	DetachCurrentThread(); 
	return 0;
    }else{
	//jvm->DestroyJavaVM();
	printf("Error: JVM get failed! \n");
	return 0;
    }

}

