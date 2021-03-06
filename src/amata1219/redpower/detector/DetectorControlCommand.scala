package amata1219.redpower.detector

import org.bukkit.ChatColor
import org.bukkit.command.{Command, CommandExecutor, CommandSender}

object DetectorControlCommand extends CommandExecutor() {

  override def onCommand(sender: CommandSender, cmd: Command, label: String, args: Array[String]): Boolean = {
    if (args.length == 0) {
      sender.sendMessage(s"${ChatColor.AQUA}/rpdetect start: 赤石信号の発生回数の記録を開始します。")
      sender.sendMessage(s"${ChatColor.AQUA}/rpdetect stop: 赤石信号の発生回数の記録を終了します。")
      return true
    }

    args(0) match {
      case "start" =>
        if (RedpowerDetector.isDetectorTaskRunning) {
          sender.sendMessage(s"${ChatColor.RED}既に赤石信号の検知は開始されています！")
          return true
        }
        RedpowerDetector.startDetectingRedpower()
        sender.sendMessage(s"${ChatColor.AQUA}赤石信号の検知を開始しました！")
      case "stop" =>
        if (!RedpowerDetector.isDetectorTaskRunning) {
          sender.sendMessage(s"${ChatColor.RED}既に赤石信号の検知は終了されています！")
          return true
        }
        RedpowerDetector.stopDetectionRedpower()
        sender.sendMessage(s"${ChatColor.AQUA}赤石信号の検知を終了しました！")
    }
    true
  }

}
