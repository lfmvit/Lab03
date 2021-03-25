package it.polito.tdp.spellchecker.model;

import java.util.ArrayList;
import java.util.List;

public class ModelTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Dictionary model= new Dictionary();
		model.loadDictionary("Italian");
		
		ArrayList<String> prova= new ArrayList<String>();
		prova.add("cazzo");
		prova.add("ciao");
		prova.add("cavallo");
		prova.add("bastardo");
		prova.add("brecco");
		prova.add("breccolotti");
		
		
		System.out.println(model.dichotomicSpellCheckText(prova).toString());
		
		
	}

}
