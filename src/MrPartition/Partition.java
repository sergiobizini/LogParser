package MrPartition;

import java.io.IOException;

public class Partition {
    public Partition() {
    }

    public static void main(String[] args) throws IOException {
        String filepath = args[0];
        String constPartOfName = args[1];
        int partitionSize = Integer.parseInt(args[2]);
        InputStreamFile f = new InputStreamFile(filepath);
        String buf = f.readLine();
        int index = 0;
        OutputStreamFile file = new OutputStreamFile(constPartOfName + index + ".txt");

        for(int timer = 0; buf != null; buf = f.readLine()) {
            file.writeLine(buf);
            if (timer > 10000) {
                if (file.fileSize() > (long)(partitionSize * 1024 * 1024)) {
                    file.close();
                    ++index;
                    file = new OutputStreamFile(constPartOfName + index + ".txt");
                }

                timer = 0;
            }

            ++timer;
        }

        file.close();
        f.close();
    }
}
