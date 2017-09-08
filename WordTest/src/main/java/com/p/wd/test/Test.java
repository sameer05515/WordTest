package com.p.wd.test;

import java.io.File;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang3.StringEscapeUtils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.prem.vocab.build.proj.VocabBuildConstants.Examples;
import com.prem.vocab.build.proj.VocabBuildConstants.Meanings;
import com.prem.vocab.build.proj.VocabBuildConstants.Word;
import com.prem.vocab.build.proj.util.XMLUtilityImpl;

public class Test {

	public static void main(String[] args) {		

	}
	
	public static List<com.p.wd.test.Wordi> getAllWords(){
		List<com.p.wd.test.Wordi> words=null;
		
		try {
			PrintStream ps = new PrintStream(new File("C:/Users/rim jhim/Desktop/p.txt"));
			XMLUtilityImpl xmlUtilityImpl = new XMLUtilityImpl();
			List<HashMap<String, String>> list = xmlUtilityImpl.getAllDescription();

			words=method3(ps, list, xmlUtilityImpl);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		return words;			
				
	}

	public static final String exerciseNodeContainer = "exerciseNodeContainer";
	public static final String exerciseHeaderNode = "exerciseHeaderNode";
	public static final String currentExerciseNodeAllowedTimeToDisplay = "currentExerciseNodeAllowedTimeToDisplay";
	public static final String exerciseNode = "exerciseNode";
	public static final String exerciseNodeImageList = "exerciseNodeImageList";
	public static final String exerciseNodeImage = "exerciseNodeImage";
	public static final String fileWriterURL = "http://127.0.0.1:8080/bce/fileWriter?documentId=";
	public static final String imageWidth = "500";
	public static final String imageHeight = "500";
	public static final String allowedTimeForWord = "120";

	private static List<Wordi> method3(PrintStream out, List<HashMap<String, String>> list, XMLUtilityImpl xmlUtilityImpl)
			throws Exception {

		List<Wordi> words = new ArrayList<Wordi>();
		Set<String> typeSet = new HashSet<String>();

		for (int i = list.size() - 1; i >= 0; i--) {
			HashMap<String, String> mapObject = list.get(i);

			if (mapObject.keySet() != null && mapObject.keySet().size() > 0) {

				typeSet.add(getValue(mapObject, Word.TYPE.getName()));

				Wordi premWord = new Wordi((i + 1)+"", getValue(mapObject, Word.node.getName()),
						getValue(mapObject, Word.TYPE.getName()));

				String mapValueForKey = getValue(mapObject, Meanings.node.getName());

				if (mapValueForKey != null && !mapValueForKey.trim().equalsIgnoreCase("")) {
					String[] strarr = mapValueForKey.trim().split("-->");
					for (String str : strarr) {
						premWord.addExample(str);
					}

				}

				mapValueForKey = getValue(mapObject, Examples.node.getName());

				if (mapValueForKey != null && !mapValueForKey.trim().equalsIgnoreCase("")) {
					String[] strarr = mapValueForKey.trim().split("-->");
					for (String str : strarr) {
						premWord.addMeaning(str);
					}
				}
				// out.println(premWord);
				words.add(premWord);
			}

		}

		ObjectMapper mapper = new ObjectMapper();

		/**
		 * Write object to file
		 */
		try {
			/**Plain JSON*/
			mapper.writeValue(new File("C:/Users/rim jhim/Desktop/1.txt"), words);
			/**Prettified JSON*/
			mapper.writerWithDefaultPrettyPrinter().writeValue(new File("C:/Users/rim jhim/Desktop/2.txt"), words); 
		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println(typeSet);
		
		return words;

	}

	public static String getValue(HashMap<String, String> mapObject, String key) {
		String ret = "";
		if (mapObject.containsKey(key)) {
			ret = mapObject.get(key);
			ret = (ret != null) ? ret : "";
		}

		return StringEscapeUtils.escapeJava(ret);
	}

}
