package model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.stream.XMLEventFactory;
import javax.xml.stream.XMLEventWriter;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Characters;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartDocument;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 * Class designed by the Singleton Design Pattern This class can be instantiated
 * only once
 */

public class SingletonThesaurus implements ThesaurusInterface {

	private static final String XML_FILENAME = "Thesaurus.xml";
	private static SingletonThesaurus thesaurus = null;
	private static Map<String, Set<String>> synonymPairs;

	private SingletonThesaurus() {
	}

	public static synchronized SingletonThesaurus getInstance() {
		if (thesaurus == null) {
			thesaurus = new SingletonThesaurus();
			setSynomymPairs(new HashMap<String, Set<String>>());
		}
		return thesaurus;
	}

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
	public void addSynonym(String word, String synonym) {
		assert isWellFormed() : "thesaurus not consistent";
		assert word != null : "null word cannot be added";
		assert synonym != null : "null synonym cannot be added";
		int preNr = nrOfSynonyms(word);
		if (synonymPairs.containsKey(word)) {
			Set<String> syns = synonymPairs.get(word);
			syns.add(synonym);

			assert nrOfSynonyms(word) == (preNr + 1) : "unsuccessful add";
			assert isWellFormed() : "thesaurus not consistent";
			updatingXML();
			loadUpdated();
			return;
		}
		Set<String> newSyns = new HashSet<String>();
		newSyns.add(synonym);
		synonymPairs.put(word, newSyns);

		assert nrOfSynonyms(word) == (preNr + 1) : "unsuccessful add";
		assert isWellFormed() : "thesaurus not consistent";
		updatingXML();
		loadUpdated();

	}

	/**
	 * delete a word with the whole definition
	 * 
	 * @param word
	 * @pre word != null
	 * @post numberOfWords@pre -1 = numberOfWords
	 * @invariant isWellFormed
	 */
	public void deleteWord(String word) {
		assert isWellFormed() : "thesaurus not consistent";
		assert word != null : "null word cannot be added";
		int preNr = nrOfkeys();
		synonymPairs.remove(word);
		assert isWellFormed() : "thesaurus not consistent";
		assert nrOfkeys() == (preNr - 1) : "unsuccessful delete";
		updatingXML();
		loadUpdated();
	}

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
	public void deleteSynonym(String word, String syno) {
		assert isWellFormed() : "thesaurus not consistent";
		assert word != null : "null word cannot be added";
		assert syno != null : "null synonym cannot be added";
		int preNr = nrOfSynonyms(word);
		if (synonymPairs.containsKey(word)) {
			Set<String> syns = synonymPairs.get(word);
			syns.remove(syno);

			if (nrOfSynonyms(word) == 0) {
				deleteWord(word);
			}
			assert nrOfSynonyms(word) == (preNr - 1) : "unsuccessful add";
			assert isWellFormed() : "thesaurus not consistent";
			updatingXML();
			loadUpdated();

		}

	}

	/**
	 * Search for a word
	 * 
	 * @param regex
	 * @pre regex != null
	 * @invariant isWellFormed
	 */
	public Set<String> serchSynonims(String regex) {

		assert regex != null;
		assert isWellFormed();
		if (regex.contains("?")) {
			regex = regex.replace("?", ".");
		} else if (regex.contains("*")) {
			regex = regex.replace("*", ".*");
		}

		Set<String> result = new HashSet<String>();
		try {
			Pattern pattern = Pattern.compile(regex);
			for (String key : synonymPairs.keySet()) {

				Matcher m = pattern.matcher(key);

				if (m.find()) {
					result.add(key);
					result.addAll(synonymPairs.get(key));
				}
			}
		} catch (PatternSyntaxException e) {
			System.out.println("Incorrect pattern syntax: " + regex);
		}
		return result;
	}

	public void printSyns() {
		for (Map.Entry<String, Set<String>> entry : synonymPairs.entrySet()) {

			System.out.print(" word: " + entry.getKey() + " syns: ");
			for (String syns : entry.getValue()) {
				System.out.print(" " + syns);
			}
			System.out.println();

		}
	}

