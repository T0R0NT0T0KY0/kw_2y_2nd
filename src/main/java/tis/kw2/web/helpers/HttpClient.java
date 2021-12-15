package tis.kw2.web.helpers;


import org.apache.http.client.fluent.Request;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.ContentType;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Map;

public class HttpClient {
	public static String get(String uri, Map<String, String> query) throws IOException {
		final Request request = Request.Get(addQuery(query, uri));
		return request.execute()
				.returnContent().asString();
	}

	private static String addQuery(Map<String, String> query, String uri) {
		try {
			URIBuilder uriBuilder = new URIBuilder(uri);
			query.forEach(uriBuilder::setParameter);
			return uriBuilder.toString();
		} catch (URISyntaxException e) {
			System.out.println(e.getMessage());
			return uri;
		}
	}

	public static String post(String url, Map<String, String> headers, Map<String, String> body) {
		try {
			final Request request = Request.Post(url);
			JSONObject json = new JSONObject();
			headers.forEach(request::addHeader);
			body.forEach(json::put);
			return request.bodyString(
							json.toString(),
							ContentType.APPLICATION_JSON).execute()
					.returnContent().asString();
		} catch (IOException e) {
			return e.getMessage();
		}
	}
}