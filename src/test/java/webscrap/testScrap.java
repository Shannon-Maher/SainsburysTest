package webscrap;

import static org.junit.Assert.*;

import java.io.IOException;

import org.codehaus.jackson.map.ObjectMapper;
import org.jsoup.Connection.Response;
import org.junit.Test;

public class testScrap {
	Scrap scrapTest = new Scrap();
	
	@Test
	public void test() {
		try {
			Response resultResponse = scrapTest.jsoupConnect("http://hiring-tests.s3-website-eu-west-1.amazonaws.com/2015_Developer_Scrape/5_products.html",
					"Mozilla/5.0 (Windows NT 10.0; WOW64; rv:43.0) Gecko/20100101 Firefox/43.0",
					"en-US,en;q=0.5",
					"gzip, deflate",
					"www.google.co.uk");
			assertEquals(200,resultResponse.statusCode());
			assertEquals(true,isJSONValid(scrapTest.getJsonString()));
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static boolean isJSONValid(String jsonInString) {
	    try {
	       final ObjectMapper mapper = new ObjectMapper();
	       mapper.readTree(jsonInString);
	       return true;
	    } catch (IOException e) {
	       return false;
	    }
	  }
}
