package amata1219.redpower.detector

import org.bukkit.Chunk
import org.bukkit.event.HandlerList
import org.bukkit.plugin.java.JavaPlugin
import org.bukkit.scheduler.{BukkitRunnable, BukkitTask}

import scala.collection.mutable

class RedpowerDetector extends JavaPlugin() {

  RedpowerDetector.instance = this

  override def onEnable(): Unit = getServer.getPluginManager.registerEvents(RedpowerListener, this)

  override def onDisable(): Unit = HandlerList.unregisterAll(this)

}

object RedpowerDetector {

  var instance: RedpowerDetector = _
  var DETECTOR_TASK: BukkitTask = _

  def startDetectingRedpower(): Unit = {
    DETECTOR_TASK = new BukkitRunnable() {
      override def run(): Unit = {
        val result: mutable.Map[Chunk, Int] = RedpowerListener.count
        val chunksOverRedpowered: Iterable[Chunk] = result.filter(_._2 > 1500).keys
        if (chunksOverRedpowered.nonEmpty) println("[List of chunks that over redpowered]")
        chunksOverRedpowered.foreach(chunk => {
          println(s"${result(chunk)} @ [${chunk.getX}, ${chunk.getZ}]")
        })
        result.clear()
      }
    }.runTaskTimer(instance, 6000, 6000)
  }

  def stopDetectionRedpower(): Unit = {
    DETECTOR_TASK.cancel()
    DETECTOR_TASK = null
  }

}