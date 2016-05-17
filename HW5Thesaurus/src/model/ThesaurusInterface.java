package model;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Set;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.stream.XMLStreamException;

import org.xml.sax.SAXException;

public interface ThesaurusInterface {

	/**
	 * Adds a new word
	 * 
	 * @param word
	 * @param synonym
	 * @pre word != null
	 * @pre synonym!= null
	 * @post numberOfWords@pre +1 = numberOfWords
	 * @invariant isWellFormed
	 */
	public void addSynonym(String word, String synonym);

	/**
	 * delete a word with the whole definition
	 * 
	 * @param word
	 * @pre word != null
	 * @post numberOfWords@pre -1 = numberOfWords
	 * @invariant isWellFormed
	 */
	public void deleteWord(String word);

	/**
	 * Delete a synonym
	 * 
	 * @param word
	 * @param syno
	 * @pre word!= null
	 * @pre syno != null
	 * @post numberOfSynonyms@pre -1 = numberOfSynonyms
	 * @invariant isWellFormed
	 */
	public void deleteSynonym(String word, String syno);

	/**
	 * Search for a word
	 * 
	 * @param regex
	 * @pre regex != null
	 * @invariant isWellFormed
	 */
	public Set<String> serchSynonims(String regex);

	/**
	 * save and load from/to XML file
	 * 
	 */
	public void save() throws FileNotFoundException, XMLStreamException;

	public void load() throws ParserConfigurationException, SAXException, IOException;

}
