import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import com.google.common.collect.ImmutableMap;
import alluxio.AlluxioURI;
import alluxio.client.file.FileOutStream;
import alluxio.client.file.FileInStream;
import alluxio.client.file.FileSystem;
import alluxio.exception.FileAlreadyExistsException;
import alluxio.*;
//import alluxio.WorkerStorageTierAssoc;
import alluxio.exception.AlluxioException;
import alluxio.Configuration;
import alluxio.PropertyKey;
import alluxio.client.file.BaseFileSystem;
//import alluxio.worker.block.meta.StorageTier;
import alluxio.client.file.policy.SpecificHostPolicy;
import alluxio.client.file.options.CreateFileOptions;
//import alluxio.worker.block.BlockMetadataManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import alluxio.util.ConfigurationUtils;
import java.util.Properties;

public class Test{

public static void main(String[] arg){
    try{
        String confile = "/home/condor/alluxio/conf/alluxio-site.properties";
        Properties mypro = ConfigurationUtils.loadPropertiesFromFile(confile);
        Configuration.merge(mypro);
	//int m = 1;
	//String mtier = String.valueOf(m);
	//Configuration.merge(ImmutableMap.of(PropertyKey.USER_FILE_WRITE_TIER_DEFAULT, mtier));
	//SpecificHostPolicy policy = new SpecificHostPolicy("cn17635");
	CreateFileOptions options = CreateFileOptions.defaults();
	//options.setLocationPolicy(policy); 
        int i= 0;
	int j =0;
        System.out.println("This is my first program.");
        
	FileSystem fs = FileSystem.Factory.get();
	AlluxioURI path = new AlluxioURI("/myFile");
  	if(fs.exists(path))
	    System.out.println("Alluxio Path exists");
	else
	    System.out.println("Failed, Alluxio Path does not exist");
	/* Write Test
 	byte[] b = new byte [1024];
	for(i=0; i<1024;i++){
                b[i]=0;
        }
        byte c[] = {1,1};
	AlluxioURI path = new AlluxioURI("/myFile");
        FileOutStream out = fs.createFile(path,options);
	System.out.println("Alluxio has created");
	long startTime = System.currentTimeMillis();
	for(i=0; i<4096;i++){
        	for(j=0; j<1024;j++){
			out.write(b);
		}
	}
	long endTime = System.currentTimeMillis();
	System.out.println("程序运行时间： " + (endTime - startTime) + "ms");
	out.write(c);    
        out.close();
        System.out.println("Output finished");
	*/		

	/* Tier info Test
	BlockMetadataManager mMetaManager = BlockMetadataManager.createBlockMetadataManager();
        StorageTier mTier0 = mMetaManager.getTier("MEM");
	long freeTier0 = mTier0.getAvailableBytes ();
	long allTier0 = mTier0.getCapacityBytes();
	double per =((double)freeTier0)/((double)allTier0);
	System.out.println("Tire created done");
	System.out.printf("Available Bytes of MEM Tier is %f \n",per);
	/*/	
		
	/* Read Test 
        byte b[] =new byte[1024];
        AlluxioURI path = new AlluxioURI("/myFile");
        FileInStream in = fs.openFile(path);
        System.out.println("FileInStream has created");
        int read;
        read = in.read(b);
	
	long startTime = System.currentTimeMillis();
        while(read != -1){
                //for(i=0;i<b.length;i++)
                //        System.out.print(b[i]);
                read = in.read(b);
        }
	long endTime = System.currentTimeMillis();
        System.out.println("程序运行时间： " + (endTime - startTime) + "ms");
        in.close();
	//	*/

        }catch(Exception e){

        }


        }

}

