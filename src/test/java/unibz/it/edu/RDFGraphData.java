package unibz.it.edu;

import java.util.LinkedList;
import java.util.List;

public class RDFGraphData {
	
	public  List<RDFTriplet> basic1;
	public  List<RDFTriplet> basic2;
	public  List<RDFTriplet> basic3;

	public RDFGraphData() {
		basic1 = new LinkedList<RDFTriplet>();	
		basic1.add(new RDFTriplet(new RDFUri("http://semantic-web-book.org/uri"),
				  new RDFUri("http://www.example.org/publishedBy"),
				  new RDFUri("http://crcpress.com/uri")));
		
		basic2 = new LinkedList<RDFTriplet>();
		basic2.add(new RDFTriplet(new RDFUri("http://www.w3.org/"),
				  new RDFUri("http://www.w3.org/title"),
				  new RDFText("World Wide Web Consortium")));
		basic3 = new LinkedList<RDFTriplet>();
		
		basic3.add(new RDFTriplet(
					new RDFUri("http://semantic-web-book.org/uri"),
					new RDFUri("http://www.example.org/title"),
					new RDFText("Foundations of Semantic Web Technologies")));
		basic3.add(new RDFTriplet(
					new RDFUri("http://semantic-web-book.org/uri"),
					new RDFUri("http://www.example.org/publishedBy"),
					new RDFUri("http://crcpress.com/uri")));
		basic3.add(new RDFTriplet(
					new RDFUri("http://crcpress.com/uri"),
					new RDFUri("http://www.example.org/name"),
					new RDFText("CRC Press")));
	}
}
