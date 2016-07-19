package com.mytechia.robobo.framework.hri;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.mytechia.robobo.framework.RoboboManager;
import com.mytechia.robobo.framework.activity.DefaultRoboboActivity;
import com.mytechia.robobo.framework.exception.ModuleNotFoundException;
import com.mytechia.robobo.hri.R;
import com.mytechia.robobo.hri.speech.production.ITtsVoice;
import com.mytechia.robobo.hri.speech.production.VoiceNotFoundException;
import com.mytechia.robobo.hri.speech.production.android.AndroidSpeechProductionModule;
import com.mytechia.robobo.hri.speech.production.android.TtsVoice;

import java.util.Collection;

public class MainActivityTTS extends AppCompatActivity {
    //TODO MIRAR SI VOZ ESTA DESCARGADA
    int index =0;
    TtsVoice actualVoice = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /*final AndroidSpeechProductionModule aspm = new AndroidSpeechProductionModule();
        try {
            aspm.startupTest(this.getApplicationContext());
        } catch (InternalErrorException e) {
            e.printStackTrace();
        }*/


        AndroidSpeechProductionModule speechM = null;
        try {
            speechM = RoboboManager.getInstance().getModuleInstance(AndroidSpeechProductionModule.class);
        } catch (ModuleNotFoundException e) {
            e.printStackTrace();
        }

        final AndroidSpeechProductionModule aspm = speechM;

        final TextView tv = (TextView) findViewById(R.id.textView);

        final EditText inputText = (EditText) findViewById(R.id.editText);

        final Button nextButton = (Button) findViewById(R.id.nextButton);

        final Button prevButton = (Button) findViewById(R.id.prevButton);

        final Button talkButton = (Button) findViewById(R.id.talkButton);

        final Button setButton = (Button) findViewById(R.id.setVoiceButton);


       // final ArrayList<String> test =new ArrayList<String>(aspm.getVoices());

        try {
            talkButton.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                 aspm.sayText(inputText.getText().toString(), AndroidSpeechProductionModule.PRIORITY_HIGH);
                    inputText.clearComposingText();


                }
            });

            nextButton.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    final Collection<ITtsVoice> voiceCol =aspm.getVoices();
                    if (index < voiceCol.size()) {
                        index = index + 1;
                    }

                    TtsVoice auxVoice =(TtsVoice) (voiceCol.toArray()[index]);
                    tv.setText(auxVoice.getVoiceName());
                    actualVoice = auxVoice;


                }
            });

            prevButton.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    final Collection<ITtsVoice> voiceCol =aspm.getVoices();
                    if (index != 0) {
                        index = index - 1;
                    }
                    TtsVoice auxVoice =(TtsVoice) (voiceCol.toArray()[index]);
                    tv.setText(auxVoice.getVoiceName());
                    actualVoice = auxVoice;

                }
            });

            setButton.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    try {
                        aspm.selectVoice(actualVoice.getVoiceName());
                    } catch (VoiceNotFoundException vnfe){
                        tv.setText("Voice not found :(");
                    }


                }
            });
        } catch (NullPointerException e) {
            e.printStackTrace();
        }


    }
}
