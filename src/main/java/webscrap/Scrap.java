package webscrap;

import java.io.IOException;
import java.util.List;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.jsoup.Connection.Response;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;;


public class Scrap {

	private String jsonString = "";
	Scrap()
	{
		init();
	}
	
	public void init()
	{
		connect();
	}
	
	/*Connects to a web site using Jsoup
	 *@param url A String that contains the URL of the website to be connected
	 *@param agent A String that contains the user agent, this is used to identifty a web browser
	 *@param acceptLanguage A String that contains the language used by the website
	 *@param acceptEncoding A String that contains the encoding language
	 *@param referrer A String that contains the referrer 
	 *@return Response The response from the website   
	 */
	
	public Response jsoupConnect(String url,String agent,String acceptLanguage, String acceptEncoding,String referrer) throws IOException
	{
		Response res = Jsoup.connect(url)
				.userAgent(agent)
				.header("Accept-Language",acceptLanguage)
				.header("Accept-Encoding",acceptEncoding)
				.header("Connection", "keep-alive")
				.referrer(referrer)
				.followRedirects(true)
				.timeout(0)
				.execute();
		return res;
	
	}
	
	/* A method that begins the connection process
	 * 
	 */
	
	public void connect()
	{
		Products allProducts = new Products();
		
		try {
			
			Document doc = jsoupConnect("http://hiring-tests.s3-website-eu-west-1.amazonaws.com/2015_Developer_Scrape/5_products.html"
							,"Mozilla/5.0 (Windows NT 10.0; WOW64; rv:43.0) Gecko/20100101 Firefox/43.0",
							"en-US,en;q=0.5",
							"gzip, deflate",
							"http://www.google.com").parse();
			
			List<Element> elements = doc.getElementsByClass("product");
			for(Element ele:elements)
			{
				Product aProduct = new Product();
				aProduct.setTitle(ele.getElementsByClass("productInfo").text());
				String price = ele.getElementsByClass("pricePerUnit").get(0).text();
				double dbprice = new Double(price.replaceAll("[^0-9\\.]", "")).doubleValue();
				aProduct.setUnit_price(dbprice);
				
				aProduct.setDescription(getDescription(ele.getElementsByTag("a").attr("href")));
				Element image = ele.getElementsByTag("img").get(0);
				aProduct.setSize(""+imageSize(image.attributes().get("src")));
				allProducts.addProduct(aProduct);
				
			}
			jsonString = parse(allProducts);
			System.out.println(jsonString);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	
	}

	/*
	 * Returns a string of a json object
	 * @return String a json String
	 */
	public String getJsonString()
	{
		return this.jsonString;
	}
	
	/*Parse an object and return a Json String
	 *@param json A object to be parsed
	 *@exception @throws JsonGenerationException
	 *@exception @throws JsonMappingException
	 *@exception @throws IOException
	 *@return String the Json String
	 */
	public String parse(Object json) throws JsonGenerationException, JsonMappingException, IOException  {
	       org.codehaus.jackson.JsonFactory factory = new org.codehaus.jackson.JsonFactory();

	       ObjectMapper mapper = new ObjectMapper(factory);
		   return  mapper.writerWithDefaultPrettyPrinter().writeValueAsString(json);
		   
		}
	
	/*Gets a description for the current product from the website
	 *@param url A String that contains the url 
	 *@return String returns the description of the product 
	 */
	public String getDescription(String url) throws IOException
	{
		Response resultDescResponse = jsoupConnect(url,
				"Mozilla/5.0 (Windows NT 10.0; WOW64; rv:43.0) Gecko/20100101 Firefox/43.0",
				"en-US,en;q=0.5",
				"gzip, deflate",
				"http://hiring-tests.s3-website-eu-west-1.amazonaws.com/2015_Developer_Scrape/5_products.html");
		
		
		Document descDoc = resultDescResponse.parse();
		return descDoc.getElementsByClass("productText").get(0).text();
		
	}
	
	/*The image size of the picture of the product
	 *@param url The url of the image
	 *@return float returns the image size 
	 */
	public float imageSize(String url) throws IOException
	{
		Response resultImageResponse = Jsoup.connect(url)
				.ignoreContentType(true)
				.userAgent("Mozilla/5.0 (Windows NT 10.0; WOW64; rv:43.0) Gecko/20100101 Firefox/43.0")
				.header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,*//*;q=0.8")
				.header("Accept-Language", "en-US,en;q=0.5")
				.header("Accept-Encoding", "gzip, deflate")
				.referrer("http://hiring-tests.s3-website-eu-west-1.amazonaws.com/2015_Developer_Scrape/5_products.html")
				.timeout(0)
				.followRedirects(true).execute();
		
		String length = resultImageResponse.header("content-length");
		float ln = new Integer(length).floatValue();
		return ln;
	}
	
	
	
	/*
	 * The console initiation method	
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scrap aScrap = new Scrap();
	}

}
