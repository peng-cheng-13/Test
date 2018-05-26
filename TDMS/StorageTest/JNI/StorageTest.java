import java.io.File;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import com.google.common.collect.ImmutableMap;
import alluxio.AlluxioURI;


import alluxio.client.file.FileSystem;
import alluxio.client.file.URIStatus;
import alluxio.exception.FileAlreadyExistsException;
import alluxio.exception.FileDoesNotExistException;
import alluxio.*;
import alluxio.exception.AlluxioException;
import alluxio.Configuration;
import alluxio.client.file.BaseFileSystem;
import alluxio.client.file.options.CreateFileOptions;
import alluxio.client.file.policy.FileWriteLocationPolicy;
import alluxio.client.file.policy.LocalFirstPolicy;
import alluxio.client.file.policy.RoundRobinPolicy;
import alluxio.client.file.policy.MostAvailableFirstPolicy;
import alluxio.client.file.policy.SpecificHostPolicy;
import alluxio.wire.FileBlockInfo;
import alluxio.wire.BlockInfo;
import alluxio.wire.BlockLocation;
import alluxio.wire.WorkerNetAddress;
import alluxio.worker.block.BlockMetadataManager;
import alluxio.worker.block.meta.StorageTier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import alluxio.util.ConfigurationUtils;
import java.util.Properties;
import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;
import java.util.concurrent.TimeUnit;

public class StorageTest{

private static String myinputfile=null;
private static String mymount_point="/home/condor/alluxio-data";
private static int sNum = 0;
private static int sI = 0;
private static StorageTier sTier0;
private static StorageTier sTier1;


public static void main(String[] args){
	String confile = "/home/condor/alluxio/conf/alluxio-site.properties";
        Properties mypro = ConfigurationUtils.loadPropertiesFromFile(confile);
        Configuration.merge(mypro);
	System.out.printf("Capacity of Storage Tier\n");
 	BlockMetadataManager mMetaManager;
	long freeTier0,avaTier0,freeTier1,avaTier1;
	double f0,f1;
	//while(true){
		mMetaManager = BlockMetadataManager.createBlockMetadataManager();
		sTier0 = mMetaManager.getTier("MEM");
        	sTier1 = mMetaManager.getTier("HDD");
		freeTier0 = sTier0.getAvailableBytes();
   		avaTier0 = sTier0.getCapacityBytes();
  		freeTier1 = sTier1.getAvailableBytes();
        	avaTier1 = sTier1.getCapacityBytes();
        	f0 = ((double) freeTier0) / ((double) avaTier0);
        	f1 = ((double) freeTier1) / ((double) avaTier1);
   		System.out.printf("Used space of Tier 0 is %f \n",1-f0);
		System.out.printf("Used space of Tier 1 is %f \n",1-f1);
		System.out.printf("\n");
	//	try{
        //               TimeUnit.SECONDS.sleep(5);
        //      }catch(Exception e){

        //        }
	//}
}

}













