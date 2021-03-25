package it.polito.tdp.spellchecker.model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Dictionary {
	
	List<String> words;
	
	public Dictionary() {
		
		this.words = new ArrayList<String>();
	}

	public boolean loadDictionary(String lingua) {
		try {
			
		FileReader fr = new FileReader("src/main/resources/"+lingua+".txt");
		
			BufferedReader br= new BufferedReader(fr);
			String word;
			
			while ((word=br.readLine())!= null) {
				words.add(word);
			}
			br.close();
			return true;
			
		}catch (IOException e) {
			System.out.println("errore nella lettura del dizionario");
			e.printStackTrace();
			return false;
		}
		
	}
	
	public List<RichWord> spellCheckText (List<String> wordlist){
		ArrayList<RichWord> richwords= new ArrayList<RichWord>();
		
		for(String s:wordlist) {
			
			RichWord rw= new RichWord(s);
			richwords.add(rw);
			
			for(String w: words) {
				if(s.equalsIgnoreCase(w)) {
					rw.setCheck(true);
						break;
				}
			}
		}
		return richwords;
	}

//	
//	public List<RichWord> dichotomicSpellCheckText (List<String> inputTextList){
//
//		List<RichWord> parole = new ArrayList<RichWord>();
//		//List<RichWord> parole= new LinkedList<RichWord>();
//
//		for (String str : inputTextList) {
//
//			RichWord richWord = new RichWord(str);
//			if (binarySearch(str.toLowerCase()))
//				richWord.setCheck(true);
//			else
//				richWord.setCheck(false);
//			parole.add(richWord);
//		}
//
//		return parole;
//	}
//
//	private boolean binarySearch(String stemp) {
//		int inizio = 0;
//		int fine = words.size();
//
//		while (inizio != fine) {
//			int medio = inizio + (fine - inizio) / 2;
//			if (stemp.compareToIgnoreCase(words.get(medio)) == 0) {
//				return true;
//			} else if (stemp.compareToIgnoreCase(words.get(medio)) > 0) {
//				inizio = medio + 1;
//			} else {
//				fine = medio;
//			}
//		}
//
//		return false;
//	}
//	
//	}


public List<RichWord> dichotomicSpellCheckText (List<String>wordlist){
		
		ArrayList<RichWord> ritorna= new ArrayList<RichWord>();
		
		for(String s: wordlist) {
			
			RichWord rw= new RichWord(s);
			ritorna.add(rw);
			
			int inizio=0;
			int fine= words.size();
			
			while(inizio!=fine) {
				int medio= inizio+ (fine-inizio)/2;
			
				if(s.compareTo(words.get(medio))==0) {
					rw.setCheck(true);
					break;
					
				}else if (s.compareTo(words.get(medio))>0) {
					inizio=medio+1;
				
				}else  {
					fine= medio;
				
				}
			}
			
		}
		return ritorna;
	}
}
	
	

