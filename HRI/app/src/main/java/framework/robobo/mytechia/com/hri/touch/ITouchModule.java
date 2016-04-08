package framework.robobo.mytechia.com.hri.touch;

import com.mytechia.robobo.framework.IModule;

/**
 * Created by luis on 5/4/16.
 */
public interface ITouchModule extends IModule{
    void suscribe(ITouchListener listener);
    void unsuscribe(ITouchListener listener);
}
