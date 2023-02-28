package services;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.widget.Toast;

import androidx.navigation.Navigation;

import com.google.firebase.iid.FirebaseInstanceIdReceiver;
import com.google.firebase.iid.internal.FirebaseInstanceIdInternal;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.ulp.instituto.MainActivity;
import com.ulp.instituto.Principal;
import com.ulp.instituto.R;
import com.ulp.instituto.login.Login;
import com.ulp.instituto.modelo.Persona;
import com.ulp.instituto.request.ApiRetrofit;
import com.ulp.instituto.request.Token;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MyFirebaseMessagingService extends FirebaseMessagingService {

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        // ...




        String TAG = "Mensaje Enviado";
        // TODO(developer): Handle FCM messages here.
        // Not getting messages here? See why this may be: https://goo.gl/39bRNJ
        Log.d(TAG, "From: " + remoteMessage.getFrom());
        // Check if message contains a data payload.
        if (remoteMessage.getData().size() > 0) {
            Log.d(TAG, "Message data payload: " + remoteMessage.getData());
            if (/* Check if data needs to be processed by long running job */ true) {
                // For long-running tasks (10 seconds or more) use Firebase Job Dispatcher.
                //scheduleJob();
            } else {
                // Handle message within 10 seconds
                //handleNow();
            }
        }

        // Check if message contains a notification payload.
        if (remoteMessage.getNotification() != null) {
            Looper.prepare();

                    AlertDialog.Builder builder = new AlertDialog.Builder(Principal.myActividad)
                            .setTitle("Notificación: ")
                            .setMessage(remoteMessage.getNotification().getBody())
                            .setCancelable(false)
                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                                    //do things
                                }
                            });
                    AlertDialog alert = builder.create();
                    alert.show();

        //   Toast.makeText(this, "Notificacion " + remoteMessage.getNotification().getBody(), Toast.LENGTH_LONG).show();
            Looper.loop();
        }

        // Also if you intend on generating your own notifications as a result of a received FCM
        // message, here is where that should be initiated. See sendNotification method below.
    }


    //    @Override
    public void onTokenRefresh() {
        // Get updated InstanceID token.


        //FirebaseInstanceId.getInstance().getToken();
        String refreshedToken = "Aca deberia venir tocken"; // FirebaseInstanceId.getInstance().getToken();
        String TAG = "Token ";
        Log.d(TAG, "Refreshed token: " + refreshedToken);

        // If you want to send messages to this application instance or
        // manage this apps subscriptions on the server side, send the
        // Instance ID token to your app server.
        //sendRegistrationToServer(refreshedToken);
    }



}
