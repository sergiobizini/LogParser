package MrPartition;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;

public class OutputStreamFile {
    private BufferedWriter bufWriter;
    private FileWriter fWriter;
    private OutputStream outputStream;
    private File outFile;
    private String path;

    public OutputStreamFile(String path) throws IOException {
        this.path = path;
        this.outFile = new File(path);
        this.fWriter = new FileWriter(this.outFile);
        this.bufWriter = new BufferedWriter(this.fWriter);
    }

    public void close() throws IOException {
        this.bufWriter.close();
        this.fWriter.close();
    }

    public long fileSize() {
        return this.outFile.length();
    }

    public void writeLine(String st) throws IOException {
        st = st + System.getProperty("line.separator");
        this.bufWriter.write(st);
    }
}
