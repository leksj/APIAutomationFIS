package org.test.apiTesting;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;

public class TC001_GETCall_FIS extends TestBaseClass {

	@Test
	public void TC001_verifyGETCall() {
		String url="https://api.coindesk.com/v1/bpi/currentprice.json";
		Response getMethodResponse = getMethod(url, null);
		
		ResponseBody responseBody = getMethodResponse.getBody();
		String responseBodyString = responseBody.asString();
		
		System.out.println("GET method response :"+getMethodResponse.asPrettyString());
		System.out.println("GET method response Body :"+responseBodyString);
		
		//JSon response validation
		Assert.assertEquals(getMethodResponse.getStatusCode(), 200);
		Assert.assertEquals(responseBodyString.contains("USD"), true);
		Assert.assertEquals(responseBodyString.contains("GBP"), true);
		Assert.assertEquals(responseBodyString.contains("EUR"), true);
		
		JsonPath jsonpath=getMethodResponse.jsonPath();
		Assert.assertEquals(jsonpath.getString("bpi.GBP.description"), "British Pound Sterling");

	}


}
