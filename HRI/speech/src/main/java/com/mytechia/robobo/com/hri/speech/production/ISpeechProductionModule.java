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
    void selectVoice(String name) throws Exception;
    void selectTtsVoice(TtsVoice voice) throws Exception;
    Collection<TtsVoice> getVoices();
    Collection<String> getStringVoices();
}
