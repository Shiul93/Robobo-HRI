package framework.robobo.mytechia.com.hri.speech.production.android;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Locale;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.speech.tts.Voice;
import android.support.v7.app.AppCompatActivity;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;
import com.mytechia.commons.framework.exception.InternalErrorException;
import com.mytechia.robobo.framework.FrameworkManager;

import framework.robobo.mytechia.com.hri.speech.production.ISpeechProductionModule;
import framework.robobo.mytechia.com.hri.speech.production.VoiceNotFoundException;

/**
 * Created by luis on 5/4/16.
 */
public class AndroidSpeechProductionModule implements ISpeechProductionModule {
    private TextToSpeech tts = null;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    public void sayText(String text) {

    }

    @Override
    /**
     *  Sets the current voice of the text to speech generator
     *  @param name The name of the voice to use
     *  @throws VoiceNotFoundException
     */
    public void selectVoice(String name) throws VoiceNotFoundException{

        Voice v = null;
        Collection<Voice> voices = tts.getVoices();
        //Iterate over the collection searching for the required voice
        for (Voice vo : voices) {
            if (vo.getName().equals(name)){
                v = vo;
            }
        }

        //Throw exception if no suitable voice is found
        if (v == null){
            throw new VoiceNotFoundException("Voice "+name+" not found");
        }

        tts.setVoice(v);

    }

    @Override
    /**
     *  Returns a collection of the available voices for text to speech
     *  @return The voice collection
     */
    public Collection<String> getVoices() {

        Collection<Voice> voices = tts.getVoices();
        Collection<String> results = new ArrayList<String>();
        for (Voice v : voices) {
           results.add(v.getName());
        }
        return results;

    }

    @Override
    public void startup(FrameworkManager frameworkManager) throws InternalErrorException {
        Context context = null; //Provisional a espera de recibirlo del manager

        tts = new TextToSpeech(context, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
            }
        }
        );
        Locale spanish = new Locale("es", "ES");
        tts.setLanguage(spanish);
    }

    @Override
    public void shutdown() throws InternalErrorException {

    }


}
