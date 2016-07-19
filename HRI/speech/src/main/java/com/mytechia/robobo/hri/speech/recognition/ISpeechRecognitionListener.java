package com.mytechia.robobo.hri.speech.recognition;

/**
 * Created by luis on 5/4/16.
 */
public interface ISpeechRecognitionListener {
    void phraseRecognized(String phrase, Long timestamp);
}
