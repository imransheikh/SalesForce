package restApiPOC;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicHeader;
import org.apache.http.util.EntityUtils;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class salesforceRestApiConnection {
	static HttpClient client;
	static HttpResponse response;
	static String testsuiteEntityId, caseId;
	public void getAccessToken() throws ParseException, ClientProtocolException, IOException, URISyntaxException {
		HttpPost post;
		client = HttpClientBuilder.create().build();

		String baseUrl = "https://uscis--uatg.my.salesforce.com/services/oauth2/token";
		URI uri = new URIBuilder().setPath(baseUrl).build();
		post = new HttpPost(uri);

		MultipartEntityBuilder builder = MultipartEntityBuilder.create();
		builder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);
		builder.addTextBody("username", "zabid_cis@acumensolutions.com.uatg");
		builder.addTextBody("password", "acumentest2");
		builder.addTextBody("grant_type", "password");
		builder.addTextBody("client_id", "3MVG9Zdl7Yn6QDKNxvqHsBnIdZTFzPXHahuULHHyA8_WiQSdvpyAw8OfOhwH56vNybycAZ1aZHQKqTgXK8_73");
		builder.addTextBody("client_secret", "AC563FEEDDD0B10FC86727DF2D2AFF01D49D943B0285DB04DEF0BDCE39FAFE55");
		HttpEntity entity = builder.build();
		post.setEntity(entity);
		response = client.execute(post);
		HttpEntity ENTITY_BODY = response.getEntity();
		String strEntityBody = ENTITY_BODY != null ? EntityUtils.toString(ENTITY_BODY, "UTF-8") : "";
		//System.out.println("The response is :" + strEntityBody);
		JSONParser parser = new JSONParser();
		JSONObject json = (JSONObject) parser.parse(strEntityBody);
		testsuiteEntityId = json.get("access_token").toString();
		//System.out.println("Access token-->" + testsuiteEntityId);
		testsuiteEntityId = "Bearer "+testsuiteEntityId;
		post.releaseConnection();
	}
	public void fetchResponse() throws URISyntaxException, ClientProtocolException, IOException {
		HttpGet get;
		client = HttpClientBuilder.create().build();
		String baseUrl = "https://uscis--uatg.my.salesforce.com/services/data/v42.0/query/?q=SELECT+Name,Type+FROM+Account";
		//URI uri = new URIBuilder().setPath(baseUrl).build();
		get = new HttpGet(baseUrl);
		get.addHeader("Authorization", testsuiteEntityId);
		response = client.execute(get);
		HttpEntity resEntity = response.getEntity();
		String strEntityBody = resEntity != null ? EntityUtils.toString(resEntity, "UTF-8") : "";
		System.out.println("The response is :" + strEntityBody);
		get.releaseConnection();
	}
	public static void createCase() {
		Header prettyPrintHeader = new BasicHeader("X-PrettyPrint", "1");
        System.out.println("\n_______________ Case INSERT _______________");
        String uri = "https://uscis--uatg.my.salesforce.com/services/data/v42.0/sobjects/Case";
        try {
            // create the JSON object containing the new lead details.
            JSONObject lead = new JSONObject();
            lead.put("Description", "API SI Creation Test");
            lead.put("Status", "New");
            lead.put("Origin", "Web");
            //System.out.println("JSON for lead record to be inserted:\n" + lead.toString(1));
            // Construct the objects needed for the request
            HttpClient httpClient = HttpClientBuilder.create().build();
            HttpPost httpPost = new HttpPost(uri);
            httpPost.addHeader("Authorization",testsuiteEntityId);
            httpPost.addHeader(prettyPrintHeader);
            // The message we are going to post
            StringEntity body = new StringEntity(lead.toString());
            body.setContentType("application/json");
            httpPost.setEntity(body);
            // Make the request
            HttpResponse response = httpClient.execute(httpPost);
            // Process the results
            int statusCode = response.getStatusLine().getStatusCode();
            if (statusCode == 201) {
                String response_string = EntityUtils.toString(response.getEntity());
                JSONParser parser = new JSONParser();
        		JSONObject json = (JSONObject) parser.parse(response_string);
                //JSONObject json = new JSONObject(response_string);
                // Store the retrieved lead id to use when we update the lead.
                caseId = json.get("id").toString();
                //String caseNumber = json.get("CaseNumber").toString();
                System.out.println("New Case id from response: " + caseId);
                System.out.println("New Case Number from response: " + response_string);
            } else {
                System.out.println("Insertion unsuccessful. Status code returned is " + statusCode);
            }
            httpPost.releaseConnection();
        } catch (Exception e) {
            System.out.println("Issue creating JSON or processing results");
            e.printStackTrace();
        } /*catch (IOException ioe) {
            ioe.printStackTrace();
        } catch (NullPointerException npe) {
            npe.printStackTrace();
        }*/
    }
	public void fetchNewlyCreatedSI() throws URISyntaxException, ClientProtocolException, IOException, ParseException {
		HttpGet get;
		client = HttpClientBuilder.create().build();
		String baseUrl = "https://uscis--uatg.my.salesforce.com/services/data/v42.0/sobjects/Case/"+caseId;
		//URI uri = new URIBuilder().setPath(baseUrl).build();
		get = new HttpGet(baseUrl);
		get.addHeader("Authorization", testsuiteEntityId);
		response = client.execute(get);
		HttpEntity resEntity = response.getEntity();
		String strEntityBody = resEntity != null ? EntityUtils.toString(resEntity, "UTF-8") : "";
		JSONParser parser = new JSONParser();
		JSONObject json = (JSONObject) parser.parse(strEntityBody);
		String serviceItemNo = json.get("CaseNumber").toString();
		System.out.println("The Service Item Number : " + serviceItemNo);
		System.out.println("Full details of the response : "+strEntityBody);
		get.releaseConnection();
	}
	public void createContact() throws InterruptedException, ClientProtocolException, IOException, ParseException, URISyntaxException {
		System.out.println("Creating new Contact");
		//Thread.sleep(2000);
		HttpPost post;
		client = HttpClientBuilder.create().build();
		String baseUrl = "https://uscis--uatg.my.salesforce.com/services/data/v42.0/sobjects/Contact";
		post = new HttpPost(baseUrl);
		JSONObject json = new JSONObject();
		json.put("FirstName", "TestAPI8");
		json.put("LastName", "Contact");

		StringEntity params = new StringEntity(json.toString());
		post.setEntity(params);
		post.addHeader("Authorization", testsuiteEntityId);
		post.addHeader("Content-Type", "application/json");
		response = client.execute(post);

		HttpEntity ENTITY_BODY = response.getEntity();
		String strEntityBody = ENTITY_BODY != null ? EntityUtils.toString(ENTITY_BODY, "UTF-8") : "";
		JSONParser parser = new JSONParser();
		JSONObject json1 = (JSONObject) parser.parse(strEntityBody);
		System.out.println("Full details of the response : "+strEntityBody);
		String id = json1.get("id").toString();
		fetchDetails(baseUrl+"/"+id);
		//deleteContacts(baseUrl+"/"+id);
		post.releaseConnection();
		
	}
	public void fetchDetails(String api) throws URISyntaxException, ClientProtocolException, IOException, ParseException {
		HttpGet get;
		client = HttpClientBuilder.create().build();
		String baseUrl = api;
		//URI uri = new URIBuilder().setPath(baseUrl).build();
		get = new HttpGet(baseUrl);
		get.addHeader("Authorization", testsuiteEntityId);
		response = client.execute(get);
		HttpEntity resEntity = response.getEntity();
		String strEntityBody = resEntity != null ? EntityUtils.toString(resEntity, "UTF-8") : "";
		System.out.println("Full details of the response : "+strEntityBody);
		get.releaseConnection();
	}
	public void deleteContacts(String api) throws URISyntaxException, ClientProtocolException, IOException, ParseException {
		HttpDelete delete;
		client = HttpClientBuilder.create().build();
		String baseUrl = api;
		//URI uri = new URIBuilder().setPath(baseUrl).build();
		delete = new HttpDelete(baseUrl);
		delete.addHeader("Authorization", testsuiteEntityId);
		try {
		response = client.execute(delete);
		HttpEntity resEntity = response.getEntity();
		String strEntityBody = resEntity != null ? EntityUtils.toString(resEntity, "UTF-8") : "";
		System.out.println("Response : "+response.getStatusLine().getStatusCode());
		System.out.println("Contact deleted Successfully.");
		} catch (Exception e) {
			System.out.println("Contact did not get deleted.");
		}
		delete.releaseConnection();
	}
	public static void main(String[] args) throws ClientProtocolException, ParseException, IOException, URISyntaxException, InterruptedException {
		// TODO Auto-generated method stub
		salesforceRestApiConnection conn = new salesforceRestApiConnection();
		conn.getAccessToken();
		//conn.fetchResponse();
		conn.createCase();
		conn.fetchNewlyCreatedSI();
		conn.createContact();
		//conn.deleteContacts("https://uscis--uatg.my.salesforce.com/services/data/v42.0/sobjects/Contact/0033500000DHnVBAA1");
	}

}
