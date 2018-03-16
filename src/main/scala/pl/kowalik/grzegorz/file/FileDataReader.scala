package pl.kowalik.grzegorz.file

import scala.collection.mutable.ArrayBuffer
import scala.io.Source

object FileDataReader {
	def readFile(name:String): FileData ={
		val lines = Source.fromURL(getClass.getResource(name)).getLines()

		val n = lines.next().trim().toInt

		lines.next()

		val flowsBuffer = new ArrayBuffer[Array[Int]]
		for( _ <- 0 until n){
			flowsBuffer += parseLine(lines.next())
		}

		lines.next()

		val distancesBuffer = new ArrayBuffer[Array[Int]]
		for( _ <- 0 until n){
			distancesBuffer += parseLine(lines.next())
		}

		FileData(n, flowsBuffer.toArray, distancesBuffer.toArray)
	}

	private def parseLine(line:String):Array[Int]={
		line.trim().replaceAll(" +", " ").split(" ").map(s => s.toInt)
	}
}
