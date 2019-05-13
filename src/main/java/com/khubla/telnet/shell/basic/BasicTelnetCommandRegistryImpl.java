/*
 * Copyright (C) khubla.com - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by Tom Everett <tom@khubla.com>, 2018
 */
package com.khubla.telnet.shell.basic;

import com.khubla.telnet.shell.basic.command.CCommand;
import com.khubla.telnet.shell.basic.command.QuitCommand;
import com.khubla.telnet.shell.basic.command.SendCommand;
import com.khubla.telnet.shell.command.AbstractTelnetCommandRegistry;

public class BasicTelnetCommandRegistryImpl extends AbstractTelnetCommandRegistry {
   public BasicTelnetCommandRegistryImpl() {
      addCommand(new CCommand());
      addCommand(new SendCommand());
      addCommand(new QuitCommand());
   }
}
