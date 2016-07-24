package com.geoshare.xml_loader;

import java.io.File;

import javax.inject.Singleton;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.geoshare.entities.Note;
import com.geoshare.repositories.NoteRepository;

@Singleton
@Service
@Component
public class XmlLoader
{

	@Autowired
	private NoteRepository noteRepository;

	public void ImportFile()
	{
		try
		{
			File folder = new File("../geoshare_xml");
			File[] files = folder.listFiles();

			for (final File xmlFile : files)
			{
				DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
				DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
				Document doc = dBuilder.parse(xmlFile);

				// optional, but recommended
				// read this -
				// http://stackoverflow.com/questions/13786607/normalization-in-dom-parsing-with-java-how-does-it-work
				doc.getDocumentElement().normalize();

				System.out.println("Root element :" + doc.getDocumentElement().getNodeName());

				NodeList nList = doc.getElementsByTagName("wpt");

				System.out.println("----------------------------");

				for (int temp = 0; temp < nList.getLength(); temp++)
				{

					Node nNode = nList.item(temp);

					System.out.println("\nCurrent Element :" + nNode.getNodeName());

					if (nNode.getNodeType() == Node.ELEMENT_NODE)
					{

						Element eElement = (Element) nNode;

						String latitude = eElement.getAttribute("lat");
						String longitude = eElement.getAttribute("lon");
						String name = eElement.getElementsByTagName("name").item(0).getTextContent();
						String text = eElement.getElementsByTagName("desc").item(0).getTextContent();

						double latitudeFloat = Double.parseDouble(latitude);
						double longitudeFloat = Double.parseDouble(longitude);

						System.out.println("Lat : " + latitude);
						System.out.println("lon : " + longitude);
						System.out.println("Name : " + name);
						System.out.println("desc : " + text);

						Note note = new Note();
						note.setText(name);
						note.setLatitude(latitudeFloat);
						note.setLongitude(longitudeFloat);
						noteRepository.SaveNote(note);

					}
				}

				xmlFile.delete();

			}
		} catch (Exception e)
		{
			e.printStackTrace();
		}

	}

}
