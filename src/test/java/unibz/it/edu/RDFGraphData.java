package unibz.it.edu;

import java.util.LinkedList;
import java.util.List;

import unibz.it.edu.rdfElements.RDFLiteral;
import unibz.it.edu.rdfElements.RDFTriplet;
import unibz.it.edu.rdfElements.RDFUri;

public class RDFGraphData {

	public List<RDFTriplet> basic1;
	public List<RDFTriplet> basic2;
	public List<RDFTriplet> basic3;

	public RDFGraphData() {
		String ns1 = "http://semantic-web-book.org/";
		String ns2 = "http://www.example.org/";
		
		basic1 = new LinkedList<RDFTriplet>();
		basic1.add(new RDFTriplet(
									new RDFUri("uri", ns1),
									new RDFUri("publishedBy", ns2),
									new RDFUri("http://crcpress.com/uri")));

		basic2 = new LinkedList<RDFTriplet>();
		basic2.add(new RDFTriplet(
				new RDFUri("http://www.w3.org/"),
				new RDFUri("http://www.w3.org/title"),
				new RDFLiteral("World Wide Web Consortium")));
		
		basic3 = new LinkedList<RDFTriplet>();
		basic3.add(new RDFTriplet(
				new RDFUri("uri", ns1), 
				new RDFUri("title", ns2),
				new RDFLiteral("Foundations of Semantic Web Technologies")));
		basic3.add(new RDFTriplet(
				new RDFUri("uri", ns1),
				new RDFUri("publishedBy", ns2),
				new RDFUri("http://crcpress.com/uri")));
		basic3.add(new RDFTriplet(new RDFUri("http://crcpress.com/uri"),
				new RDFUri("http://www.example.org/name"), new RDFLiteral(
						"CRC Press")));
	}
}
