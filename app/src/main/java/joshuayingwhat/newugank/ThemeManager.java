package joshuayingwhat.newugank;

public enum ThemeManager {
    INSTANCE;

    private int colorPaimry;

    public void initColorParimry(int colorPaimry) {
        setColorParimay(colorPaimry);
    }

    public void setColorParimay(int colorPaimry) {
        this.colorPaimry = colorPaimry;
    }

    public int getColorParimay() {
        return colorPaimry;
    }
}
