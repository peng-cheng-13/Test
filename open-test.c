#include<stdio.h>
#include<stdlib.h>
int main( int argc, char *argv[])
{
        char *s="That's a good new";
        char data[15];
	int i;
        FILE *fp;
        fp=fopen(argv[1],"w+b");
	//fp=fopen("/home/condor/alluxio-data/test-open.txt","w+b");
        /*
	if(fp){
		printf("File exists\n");
		fclose(fp);
	}*/
	//printf("File not exists\n");
	//fprintf(fp,"%s",s);
	//char v1[10] = "S";
	//setxattr("/home/condor/alluxio-data/test-open.txt","DAP",v1,10*sizeof(char),0);
	fwrite(s,sizeof(char),10,fp);
	fflush(fp);
        fseek(fp, 0, SEEK_SET);
	fread(data,sizeof(char),10,fp);
        fclose(fp);
	printf("File created\n");
	for(i=0;i<10;i++)
		printf("%c",data[i]);
	printf("\n");
        /*
        fp=fopen("/home/condor/test-open.txt","w+");
	if(fp == (FILE *)NULL ){
		printf("fp = null \n");
	}else
		fclose(fp);
	*/
        return 0;
}
