package framework.robobo.mytechia.com.hri.speech.recognition;

/**
 * Created by luis on 5/4/16.
 */
public abstract class ASpeechRecognitionModule implements ISpeechRecognitionModule{
    private ISpeechRecognitionListener listeners;
    @Override
    public void suscribe(ISpeechRecognitionListener listener) {

    }

    @Override
    public void unsuscribe(ISpeechRecognitionListener listener) {

    }

    protected void notifyPhrase(String phrase, Long timestamp){

    }
}
