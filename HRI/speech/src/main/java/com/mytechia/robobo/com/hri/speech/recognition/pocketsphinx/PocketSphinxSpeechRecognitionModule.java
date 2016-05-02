package com.mytechia.robobo.com.hri.speech.recognition.pocketsphinx;

import android.util.Log;

import com.mytechia.commons.framework.exception.InternalErrorException;
import com.mytechia.robobo.framework.FrameworkManager;

import java.io.File;
import java.io.IOException;
import java.util.AbstractCollection;
import java.util.HashSet;

import edu.cmu.pocketsphinx.*;

import com.mytechia.robobo.com.hri.speech.recognition.ASpeechRecognitionModule;
import com.mytechia.robobo.com.hri.speech.recognition.ISpeechRecognitionListener;

import static edu.cmu.pocketsphinx.SpeechRecognizerSetup.defaultSetup;


/**
 * Created by luis on 5/4/16.
 */
public class PocketSphinxSpeechRecognitionModule extends ASpeechRecognitionModule implements ISpeechRecognitionListener {

    private SpeechRecognizer recognizer;

    private static final String KEYPHRASE = "hey robobo";
    private AbstractCollection<String> recognizablePhrases;
    private static final Integer HASHSETSIZE = 128;

    @Override
    public void addPhrase(String phrase) {
        //TODO Las palabras a reconocer van dentro de un archivo .gram
        //TODO ¿Metodo para actualizar este fichero en funcion de la lista de frases?

        recognizablePhrases.add(phrase);

    }

    @Override
    public void removePhrase(String phrase) {

        if(!recognizablePhrases.remove(phrase)){
            Log.e("PS_SpeechRecognition","Phrase "+phrase+" not found in the recognizable set");
        }
    }

    @Override
    public void cleanPhrases() {

    }

    @Override
    public void startup(FrameworkManager frameworkManager) throws InternalErrorException {
        recognizablePhrases = new HashSet<String>(HASHSETSIZE);
    }

    @Override
    public void shutdown() throws InternalErrorException {
        recognizer.cancel();
        recognizer.shutdown();
    }

    @Override
    public void phraseRecognized(String phrase, Long timestamp) {
        notifyPhrase(phrase, timestamp);
    }

    private void setupRecognizer(File assetsDir) throws IOException {
        // The recognizer can be configured to perform multiple searches
        // of different kind and switch between them

        recognizer = defaultSetup()
                .setAcousticModel(new File(assetsDir, "en-us-ptm"))
                .setDictionary(new File(assetsDir, "cmudict-en-us.dict"))

                        // To disable logging of raw audio comment out this call (takes a lot of space on the device)
                .setRawLogDir(assetsDir)

                        // Threshold to tune for keyphrase to balance between false alarms and misses
                .setKeywordThreshold(1e-45f)

                        // Use context-independent phonetic search, context-dependent is too slow for mobile
                .setBoolean("-allphone_ci", true)

                .getRecognizer();
        //recognizer.addListener(this);

        /** In your application you might not need to add all those searches.
         * They are added here for demonstration. You can leave just one.
         */

        // Create keyword-activation search.
        //recognizer.addKeyphraseSearch(KWS_SEARCH, KEYPHRASE);



    }
}
