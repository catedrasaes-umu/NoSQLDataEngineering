package genFigure;

import java.io.*;
import java.util.List;

import net.sourceforge.plantuml.*;

public class GeneratePNG {
  public static void main (String[] args) throws IOException {
	String inFile=new File (args[0]).getAbsolutePath();
	SourceFileReader reader = new SourceFileReader(new File(inFile));
	List<GeneratedImage> list = reader.getGeneratedImages();
	//if (list == null) System.out.println("lista 
	// Generated files
	File png = list.get(0).getPngFile();
	}
}
