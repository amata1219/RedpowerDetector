package amata1219.redpower.detector

import org.bukkit.Chunk
import org.bukkit.event.{EventHandler, Listener}
import org.bukkit.event.block.BlockRedstoneEvent

import scala.collection.mutable

object RedpowerListener extends Listener {

  val count: mutable.Map[Chunk, Int] = new mutable.HashMap().withDefault(_ => 0)

  implicit class XChunk(val chunk: Chunk) {
    override def hashCode(): Int = (chunk.getX << 16) ^ chunk.getZ
  }

  @EventHandler
  def on(event: BlockRedstoneEvent): Unit = if (RedpowerDetector.isDetectorTaskRunning) {
    count(event.getBlock.getChunk) += 1
  }

}
