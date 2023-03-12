package libs;

public class ScreenShot {
    private String name = "";
    private byte[] screenShotImg;

    public ScreenShot(byte[] screenShotImg) {
        this.screenShotImg = screenShotImg;
    }

    public ScreenShot(String name, byte[] screenShotImg) {
        this.name = name;
        this.screenShotImg = screenShotImg;
    }

    public String getName() {
        return name;
    }

    public byte[] getScreenShotImg() {
        return screenShotImg;
    }
}
