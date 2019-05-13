/*
 * Copyright (C) khubla.com - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by Tom Everett <tom@khubla.com>, 2018
 */
package com.khubla.telnet.shell.basic.command;

import com.khubla.telnet.Telnet;
import com.khubla.telnet.TelnetException;
import com.khubla.telnet.nvt.NVT;
import com.khubla.telnet.shell.command.AbstractCommand;

import java.io.*;
import java.util.HashMap;
import java.util.Properties;

public class SendCommand extends AbstractCommand {

   public static void main(String[] args) throws Exception {
      SendCommand sendCommand = new SendCommand();
      sendCommand.execute(null, null, null);
   }

   private final static String names[] = { "FORWARD" };
   private Properties prop;

   public SendCommand() {
      prop = new Properties();
      try {
         File external = new File("config.properties");
         if (external.exists()) {
            prop.load(new DataInputStream(new FileInputStream(external)));
         }
      } catch (Exception e) {

      }
   }

   @Override
   public boolean execute(NVT nvt, String line, HashMap<String, Object> sessionParameters) throws TelnetException {
      /*
      String cmd = prop.getProperty(line.replace("FORWARD ", ""));
      if (url != null) {
         if (!url.equals("")) {
            System.out.println(line + ": " + url);

            Request request = new Request.Builder()
                    .url(url)
                    .build();

            try {
               Response response = client.newCall(request).execute();
            } catch (Exception e) {

            }
         }
      } else {
         System.out.println("Unmapped: " + line);
      } */
      /*
       * don't continue
       */

      try {
         String cmd = line.substring(7);
         new Telnet(prop.getProperty("host"), Integer.parseInt(prop.getProperty("port")), cmd);

      } catch (Exception e) {

      }

      return true;
   }

   @Override
   public String[] getNames() {
      return names;
   }
}
