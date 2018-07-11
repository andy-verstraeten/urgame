package utilities;

public class CssManager {
    public static String basicStyle = "-fx-background-color: transparent;";
    public static String gameBGStyle = "-fx-background-image: url('/gfx/game/tableBG.png')";
    public static String basicTextStyle = "-fx-font-size: 22;";
    public static String smallTextStyle = "-fx-font-size: 18;";
    public static String boldTextStyle = basicTextStyle+"-fx-font-weight: bold;";
    public static String smallBoldTextStyle = smallTextStyle+"-fx-font-weight: bold;";
    public static String whiteText = "-fx-text-fill: white;";
    public static String normalTextStyle = basicTextStyle+"-fx-font-weight: normal;";
    public static String titleTextStyle =   "-fx-font-size: 26;" +
                                            "-fx-font-weight: bold;" +
                                            "-fx-underline: true;";
    public static String buttonStyle=   "-fx-base: #edb157;"+
                                        "-fx-pref-width: 200px;" +
                                        "-fx-focus-color: transparent;"+
                                        "-fx-font: 22 arial;";

    public static String menuPaneBG = "-fx-background-color: #edb157;";



    public static String gameButtonStyle= buttonStyle + "-fx-base: brown;";

    public static String requiredFieldStyle = "-fx-text-box-border: red;"+
            "-fx-border-width: 2px;";

    public static String removeRequiredFieldStyle= "-fx-border-width: 0;";
}
