package co.krypt.kryptonite.transport;

import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

import co.krypt.kryptonite.exception.TransportException;

public class FirebaseInstanceIDService extends FirebaseInstanceIdService {
    private static final String TAG = "FirebaseInstanceID";

    public FirebaseInstanceIDService() {
    }

    @Override
    public void onTokenRefresh() {
        // Get updated InstanceID token.
        String refreshedToken = FirebaseInstanceId.getInstance().getToken();
        Log.d(TAG, "Refreshed token: " + refreshedToken);

        // If you want to send messages to this application instance or
        // manage this apps subscriptions on the server side, send the
        // Instance ID token to your app server.
        try {
            SNSTransport.getInstance(getApplicationContext()).setDeviceToken(refreshedToken);
        } catch (TransportException e) {
            e.printStackTrace();
        }
    }
}
