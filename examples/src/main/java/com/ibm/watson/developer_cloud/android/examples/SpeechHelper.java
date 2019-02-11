package com.ibm.watson.developer_cloud.android.examples;

import android.content.Context;

import com.ibm.watson.developer_cloud.android.speech_to_text.v1.dto.STTConfiguration;
import com.ibm.watson.developer_cloud.android.text_to_speech.v1.dto.TTSConfiguration;
import com.ibm.watson.developer_cloud.android.token_provider.v1.TokenProvider;

/**
 * Created by mihui on 8/6/16.
 */
public class SpeechHelper {

    private static final String TAG = "SpeechHelper";

    private static STTConfiguration sConfig = null;
    public static STTConfiguration makeSTTConfigWithContext(Context context) {
        if(sConfig == null) {
            sConfig = new STTConfiguration(STTConfiguration.AUDIO_FORMAT_OGGOPUS, STTConfiguration.SAMPLE_RATE_OGGOPUS);
//            STTConfiguration sConfig = new STTConfiguration(STTConfiguration.AUDIO_FORMAT_DEFAULT, STTConfiguration.SAMPLE_RATE_DEFAULT);
            sConfig.basicAuthUsername = context.getString(R.string.STTUsername);
            sConfig.basicAuthPassword = context.getString(R.string.STTPassword);

            String tokenFactoryURL = context.getString(R.string.STTTokenFactory);
            // token factory is the preferred authentication method (service credentials are not distributed in the client app)
            if (!tokenFactoryURL.equals(context.getString(R.string.defaultTokenFactory))) {
                // SpeechToText.sharedInstance().setTokenProvider(new MyTokenProvider(tokenFactoryURL));
                sConfig.setTokenProvider(new TokenProvider.MyTokenProvider(tokenFactoryURL));
            }
        }
        return sConfig;
    }

    private static TTSConfiguration tConfig = null;
    public static TTSConfiguration makeTTSConfigWithContext(Context context) {
        if(tConfig == null) {
            tConfig = new TTSConfiguration();
            tConfig.basicAuthUsername = context.getString(R.string.TTSUsername);
            tConfig.basicAuthPassword = context.getString(R.string.TTSPassword);
            tConfig.codec = TTSConfiguration.CODEC_OPUS;
            tConfig.appContext = context.getApplicationContext();

            String tokenFactoryURL = context.getString(R.string.TTSTokenFactory);
            // token factory is the preferred authentication method (service credentials are not distributed in the client app)
            if (!tokenFactoryURL.equals(context.getString(R.string.defaultTokenFactory))) {
//                TextToSpeech.sharedInstance().setTokenProvider(new MyTokenProvider(tokenFactoryURL));
                tConfig.setTokenProvider(new TokenProvider.MyTokenProvider(tokenFactoryURL));
            }
        }
        return tConfig;
    }

}
