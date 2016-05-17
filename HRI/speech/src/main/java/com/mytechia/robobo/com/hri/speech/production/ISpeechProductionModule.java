package com.mytechia.robobo.com.hri.speech.production;

import com.mytechia.robobo.com.hri.speech.production.android.TtsVoice;
import com.mytechia.robobo.framework.IModule;

import java.util.Collection;

/**
 * Created by luis on 5/4/16.
 */
public interface ISpeechProductionModule extends IModule {
    Integer PRIORITY_HIGH = 1;
    Integer PRIORITY_LOW = 0;



    void sayText(String text, Integer priority);
    /**
     *  Sets the current voice of the text to speech generator
     *  @param name The name of the voice to use
     *  @throws VoiceNotFoundException, UnsupportedOperationException
     */
    void selectVoice(String name) throws Exception;
    /**
     *  Sets the current voice of the text to speech generator
     *  @param voice The  voice to use
     *  @throws VoiceNotFoundException, UnsupportedOperationException
     */
    void selectTtsVoice(TtsVoice voice) throws Exception;
    Collection<TtsVoice> getVoices();
    /**
     *  Returns a collection of the available voices for text to speech
     *  @return A collection of the available voices names
     *  @throws UnsupportedOperationException
     */
    Collection<String> getStringVoices();
}
