package com.mytechia.robobo.com.hri.speech.production;

import com.mytechia.robobo.framework.IModule;

import java.util.Collection;

/**
 * Created by luis on 5/4/16.
 */
public interface ISpeechProductionModule extends IModule {

    //TODO: Prioridad de habla
    //TODO: Objeto voz
    //TOTO: Modulos android

    void sayText(String text);
    void selectVoice(String name) throws Exception;
    Collection<String> getVoices();
}
