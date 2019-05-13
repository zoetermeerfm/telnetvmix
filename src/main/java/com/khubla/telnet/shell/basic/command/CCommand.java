/*
 * Copyright (C) khubla.com - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by Tom Everett <tom@khubla.com>, 2018
 */
package com.khubla.telnet.shell.basic.command;

import com.khubla.telnet.TelnetException;
import com.khubla.telnet.nvt.NVT;
import com.khubla.telnet.shell.command.AbstractCommand;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Properties;

public class CCommand extends AbstractCommand {
   private final static String names[] = { "C" };
   private final OkHttpClient client = new OkHttpClient();
   private Properties prop;

   public CCommand() {
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
      String url = prop.getProperty(line.replace(" ", "."));
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
      }
      /*
       * don't continue
       */
      return false;
   }

   @Override
   public String[] getNames() {
      return names;
   }
}
