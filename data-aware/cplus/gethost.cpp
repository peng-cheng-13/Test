#include <stdio.h>
#include "gethost.h"
#include <jni.h>
#include <stdlib.h>
#include <assert.h>

char* gethost_c(char* argv){
    JavaVMOption options[3];
    JNIEnv *env;
    static JavaVM *jvm=NULL;
    static JavaVM *g_jvm;
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
    options[1].optionString = "-Xmx64m";
    options[2].optionString = "-Xms32m";
    vm_args.version = 0x00010002;
    vm_args.nOptions = 1; 
    vm_args.options = options;
    /*if(g_jvm == NULL){
	printf("g_jvm is NULL \n");
	status = JNI_CreateJavaVM(&jvm, (void**)&env, &vm_args);
	//jstring cached = env->NewStringUTF("AAA");
	printf("status = %ld \n",status);
	if(status != JNI_ERR)
		g_jvm = (JavaVM *)(env->NewGlobalRef(jvm));
    }else{ 
	printf("Using g_jvm \n");
	//JNIEnv *tenv;
	//long tstatus = JNI_CreateJavaVM(&jvm, (void**)&tenv, &vm_args);
	//printf("status = %ld \n",status);
	//printf("tstatus = %ld \n",tstatus);
	//if(tstatus != JNI_ERR)
	//	printf("create JVM successfully\n");
	//if(jvm !=NULL)
	//    status = jvm->AttachCurrentThread((void**)&env,NULL); 
        //else
	//    printf("jvm is NULL \n");
    }*/
    if(jvm == NULL){
        status = JNI_CreateJavaVM(&jvm, (void**)&env, &vm_args); 
    }else{
	printf("jvm is not NULL \n");
	status = jvm->AttachCurrentThread((void**)&env, NULL);
    }
    if(status != JNI_ERR){
	//printf("create JVM successfully\n");
	cls = env->FindClass("GetDataHost");
	if(cls !=0){
	    //printf("find class successfully\n");

	    //get the jmethodID	    
	    mid=env->GetStaticMethodID(cls, "gethost", "(Ljava/lang/String;)Ljava/lang/String;");
	    if(mid !=0){
		 //printf("get static int method successfully\n");

		    appArg = env->NewStringUTF(argv);
		    msg = (jstring)env->CallObjectMethod(cls, mid, appArg);
		    test = (char*)env->GetStringUTFChars(msg, NULL);
		    printf("return string >%s<\n",test);
		    
	    }
	}
	jvm->DestroyJavaVM(); 
	return test;
    }else{
	//jvm->DestroyJavaVM();
	printf("Error: JVM create failed! \n");
	return NULL;
    }

}

