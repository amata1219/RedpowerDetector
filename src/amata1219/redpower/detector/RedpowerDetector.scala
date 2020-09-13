package amata1219.redpower.detector

import amata1219.redpower.detector.RedpowerDetector.stopDetectionRedpower
import org.bukkit.Chunk
import org.bukkit.event.HandlerList
import org.bukkit.plugin.java.JavaPlugin
import org.bukkit.scheduler.{BukkitRunnable, BukkitTask}

import scala.collection.mutable

class RedpowerDetector extends JavaPlugin() {

  RedpowerDetector.instance = this

  override def onEnable(): Unit = {
    getCommand("rpdetect").setExecutor(DetectorControlCommand)
    getServer.getPluginManager.registerEvents(RedpowerListener, this)
  }

  override def onDisable(): Unit = {
    HandlerList.unregisterAll(this)
    stopDetectionRedpower()
  }

}

object RedpowerDetector {

  var instance: RedpowerDetector = _
  var DETECTOR_TASK: BukkitTask = _

  def startDetectingRedpower(): Unit = if (!isDetectorTaskRunning) {
    DETECTOR_TASK = new BukkitRunnable() {
      override def run(): Unit = {
        val result: mutable.Map[Chunk, Int] = RedpowerListener.count
        val chunksOverRedpowered: Iterable[Chunk] = result.filter(_._2 > 300).keys
        if (chunksOverRedpowered.nonEmpty) println("[List of chunks that over redpowered]")
        chunksOverRedpowered.foreach(chunk => println(s"${result(chunk)} @ [${chunk.getX}, ${chunk.getZ}]"))
        result.clear()
      }
    }.runTaskTimer(instance, 1200, 1200)
  }

  def stopDetectionRedpower(): Unit = if (isDetectorTaskRunning) {
    DETECTOR_TASK.cancel()
    DETECTOR_TASK = null
  }

  def isDetectorTaskRunning: Boolean = DETECTOR_TASK != null

}