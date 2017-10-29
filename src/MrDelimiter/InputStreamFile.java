package MrDelimiter;

import java.io.*;

public class InputStreamFile {
    private InputStream inputstream;
    private BufferedReader in;
    private String path;

    public InputStreamFile(String path) throws IOException {
        this.path = path;
        this.inputstream = new FileInputStream(path);
        this.in = new BufferedReader(new InputStreamReader(this.inputstream));
    }

    public void close() throws IOException {
        this.inputstream.close();
        this.in.close();
    }

    public String readLine() throws IOException {
        return this.in.readLine();
    }
}
