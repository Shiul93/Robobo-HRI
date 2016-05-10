package com.mytechia.robobo.com.hri.speech.production.android;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Locale;

import android.content.Context;
import android.speech.tts.TextToSpeech;
import android.speech.tts.Voice;
import android.util.Log;

import com.mytechia.commons.framework.exception.InternalErrorException;
import com.mytechia.robobo.framework.FrameworkManager;

import com.mytechia.robobo.com.hri.speech.production.ISpeechProductionModule;
import com.mytechia.robobo.com.hri.speech.production.VoiceNotFoundException;

/**
 * Created by luis on 5/4/16.
 */
public class AndroidSpeechProductionModule implements ISpeechProductionModule {

    private TextToSpeech tts = null;
    private Locale loc = null;
    private Context context = null;


    @Override
    /**
     * Says a text through the phone speaker
     * @param text The text to be said
     * @param priority The priority of the phrase, ISpeechProductionModule.PRIORITY_HIGH / LOW
     */
    public void sayText(String text, Integer priority) {

       /* if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP){

        } else{
            tts.speak(text, TextToSpeech.QUEUE_ADD, null);

        }*/
        if (priority == ISpeechProductionModule.PRIORITY_HIGH){
            tts.speak(text, TextToSpeech.QUEUE_FLUSH, null);
        }else
        if (priority == ISpeechProductionModule.PRIORITY_LOW){
            tts.speak(text, TextToSpeech.QUEUE_ADD, null);
        }
    }

    /**
     * Sets a new locale for the Text To Speech object
     * @param newloc
     */
    public void setLocale(Locale newloc){
        //TODO: Podria añadirse un metodo para cambiar lenguaje
        loc = newloc;
        tts.setLanguage(loc);
    }

    @Override
    /**
     *  Sets the current voice of the text to speech generator
     *  @param name The name of the voice to use
     *  @throws VoiceNotFoundException, UnsupportedOperationException
     */
    public void selectVoice(String name) throws VoiceNotFoundException, UnsupportedOperationException{

        //Check if the android version of the device supports voices for the tts


        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP){
            //Iterate over the voices and if the desired voice is found, set it in the tts object

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
                Log.e("TTS","Error: voice "+name+"not found");
                throw new VoiceNotFoundException("Voice "+name+" not found");


            }

            tts.setVoice(v);
        } else{
            //TODO Mirar a ver que se hace con esto, ¿Objeto voz por defecto?
            throw new UnsupportedOperationException(
                    "Selecting voices is not supported for this " +
                            "Android version, minimum api level 21"
            );

        }


    }

    public void selectTtsVoice(TtsVoice voice) throws VoiceNotFoundException, UnsupportedOperationException{

        //Check if the android version of the device supports voices for the tts


        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP){
            //Iterate over the voices and if the desired voice is found, set it in the tts object



            tts.setVoice(voice.getInternalVoice());
        } else{
            //TODO Mirar a ver que se hace con esto, ¿Objeto voz por defecto?
            throw new UnsupportedOperationException(
                    "Selecting voices is not supported for this " +
                            "Android version, minimum api level 21"
            );

        }


    }

    @Override
    /**
     *  Returns a collection of the available voices for text to speech
     *  @return A collection of the available voices names
     *  @throws UnsupportedOperationException
     */
    public Collection<String> getStringVoices() throws  UnsupportedOperationException{



        Collection<String> results = new ArrayList<String>();




        //Check if the android version of the device supports voices for the tts

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP){
            Collection<Voice> voices = tts.getVoices();
            for (Voice v : voices) {
                results.add(v.getName());
            }
            return results;
        } else{
            //TODO: Comprobar que hay dentro del features
            //TODO Mirar a ver que se hace con esto, ¿Objeto voz por defecto?
            //Locale locale = tts.getLanguage();
            //Set<String> features = tts.getFeatures(locale);
            //String[] featureArray = new String[features.size()];
            //features.toArray(featureArray);

            //Throw a exception for earlier versions of the api which don't support voices
            throw new UnsupportedOperationException(
                    "Selecting voices is not supported for this " +
                            "Android version, minimum api level 21"
            );
        }



        /*
        */



    }

    public Collection<TtsVoice> getVoices() throws  UnsupportedOperationException{


        //TODO Duda: ¿Generar la lista de voces al crear el tts?
        Collection<TtsVoice> results = new ArrayList<TtsVoice>();




        //Check if the android version of the device supports voices for the tts

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP){
            Collection<Voice> voices = tts.getVoices();
            for (Voice v : voices) {
                TtsVoice ttsV = new TtsVoice(v);
                results.add(ttsV);
            }
            return results;
        } else{
            //Returns the default voice
            Locale locale = tts.getLanguage();
            results.add(new TtsVoice((locale)));
           return  results;
        }



        /*
        */



    }

    @Override
    /**
     *  Starts the TextToSpeech engine
     *  @param frameworkManager instance of the Robobo framework manager
     *  @throws InternalErrorException
     */
    //TODO Igual estaba bien pasarle el locale de alguna forma (Por defecto el ingles)
    public void startup(FrameworkManager frameworkManager) throws InternalErrorException {
        context = frameworkManager.getApplicationContext();

        //Default language, English
        loc = Locale.UK;

        //Creation the TTS object
        tts = new TextToSpeech(context, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {

                tts.setLanguage(loc);
            }

        }
        );
        //tts.setOnUtteranceProgressListener(new TtsUtteranceListener());

    }

    //TODO Igual estaba bien pasarle el locale de alguna forma (Por defecto el ingles)
    public void startupTest(Context co) throws InternalErrorException {
        context = co;

        //Default language, English
        loc = Locale.UK;

        //Creation the TTS object
        tts = new TextToSpeech(context, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {

                tts.setLanguage(loc);
            }

        }
        );
       // tts.setOnUtteranceProgressListener(new TtsUtteranceListener());

    }

    @Override
    /**
     * Stops the TextTOSpeech engine and frees the resources
     * @throws InternalErrorException
     */
    public void shutdown() throws InternalErrorException {
        //Liberación de recursos del text to speech
        tts.shutdown();

    }


}