	/**
	 * save and load from/to XML file
	 * 
	 */
	public void save() throws FileNotFoundException, XMLStreamException {
		XMLOutputFactory outputFactory = XMLOutputFactory.newInstance();
		// Create XMLEventWriter
		XMLEventWriter eventWriter = outputFactory.createXMLEventWriter(new FileOutputStream(XML_FILENAME));
		// Create a EventFactory
		XMLEventFactory eventFactory = XMLEventFactory.newInstance();
		XMLEvent end = eventFactory.createDTD("\n");
		// Create and write Start Tag
		StartDocument startDocument = eventFactory.createStartDocument();
		eventWriter.add(startDocument);
		// Create content open tag
		StartElement configStartElement = eventFactory.createStartElement("", "", "content");
		eventWriter.add(configStartElement);
		eventWriter.add(end);

		for (Map.Entry<String, Set<String>> word : synonymPairs.entrySet()) {
			StartElement sElement = eventFactory.createStartElement("", "", "WORD");
			eventWriter.add(sElement);
			eventWriter.add(end);
			encodeToXml(word.getKey(), eventWriter);
			EndElement eElement = eventFactory.createEndElement("", "", "WORD");
			eventWriter.add(eElement);
			eventWriter.add(end);
		}
		eventWriter.add(eventFactory.createEndElement("", "", "content"));
		eventWriter.add(eventFactory.createEndDocument());
		eventWriter.close();
	}

	public void load() throws ParserConfigurationException, SAXException, IOException {

		File fXmlFile = new File(XML_FILENAME);
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		Document doc = dBuilder.parse(fXmlFile);
		doc.getDocumentElement().normalize();
		NodeList nodeList = doc.getElementsByTagName("WORD");
		for (int i = 0; i < nodeList.getLength(); i++) {
			Node node = nodeList.item(i);
			if (node.getNodeType() == Node.ELEMENT_NODE) {
				Element element = (Element) node;
				String discriminant = element.getElementsByTagName("D").item(0).getTextContent();
				switch (discriminant) {
				case "syn":
					decodeFromXml(element);
					break;
				}
			}

		}
	}

	public void encodeToXml(String word, XMLEventWriter eventWriter) throws XMLStreamException {

		// createNode(eventWriter, "name", String.valueOf(this.name));
		int i = 0;
		createNode(eventWriter, "word", word);
		System.out.println(word);
		for (String syn : synonymPairs.get(word)) {
			createNode(eventWriter, "word" + i, syn);
			i++;
		}
		createNode(eventWriter, "howMany", Integer.toString(i));
		createNode(eventWriter, "D", "syn");
	}

	public void decodeFromXml(Element element) {
		System.out.println("what" + element.getElementsByTagName("word").item(0).getTextContent());
		System.out.println("whatNUMber" + element.getElementsByTagName("howMany").item(0).getTextContent());
		Set<String> syns = new HashSet<String>();
		int len = Integer.parseInt(element.getElementsByTagName("howMany").item(0).getTextContent());
		String key = element.getElementsByTagName("word").item(0).getTextContent();
		for (int i = 0; i < len; i++) {

			syns.add(element.getElementsByTagName("word" + i).item(0).getTextContent());
			// this.addSynonym(key,
			// element.getElementsByTagName("word"+i).item(0).getTextContent());
		}
		synonymPairs.put(key, syns);
		// updatingXML();

	}

	public static void createNode(XMLEventWriter eventWriter, String name, String value) throws XMLStreamException {
		XMLEventFactory eventFactory = XMLEventFactory.newInstance();
		XMLEvent end = eventFactory.createDTD("\n");
		XMLEvent tab = eventFactory.createDTD("\t");
		// Create Start node
		StartElement sElement = eventFactory.createStartElement("", "", name);
		eventWriter.add(tab);
		eventWriter.add(sElement);
		// Create Content
		Characters characters = eventFactory.createCharacters(value);
		eventWriter.add(characters);
		// Create End node
		EndElement eElement = eventFactory.createEndElement("", "", name);
		eventWriter.add(eElement);
		eventWriter.add(end);
	}

	public static Map<String, Set<String>> getSynomymPairs() {
		return synonymPairs;
	}

	public static void setSynomymPairs(Map<String, Set<String>> synomymPairs) {
		SingletonThesaurus.synonymPairs = synomymPairs;
	}

	public void updatingXML() {
		try {
			save();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		} catch (XMLStreamException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void loadUpdated() {
		try {
			load();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}
	}

	public Boolean isWellFormed() {

		for (Map.Entry<String, Set<String>> word : synonymPairs.entrySet()) {
			for (String syn : word.getValue()) {
				if (!synonymPairs.containsKey(syn)) {
					return false;
				}
			}
		}
		return true;
	}

	public int nrOfkeys() {
		return synonymPairs.entrySet().size();
	}

	public int nrOfSynonyms(String word) {
		int nrOfSyns = 0;
		for (Map.Entry<String, Set<String>> entry : synonymPairs.entrySet()) {
			if (entry.getKey().equals(word)) {
				for (String s : entry.getValue()) {
					nrOfSyns++;
				}
			}
		}
		System.out.println("nr of accounts......" + word + "..." + nrOfSyns);
		return nrOfSyns;
	}
}
