package controller;

import model.SingletonThesaurus;
import view.Gui;

public class MainDriver {

	public static void main(String[] args) {

		SingletonThesaurus thesaurus = SingletonThesaurus.getInstance();
		String word = new String("error");
		String syn = new String("pretty");
		// thesaurus.addSynonym(word, "nice");
		// thesaurus.addSynonym(word, "beautiful");
		// thesaurus.addSynonym(word, syn);
		// thesaurus.addSynonym("bad", "verybad");

		// thesaurus.printSyns();
		// thesaurus.deleteSynonym(word, syn);
		// thesaurus.printSyns();
		// thesaurus.updatingXML();
		
		thesaurus.loadUpdated();
		Gui gui = new Gui();
		Controller con = new Controller(gui, thesaurus);
	
		
	}

}
