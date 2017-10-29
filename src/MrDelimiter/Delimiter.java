package MrDelimiter;

import MrFind.InputStreamFile;
import MrFind.OutputStreamFile;
import java.io.File;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Delimiter {
    public Delimiter() {
    }

    public static void main(String[] args) throws IOException {
        String delimiter = args[0];
        String filespath = args[1];
        String newFileName = args[2];
        File F = new File(filespath);
        File[] fList = F.listFiles();
        OutputStreamFile file = new OutputStreamFile(newFileName);

        for(int i = 0; i < fList.length; ++i) {
            if (fList[i].isFile()) {
                InputStreamFile f = new InputStreamFile(fList[i].getAbsolutePath());

                for(String buf = f.readLine(); buf != null; buf = f.readLine()) {
                    Pattern patternDate = Pattern.compile("[0-9]{2}\\.[0-9]{2}\\.[0-9]{4}");
                    Pattern patternTime = Pattern.compile("[0-9]{2}:[0-9]{2}:[0-9]{2}\\.[0-9]{3}");
                    Pattern patternType = Pattern.compile("\\s([A-Z]+)\\s*:(.*)");
                    Pattern patternSocket = Pattern.compile(":\\s*([0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}:[0-9]{1,5})(.*)");
                    Matcher mDate = patternDate.matcher(buf);
                    Matcher mTime = patternTime.matcher(buf);
                    Matcher mType = patternType.matcher(buf);
                    Matcher mSocket = patternSocket.matcher(buf);
                    StringBuilder temp = new StringBuilder();
                    if (mTime.find() && mDate.find() && mType.find()) {
                        temp.append(mDate.group(0) + delimiter + mTime.group(0) + delimiter + mType.group(1) + delimiter);
                        Pattern patternMessageParser;
                        Matcher mMessageParser;
                        if (mSocket.find()) {
                            temp.append(mSocket.group(1) + delimiter);
                            if (!mSocket.group(2).isEmpty()) {
                                patternMessageParser = Pattern.compile("([><]\\s*[A-Za-z]+);*\\s*\\[(.*)\\]");
                                mMessageParser = patternMessageParser.matcher(buf);
                                if (mMessageParser.find()) {
                                    temp.append(mMessageParser.group(1) + delimiter);
                                    String[] tempMessage = mMessageParser.group(2).toString().split(", ");
                                    for(String s:tempMessage) {
                                        temp.append(s + delimiter);
                                    }
                                } else {
                                    temp.append(mSocket.group(2).replace(";", delimiter) + delimiter);
                                }
                            }
                        } else {
                            temp.append(delimiter);
                            patternMessageParser = Pattern.compile("\\s[A-Z]+\\s*:(.*)");
                            mMessageParser = patternMessageParser.matcher(buf);
                            if (mMessageParser.find()) {
                                temp.append(mMessageParser.group(1) + delimiter);
                            }
                        }

                        file.writeLine(temp.toString());
                    }
                }

                f.close();
            }
        }

        file.close();
    }
}
