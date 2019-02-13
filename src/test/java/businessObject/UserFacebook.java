package businessObject;

public class UserFacebook extends BaseUser {
    public UserFacebook(String userEmail, String userPassword) {
        this.setUserEmail(userEmail);
        this.setUserPassword(userPassword);
    }
}
