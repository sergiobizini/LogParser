package MrFind;

import java.io.File;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Find {
    private static String regexp;

    public Find() {
    }

    public static void main(String[] args) throws IOException {
        regexp = args[0];
        String filespath = args[1];
        String newFileName = args[2];
        File F = new File(filespath);
        File[] fList = F.listFiles();
        OutputStreamFile file = new OutputStreamFile(newFileName);

        for(int i = 0; i < fList.length; ++i) {
            if (fList[i].isFile()) {
                InputStreamFile f = new InputStreamFile(fList[i].getAbsolutePath());

                for(String buf = f.readLine(); buf != null; buf = f.readLine()) {
                    if (checkWithRegExp(buf)) {
                        file.writeLine(buf);
                    }
                }

                f.close();
            }
        }

        file.close();
    }

    public static boolean checkWithRegExp(String userNameString) {
        Pattern p = Pattern.compile(regexp);
        Matcher m = p.matcher(userNameString);
        return m.find();
    }
}
