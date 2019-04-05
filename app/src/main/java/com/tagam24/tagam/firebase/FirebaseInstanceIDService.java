package com.tagam24.tagam.firebase;

import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

/**
 * Created by User on 15.01.2019.
 */

public class FirebaseInstanceIDService extends FirebaseInstanceIdService {
    @Override
    public void onTokenRefresh() {
        String token= FirebaseInstanceId.getInstance().getToken();
        registerToken(token);
        Log.d("token12345",token);
    }

    void registerToken(String token){
        register_token.get_Data(token);
    }
}
