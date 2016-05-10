package com.mytechia.robobo.com.hri.speech.production.android;

import android.annotation.TargetApi;
import android.os.Build;
import android.speech.tts.Voice;

import java.util.Locale;

/**
 * Created by luis on 2/5/16.
 */
public class TtsVoice {
    //TODO: El nombre puede llevar a equivoco, revisar
    //Is true if the api is lower than 19
    public boolean isDefault = true;
    //Voice object to wrap
    private Voice internalVoice = null;
    //Name of the voice
    private String voiceName = null;
    //Locale of the voice
    private Locale loc = null;
    //Name of the language
    private String voiceLanguage = null;

    /**
     * Constructor for api versions previous to 21
     */
    public TtsVoice(Locale newloc){
        isDefault = true;
        voiceName = "DefaultVoice";
        loc = newloc;
        voiceLanguage = loc.getLanguage();

    }

    /**
     * Constructor for api versions >= 21
     * @param vo The voice object to wrap
     */
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public TtsVoice(Voice vo){
        internalVoice = vo;
        loc = vo.getLocale();
        voiceLanguage = loc.getLanguage();
        voiceName = vo.getName();
        isDefault = false;

    }

    public String getVoiceName() {
        return voiceName;
    }

    public String getVoiceLanguage() {
        return voiceLanguage;
    }

    @Override
    public String toString() {
        return "TtsVoice{" +
                "voiceName='" + voiceName + '\'' +
                ", isDefault=" + isDefault +
                ", voiceLanguage='" + voiceLanguage + '\'' +
                '}';
    }

    public Voice getInternalVoice() {
        return internalVoice;
    }
}
