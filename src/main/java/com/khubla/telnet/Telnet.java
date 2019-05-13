package com.khubla.telnet;

import java.io.OutputStream;
import static java.lang.System.out;
import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.logging.Logger;
import org.apache.commons.net.telnet.TelnetClient;

public final class Telnet {

    private final static Logger LOG = Logger.getLogger(Telnet.class.getName());
    private TelnetClient telnetClient = new TelnetClient();
    static private boolean done = false;

    public Telnet(String hostname, int port, String forward) throws SocketException, IOException, InterruptedException {
        InetAddress host = InetAddress.getByName(hostname);
        telnetClient.connect(host, port);

        final InputStream inputStream = telnetClient.getInputStream();
        final OutputStream outputStream = telnetClient.getOutputStream();

        Thread print = new Thread() {

            @Override
            public void run() {
                try {
                    char ch = (char) inputStream.read();
                    while (255 > ch && ch >= 0 && !done) {
                        out.print(ch);
                        ch = (char) inputStream.read();
                    }
                } catch (IOException ex) {
                    out.println("cannot read inputStream:\t" + ex);
                }
            }
        };
        print.start();

        outputStream.write(forward.getBytes());
        outputStream.write(10);
        outputStream.flush();

        done = true;

        print.join();
    }

    public static void main(String[] args) throws SocketException, IOException, InterruptedException {
        // new Telnet("192.168.150.59", 4788, "HELP");
    }
}