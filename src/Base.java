import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Base {
    void createNextApp() {
        Process process = null;
        try {
            Runtime.getRuntime().exec("powershell.exe /c npx create-next-app test-app");
            helper(process);

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    void makeDir(String dirName) {
        try {
            List <String> commands = new ArrayList<>();
            commands.add("powershell.exe");
            commands.add("ls");

            System.out.println("Commands :- " + commands);

            ProcessBuilder pb = new ProcessBuilder(commands);
            pb.directory(new File(dirName));
            Process process = pb.start();

            BufferedReader stdInp = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String s = null;

            while((s = stdInp.readLine()) != null) {
                System.out.println(s);
            }

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private void helper(Process process) {
        try {
            StringBuilder output = new StringBuilder();
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;

            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
            int exitVal = process.waitFor();
            if (exitVal == 0) {
                System.exit(0);
            } else {
                System.out.println("Something went wrong");
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

}
