package website.surf0335.backend.Service;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Enumeration;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class GetMacAddress {




    public String getMac(String ip) {

        Pattern macpt = null;
        // Find OS and set command according to OS
        String OS = System.getProperty("os.name").toLowerCase();

        String[] cmd;
        if (OS.contains("win")) {
            // Windows
            macpt = Pattern
                    .compile("[0-9a-f]+-[0-9a-f]+-[0-9a-f]+-[0-9a-f]+-[0-9a-f]+-[0-9a-f]+");
            String[] a = { "arp", "-a", ip };
            cmd = a;
        } else {
            // Mac OS X, Linux
            macpt = Pattern
                    .compile("[0-9a-f]+:[0-9a-f]+:[0-9a-f]+:[0-9a-f]+:[0-9a-f]+:[0-9a-f]+");
            String[] a = { "arp", ip };
            cmd = a;
        }

        try {
            // Run command
            Process p = Runtime.getRuntime().exec(cmd);
            p.waitFor();
            // read output with BufferedReader
            BufferedReader reader = new BufferedReader(new InputStreamReader(
                    p.getInputStream()));
            String line = reader.readLine();

            // Loop trough lines
            while (line != null) {
                Matcher m = macpt.matcher(line);

                // when Matcher finds a Line then return it as result
                if (m.find()) {
                    System.out.println("Found");
                    System.out.println("MAC: " + m.group(0));
                    return m.group(0);
                }

                line = reader.readLine();
            }

        } catch (IOException e1) {
            e1.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Return empty string if no MAC is found
        return "";
    }



}
