package amata1219.redpower.detector

import org.bukkit.Chunk
import org.bukkit.event.Listener
import org.bukkit.event.block.BlockRedstoneEvent

import scala.collection.mutable

object RedpowerListener extends Listener {

  val count: mutable.Map[Chunk, Int] = new mutable.HashMap().withDefault(_ => 0)

  implicit class XChunk(val chunk: Chunk) {
    override def hashCode(): Int = ((chunk.getX >> 4) << 16) ^ (chunk.getZ >> 4)
  }

  def on(event: BlockRedstoneEvent): Unit = count(event.getBlock.getChunk) += 1

}
