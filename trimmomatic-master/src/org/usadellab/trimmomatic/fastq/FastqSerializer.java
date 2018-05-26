package org.usadellab.trimmomatic.fastq;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.zip.GZIPOutputStream;

//Alluxio
import alluxio.AlluxioURI;
import alluxio.client.file.FileOutStream;
import alluxio.client.file.FileInStream;
import alluxio.client.file.FileSystem;
import alluxio.util.ConfigurationUtils;
import java.util.Properties;
import alluxio.Configuration;
import alluxio.client.file.URIStatus;
import alluxio.exception.FileAlreadyExistsException;

import org.itadaki.bzip2.BZip2OutputStream;

public class FastqSerializer {

	private BufferedWriter stream;
	private File inputFile;
	private FileOutStream aStream;

	public FastqSerializer()
	{
	}

	public void open(File file) throws IOException
	{
		String name = file.getName();
		this.inputFile = file;
		//Alluxio	
		boolean rewrite = false;	
		byte[] destr = null;
		try{
			String confile = "/home/condor/alluxio/conf/alluxio-site.properties";
		        Properties mypro = ConfigurationUtils.loadPropertiesFromFile(confile);
			Configuration.merge(mypro);
			FileSystem fs = FileSystem.Factory.get();
			String tmppath = file.getAbsolutePath();
			System.out.printf("Originall file path = %s \n",tmppath);
			String mypath = tmppath.replaceAll("/home/condor/alluxio-data","");
        		System.out.printf("Alluxio file path = %s \n",mypath);
			AlluxioURI path = new AlluxioURI(mypath);
        		if(fs.exists(path)){
				int rd = 0;
        			int nread = 0;
				URIStatus status = fs.getStatus(path);
				int myfilesize = (int) status.getLength();
				destr = new byte[myfilesize];
				FileInStream myin = fs.openFile(path);
				while (rd >= 0 && nread < myfilesize) {
         				rd = myin.read(destr, nread, myfilesize - nread);
          				if (rd >= 0) {
            					nread += rd;
          				}
        			}
				myin.close();
				fs.delete(path);
				aStream = fs.createFile(path);
				rewrite = true;
				//aStream.write(destr);
				//gStream.flush();
			}else{
				aStream = fs.createFile(path);
			}
	            }catch(Exception e){
		    }
		    if(rewrite)
			aStream.write(destr);

		 OutputStream gStream = aStream;
		//Original
		//OutputStream gStream = new FileOutputStream(file);

		if (name.endsWith(".gz"))
			{
			gStream = new GZIPOutputStream(gStream);
			}
		else if (name.endsWith(".bz2"))
			{
			gStream = new BZip2OutputStream(gStream);
			}

		// stream=new OutputStreamWriter(new BufferedOutputStream(gStream));

		stream = new BufferedWriter(new OutputStreamWriter(gStream), 32768);
		/*if(rewrite){
			String tmpdata = new String(destr);
			stream.write(tmpdata);	
		}*/
			
		//}catch(Exception e){
                //}
	}

	public void close() throws IOException
	{
		aStream.close();
		stream.close();
	}

	public void writeRecord(FastqRecord record) throws IOException
	{
		StringBuilder sb=new StringBuilder(500);
	       
	    sb.append('@');
	    sb.append(record.getName());
	    sb.append('\n');
	    sb.append(record.getSequence());
	    sb.append("\n+");
	    sb.append(record.getComment());
	    sb.append('\n');
	    sb.append(record.getQuality());
	    sb.append('\n');

	    //Alluxio
	    String name = inputFile.getName();
	    if(name.endsWith(".gz") || name.endsWith(".bz2")){
		stream.write(sb.toString());
	    }else{
		String byteString = sb.toString();
		byte[] byteArray = byteString.getBytes();
		aStream.write(byteArray);
	    }

	    //Original
	    //stream.write(sb.toString());
	    
	}

	public File getInputFile()
	{
		return inputFile;
	}

	public void setInputFile(File file)
	{
		this.inputFile = file;
	}

}
