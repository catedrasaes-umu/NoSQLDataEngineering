package genFigure;

import java.io.*;
import java.util.List;

import net.sourceforge.plantuml.*;

public class GeneratePNG {
  public static void main (String[] args) throws IOException {
	SourceFileReader reader = new SourceFileReader(new File("tests/moviesVer1.txt"));
	List<GeneratedImage> list = reader.getGeneratedImages();
	//if (list == null) System.out.println("lista 
	// Generated files
	File png = list.get(0).getPngFile();
	}
}
