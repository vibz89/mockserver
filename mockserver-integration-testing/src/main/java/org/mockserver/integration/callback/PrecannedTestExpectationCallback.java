package org.mockserver.integration.callback;

import org.mockserver.mock.action.ExpectationCallback;
import org.mockserver.model.HttpRequest;
import org.mockserver.model.HttpResponse;
import org.mockserver.model.HttpStatusCode;

import static org.mockserver.model.Header.header;
import static org.mockserver.model.HttpResponse.notFoundResponse;
import static org.mockserver.model.HttpResponse.response;

/**
 * @author jamesdbloom
 */
public class PrecannedTestExpectationCallback implements ExpectationCallback {

    public static HttpResponse httpResponse = response()
            .withStatusCode(HttpStatusCode.ACCEPTED_202.code())
            .withHeaders(
                    header("x-callback", "test_callback_header"),
                    header("Content-Length", "a_callback_response".getBytes().length),
                    header("Connection", "keep-alive")
            )
            .withBody("a_callback_response");

    @Override
    public HttpResponse handle(HttpRequest httpRequest) {
        if (httpRequest.getPath().getValue().endsWith("/callback")) {
            return httpResponse;
        } else {
            return notFoundResponse();
        }
    }
}
