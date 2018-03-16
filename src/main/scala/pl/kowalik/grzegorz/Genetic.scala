package pl.kowalik.grzegorz

import java.io.FileWriter

import pl.kowalik.grzegorz.Genetic.{Chap, Population}
import pl.kowalik.grzegorz.crossers.AbstractCrosser
import pl.kowalik.grzegorz.file.FileData
import pl.kowalik.grzegorz.mutators.AbstractMutator
import pl.kowalik.grzegorz.selectors.AbstractSelector

import scala.util.Random


object Genetic{
	type Chap = (Array[Int], Int)
	type Population = List[Chap]
}
class Genetic(data: FileData,
			  selector: AbstractSelector,
			  mutator: AbstractMutator,
			  crosser: AbstractCrosser,
			  juror: Juror,
			  popSize: Int) {

	var startTime = 0L



	def start(stopCondition:String, value: Int):Chap = {
		var best: Chap = (null, Int.MaxValue)
		var pop: Population = randomizePopulation(popSize, data.n)

//		val writer = new FileWriter("result.csv")
//		writer.write("gen;score\n")

		startTime = System.currentTimeMillis()
		while (!checkStopCondition(stopCondition, value)){
			registerIteration(true)

			pop = crosser.crossPopulation(selector.select(pop))
			pop = mutator.mutateAll(pop)
			pop = juror.applyScore(pop)
			best = juror.selectBest(pop, best)

//			logPopulation(writer, pop)
		}

//		writer.close()
		best
	}


	def checkStopCondition(condition: String, value: Int):Boolean = {
		if (condition == "time"){
			checkTimeStopCondition(value)
		}
		else if (condition == "gens"){
			checkGensStopCondition(value)
		}
		else
			throw new Exception("Unsupported stop condition")
	}

	def checkTimeStopCondition(toElapse: Int) : Boolean = {
		startTime+toElapse*1000 <= System.currentTimeMillis()
	}

	def checkGensStopCondition(targetGens: Int) : Boolean = {
		gens == targetGens
	}




	def randomizePopulation(popSize: Int, chapSize: Int): Population ={
		var res: Population = List()
		val basicChap = List.range(0, chapSize)

		for( _ <- 0 until popSize){
			val ch : Array[Int] = Random.shuffle(basicChap).toArray
			val score = juror.scoreFunction(ch)
			res = (ch, score) :: res
		}

		res
	}






	var gens = 0
	var gensSinceLastLog = 0
	var timeSinceLastLog: Long = System.currentTimeMillis()
	def registerIteration(print: Boolean): Unit ={
		gens += 1

		if(print){
			if (System.currentTimeMillis() - timeSinceLastLog > 1000) {
				timeSinceLastLog = System.currentTimeMillis()
				gensSinceLastLog = gens - gensSinceLastLog
				println(s"Generation: $gens\tAverage generation time: " +
				  (System.currentTimeMillis() - startTime)/gensSinceLastLog.toFloat + "ms")
			}
		}
	}

	def printChap(x: (Array[Int], Int)): Unit = {
		println(s"${x._1.deep} --- ${x._2}")
	}

	def printPopulation(pop: Population): Unit = {
		for	(x <- pop){
			printChap(x)
		}
	}

	def logPopulation(writer: FileWriter, pop: Population): Unit = {
		for(chap <- pop){
			writer.write(s"$gens;${chap._2}\n")
		}
	}
}
