package io.vamp.core.cli

import io.vamp.core.cli.commandline.Parameters
import io.vamp.core.cli.commands.{PerformCommand, HelpCommand}

trait VampCli extends Parameters {

  def main(args: Array[String]) {
    if (args.length == 0) {
      showHelp(HelpCommand())
      sys.exit(0)
    }
    implicit val options = readParameters(args.tail)

    if (options.contains(help)) showHelp(string2Command(args.head))

    PerformCommand.doCommand(string2Command(args.head))
    sys.exit(0)
  }


}