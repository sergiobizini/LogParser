package MrPartition;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

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
