package com.amazonaws.lambda.webhook;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.RequestStreamHandler;
import com.amazonaws.util.IOUtils;
import com.google.gson.Gson;

public class LambdaFunctionHandler implements RequestHandler<Object, Object>, RequestStreamHandler {

    public LambdaFunctionHandler() {}

	@Override
	public Object handleRequest(Object input, Context context) {
		return null;
	}

	@Override
	public void handleRequest(InputStream input, OutputStream output, Context context) throws IOException {
        LambdaLogger logger = context.getLogger();
		Impression[] impressions = new Gson().fromJson(IOUtils.toString(input), Impression[].class);
        for(Impression impression : impressions) {
        	logger.log(impression.toJson());
        }
        PrintWriter out = new PrintWriter(output);
        out.println("logged " + impressions.length + " impressions");
	}
    
}