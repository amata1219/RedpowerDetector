package amata1219.redpower.detector

import org.bukkit.ChatColor
import org.bukkit.command.{Command, CommandExecutor, CommandSender}

object DetectorControlCommand extends CommandExecutor() {

  override def onCommand(sender: CommandSender, cmd: Command, label: String, args: Array[String]): Boolean = {
    if (args.length == 0) {
      sender.sendMessage(
        s"""
          |${ChatColor.RED}/rpdetect start: 赤石信号の検知を開始します。
          |${ChatColor.RED}/rpdetect stop: 赤石信号の検知を終了します。
          |""".stripMargin)
      return true
    }

    val plugin: RedpowerDetector = RedpowerDetector.instance

    args[0] match {
      case "start" =>

      case _ => println()
    }
    true
  }

}
