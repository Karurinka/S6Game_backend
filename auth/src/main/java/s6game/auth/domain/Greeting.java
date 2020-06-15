package s6game.auth.domain;

public class Greeting {
    private String message;
    private String app;
    public void setMessage(String message) {
        this.message = message;
    }

    public void setApp(String app) {
        this.app = app;
    }

    // Although getters do not appear to be used, they are needed in order to create JSON to return to the client.
    public String getMessage() {
        return message;
    }

    public String getApp() {
        return app;
    }
}

