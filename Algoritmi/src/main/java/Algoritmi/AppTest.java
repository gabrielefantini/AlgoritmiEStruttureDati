package Algoritmi;
import Algoritmi.App;

public class AppTest{
    private static String s = "astronauta";
    private static String z = "astronave";

    public static void main(String[] Args) {
        App distanzaDiEditing = App.editDistance(s, z);
        App.operationsPrint(distanzaDiEditing.getOperations(), s.length(), z.length());
    }
}
