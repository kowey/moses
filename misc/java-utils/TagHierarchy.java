// $Id$

import java.io.*;
import java.util.*;

class TagHierarchy
{
	public static void main(String[] args) throws Exception
	{
		System.err.println("Starting...");

		InputStreamReader inStream = args.length > 0 ? new FileReader(args[0]) : new InputStreamReader(System.in); 
		OutputStreamWriter outStream = args.length > 1 ?  new FileWriter(args[1]) : new OutputStreamWriter(System.out); 
		
		new TagHierarchy(inStream, outStream);
		
		System.err.println("End...");
	}

	public TagHierarchy(Reader inStream, Writer outStream) throws Exception
	{
		BufferedReader inFile = new BufferedReader(inStream); 
		BufferedWriter outFile = new BufferedWriter(outStream); 
		
		// tokenise
		String inLine;
		while ((inLine = inFile.readLine()) != null)
		{
			if (inLine.compareTo("null") != 0)
				OutputHierarchy(inLine, outFile);
		}
	}
	
	public void OutputHierarchy(String inLine, BufferedWriter outFile) throws Exception
	{
		int level = 0;
		StringTokenizer st = new StringTokenizer(inLine);
	     while (st.hasMoreTokens()) 
	     {
	    	 String parsed = st.nextToken();
	    	 if (parsed.substring(0, 1).compareTo("(") == 0)
	    	 { // start of new node
	    		 outFile.write('\n');
	    		 for (int currLevel = 0 ; currLevel < level ; currLevel++)
	    		 {
	    			 outFile.write(' ');
	    		 }
	    		 String tag = parsed.substring(1, parsed.length());
	    		 outFile.write(tag);
	    		 level++;
	    	 }
	    	 else
	    	 { // closing nodes
	    		 int firstBracket = parsed.indexOf(')');
	    		 int noBracket = parsed.length() - firstBracket;
	    		 String tag = parsed.substring(0, firstBracket);
	    		 outFile.write(" == " + tag);
	    		 level -= noBracket;
	    	 }
	     }
	     outFile.write('\n');
	}
}

class Node
{
	public String tag, word;
	
	public Node(String tag, String word)
	{
		this.tag = tag;
		this.word = word;
	}
}
