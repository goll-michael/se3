package persistence;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;

public class Hilfsklasse
{

	public static void kopiereDatei(File in, File out) throws IOException 
	{
	    FileChannel inChannel = new FileInputStream(in).getChannel();
	    FileChannel outChannel = new FileOutputStream(out).getChannel();
	    try
	    {
	        inChannel.transferTo(0, inChannel.size(),
	                outChannel);
	    } 
	    catch (IOException e)
	    {
	        throw e;
	    }
	    finally
	    {
	        if (inChannel != null) inChannel.close();
	        if (outChannel != null) outChannel.close();
	    }
	}
	
	public static void loescheDatei(File in) throws IOException
	{
		if(in.exists())
			in.delete();
	}
}
