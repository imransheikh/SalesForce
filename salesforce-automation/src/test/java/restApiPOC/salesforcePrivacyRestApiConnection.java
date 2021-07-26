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
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.salesforcetest.shared.Constants;

public class salesforcePrivacyRestApiConnection {
	static HttpClient client;
	static HttpResponse response;
	static String testsuiteEntityId, caseId, userId;
	public void getAccessToken() throws ParseException, ClientProtocolException, IOException, URISyntaxException {
		HttpPost post;
		client = HttpClientBuilder.create().build();

		String baseUrl = Constants.salesforce_url+"services/oauth2/token";
		URI uri = new URIBuilder().setPath(baseUrl).build();
		post = new HttpPost(uri);
		try {
		MultipartEntityBuilder builder = MultipartEntityBuilder.create();
		builder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);
		builder.addTextBody("username", Constants.salesforce_pstaff2_username_Admin);
		builder.addTextBody("password", Constants.salesforce_pstaff2_password_Admin);
		builder.addTextBody("grant_type", "password");
		//builder.addTextBody("client_id", "3MVG9Zdl7Yn6QDKPQ5nV4vVl4lMimaVWQeZEjaeZwE_zAurDQTfI7k2_kTbo1cqhfNYHWo7xCs6vgbHBPC5AN");//uat
		//builder.addTextBody("client_secret", "C515D85E3A7416D274441F8CCB24D5AEA3705BE817A8AE521F10D97440DEE0B1");
		builder.addTextBody("client_id", "3MVG9ic.6IFhNpPqO9VpPQZGFFYDU.VbAG3I3QRrbz9Q1nbqOPHrdsgPlgDCnsncetG21gytm7tZNPaXSc9CI");
		builder.addTextBody("client_secret", "58656879A27AED8EEB113AA823DA98175CF6ADCF6CF277E55BC062EC01048D44");
		HttpEntity entity = builder.build();
		post.setEntity(entity);
		response = client.execute(post);
		HttpEntity ENTITY_BODY = response.getEntity();
		String strEntityBody = ENTITY_BODY != null ? EntityUtils.toString(ENTITY_BODY, "UTF-8") : "";
		JSONParser parser = new JSONParser();
		JSONObject json = (JSONObject) parser.parse(strEntityBody);
		testsuiteEntityId = json.get("access_token").toString();
		testsuiteEntityId = "Bearer "+testsuiteEntityId;
		System.out.println(strEntityBody);
		post.releaseConnection();
		} catch (Exception e) {
			post.releaseConnection();
			e.printStackTrace();
		}
	}
	public void fetchUserId() throws URISyntaxException, ClientProtocolException, IOException, ParseException {
		HttpGet get;
		client = HttpClientBuilder.create().build();
		String baseUrl = Constants.salesforce_url+"services/data/v42.0/query/?q=SELECT+Id+FROM+User+WHERE+Name+=+'EXSO+Service+Item+Manager'";
		get = new HttpGet(baseUrl);
		get.addHeader("Authorization", testsuiteEntityId);
		response = client.execute(get);
		HttpEntity resEntity = response.getEntity();
		String strEntityBody = resEntity != null ? EntityUtils.toString(resEntity, "UTF-8") : "";
		System.out.println("The response is :" + strEntityBody);
		JSONParser parser = new JSONParser();
		JSONObject json = (JSONObject) parser.parse(strEntityBody);
		JSONArray jsonarray1 = (JSONArray) json.get("records");//4
        for (int j = 0; j < jsonarray1.size(); j++) {
            userId = ((JSONObject) jsonarray1.get(j)).get("Id").toString();
            break;
        }
		//userId = json.get("records").toString();
		System.out.println(userId);
		get.releaseConnection();
	}
	public static void createCase(String subjectline) {
		Header prettyPrintHeader = new BasicHeader("X-PrettyPrint", "1");
        System.out.println("\n_______________ Case INSERT _______________");
        String uri = Constants.salesforce_url+"services/data/v42.0/sobjects/Case";
        try {
            // create the JSON object containing the new lead details.
            JSONObject lead = new JSONObject();
            lead.put("Subject", subjectline);
            lead.put("Status", "New");
            //lead.put("Origin", "Legacy System");
            lead.put("OwnerId", userId);
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
            //if (statusCode == 201) {
                String response_string = EntityUtils.toString(response.getEntity());
                System.out.println("New Case Number from response: " + response_string);
                JSONParser parser = new JSONParser();
        		JSONObject json = (JSONObject) parser.parse(response_string);
                //JSONObject json = new JSONObject(response_string);
                // Store the retrieved lead id to use when we update the lead.
                caseId = json.get("id").toString();
                //String caseNumber = json.get("CaseNumber").toString();
                System.out.println("New Case id from response: " + caseId);
            //} else {
            //    System.out.println("Insertion unsuccessful. Status code returned is " + statusCode);
            //}
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
	public String fetchNewlyCreatedSI() throws URISyntaxException, ClientProtocolException, IOException, ParseException {
		HttpGet get;
		client = HttpClientBuilder.create().build();
		String baseUrl = Constants.salesforce_url+"services/data/v42.0/sobjects/Case/"+caseId;
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
		return serviceItemNo;
	}
	public static void main(String[] args) throws ClientProtocolException, ParseException, IOException, URISyntaxException, InterruptedException {
		// TODO Auto-generated method stub
		salesforcePrivacyRestApiConnection conn = new salesforcePrivacyRestApiConnection();
		conn.getAccessToken();
		conn.fetchUserId();
		//conn.createCase();
		//conn.fetchNewlyCreatedSI();
	}

}
