package businessObject;

import com.epam.syfy.tests.Properties;

public class UserEditor extends BaseUser {
    private static UserEditor instance;
    private UserEditor(){
        this.setUserEmail(Properties.editorEmailValue);
        this.setUserPassword(Properties.editorPasswordValue);
    }
    public static UserEditor getInstance(){
        if(instance == null){
            instance = new UserEditor();
        }
        return instance;
    }
}
