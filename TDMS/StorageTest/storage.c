#include "stdlib.h"
#include "stdio.h"
#include "math.h"
void main(int argc, char *argv[]){
	if(argc < 2)
		printf("Input Error!  Usage: ./storage a b \n");
	
	//printf("MEM bytes is %f\n",atof(argv[1]));
	//printf("SSD bytes is %f\n",atof(argv[2]));
	double u1 = atof(argv[1])/31457280.0;
	double u2 = atof(argv[2])/52428800.0;
	printf("Used space of Tier 0 is %f \n",u1);
	printf("Used space of Tier 1 is %f \n",u2);
}
