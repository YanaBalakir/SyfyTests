package businessObject;

public class UserEditor extends BaseUser {
    public UserEditor(String userEmail, String userPassword) {
        this.setUserEmail(userEmail);
        this.setUserPassword(userPassword);
    }
}
