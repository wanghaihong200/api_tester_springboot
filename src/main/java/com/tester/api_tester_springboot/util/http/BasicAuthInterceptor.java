package com.tester.api_tester_springboot.util.http;

import okhttp3.Credentials;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

/**
 * BasicAuth
 * @author llg
 *
 */
public class BasicAuthInterceptor implements Interceptor{

	private String credentials;

	public BasicAuthInterceptor(String user, String password) {
		this.credentials = Credentials.basic(user, password);
	}
	    
	@Override
	public Response intercept(Chain chain) throws IOException {
		Request request = chain.request();
        Request authenticatedRequest = request.newBuilder()
                    .header("Authorization", credentials).build();
        return chain.proceed(authenticatedRequest);
	}

}
