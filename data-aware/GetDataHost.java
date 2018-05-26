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

import alluxio.wire.FileBlockInfo;
import alluxio.wire.BlockInfo;
import alluxio.wire.BlockLocation;
import alluxio.wire.WorkerNetAddress;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import alluxio.util.ConfigurationUtils;
import java.util.Properties;
import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;

public class GetDataHost{

private static String myinputfile=null;
private static String mymount_point="/home/condor/alluxio-data";

public GetDataHost(){}


public static String gethost(String inputfile){

    myinputfile=inputfile;
    try{

	String confile = "/home/condor/alluxio/conf/alluxio-site.properties";
        Properties mypro = ConfigurationUtils.loadPropertiesFromFile(confile);
        Configuration.merge(mypro);

	FileSystem fs = FileSystem.Factory.get();

	//Replace the inputfile path to Alluxio
	String a_path=myinputfile.replace(mymount_point,"");
	System.out.printf("Replacing POSIX file path '%s' to '%s' \n",myinputfile,a_path);

	AlluxioURI path = new AlluxioURI(a_path);
	if (!fs.exists(path)){
	    System.out.println("File dose not exist");
	    return null;
	}

	URIStatus mystatus = fs.getStatus(path);
	if(mystatus.isFolder()){
	     System.out.println("File is a directory");
	     return null;
	}

	
	List<FileBlockInfo> myfileblockinfos =  mystatus.getFileBlockInfos();
	List<String> myhost=new ArrayList<String>();

	Iterator<FileBlockInfo> blkinfo_it = myfileblockinfos.iterator();
	while(blkinfo_it.hasNext()){
	    BlockInfo myblock = blkinfo_it.next().getBlockInfo();
	    List<BlockLocation> blklocations = myblock.getLocations();
	    Iterator<BlockLocation> blklocation_it = blklocations.iterator();
	    while(blklocation_it.hasNext()){
		BlockLocation tmpblk = blklocation_it.next();
		String blkhost = tmpblk.getWorkerAddress().getHost();
		myhost.add(blkhost);
	    }
	}

	//Return the fisrt host that hold the data
	if(!myhost.isEmpty()){
	    //System.out.printf("The blockinfo of file is %s \n",myhost.get(0));
	    return myhost.get(0);
	}else{
	    return null;
	}
		

    } catch (FileDoesNotExistException e) {
    } catch(Exception e){    
    }
    return null;
} 


}
