package co.krypt.kryptonite;

import com.google.gson.JsonParseException;

import org.junit.Test;

import co.krypt.kryptonite.protocol.JSON;
import co.krypt.kryptonite.protocol.Request;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class JSONUnitTest {
    @Test(expected=JsonParseException.class)
    public void requestDeserializationMissingFields_fails() throws Exception {
        String json = "{\"request_id\":\"132\", \"v\": \"1.0.0\"}";
        JSON.fromJson(json, Request.class);
    }
    @Test
    public void requestDeserialization_works() throws Exception {
        String json = "{\"request_id\":\"132\", \"unix_seconds\":0, \"v\": \"1.0.0\"}";
        JSON.fromJson(json, Request.class);
    }
    @Test
    public void nestedRequestDeserialization_works() throws Exception {
        String json = "{\"request_id\":\"132\", \"unix_seconds\":0, \"sign_request\":{\"public_key_fingerprint\":\"aGkK\", \"data\": \"aGkK\"}, \"v\": \"1.0.0\"}";
        JSON.fromJson(json, Request.class);
    }
    @Test(expected = JsonParseException.class)
    public void nestedRequestDeserializationMissingFields_fails() throws Exception {
        String json = "{\"request_id\":\"132\", \"unix_seconds\":0, \"sign_request\":{\"public_key_fingerprint\":\"aGkK\"}, \"v\": \"1.0.0\"}";
        JSON.fromJson(json, Request.class);
    }
}