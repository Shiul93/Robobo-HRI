package framework.robobo.mytechia.com.hri.speech.recognition;
import com.mytechia.commons.framework.exception.InternalErrorException;
import com.mytechia.robobo.framework.FrameworkManager;
import com.mytechia.robobo.framework.IModule;
/**
 * Created by luis on 5/4/16.
 */
public interface ISpeechRecognitionModule extends IModule {

    void addPhrase(String phrase);
    void removePhrase(String phrase);
    void cleanPhrases();
    void suscribe(ISpeechRecognitionListener listener);
    void unsuscribe(ISpeechRecognitionListener listener);

}
