import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.IOException;
import com.google.common.collect.ImmutableMap;
import alluxio.AlluxioURI;
import alluxio.client.file.FileOutStream;
import alluxio.client.file.FileInStream;
import alluxio.client.file.FileSystem;
import alluxio.exception.FileAlreadyExistsException;
import alluxio.*;

import alluxio.exception.AlluxioException;
import alluxio.Configuration;
import alluxio.util.ConfigurationUtils;
import java.util.Properties;

public class Tri{

public static void main(String[] arg){
    try{
        String confile = "/home/condor/alluxio/conf/alluxio-site.properties";
        Properties mypro = ConfigurationUtils.loadPropertiesFromFile(confile);
        Configuration.merge(mypro);       
	// Write Test
	byte b[] = {0,0,0,0};
	String tmppath = "/home/condor/alluxio-data/tri.txt";
	String mypath = tmppath.replaceAll("/home/condor/alluxio-data","");
        System.out.printf("Alluxio file path = %s \n",mypath);
        FileSystem fs = FileSystem.Factory.get();
        AlluxioURI path = new AlluxioURI(mypath);
        OutputStream out = fs.createFile(path);
	System.out.println("Alluxio has created");
        out.write(b);
        out.close();
        System.out.println("Output finished");
			

	/* Tier info Test
	BlockMetadataManager mMetaManager = BlockMetadataManager.createBlockMetadataManager();
        StorageTier mTier0 = mMetaManager.getTier("MEM");
	long freeTier0 = mTier0.getAvailableBytes ();
	long allTier0 = mTier0.getCapacityBytes();
	double per =((double)freeTier0)/((double)allTier0);
	System.out.println("Tire created done");
	System.out.printf("Available Bytes of MEM Tier is %f \n",per);
	*/	
		
	/* Read Test 
        byte b[] =new byte[1024];
        AlluxioURI path = new AlluxioURI("/fileinalluxio.txt");
        FileInStream in = fs.openFile(path);
        System.out.println("FileInStream has created");
        int read;
        read = in.read(b);

        while(read != -1){
                for(i=0;i<b.length;i++)
                        System.out.print(b[i]);
                read = in.read(b);
        }
        in.close();
		*/

        }catch(Exception e){

        }


        }

}

