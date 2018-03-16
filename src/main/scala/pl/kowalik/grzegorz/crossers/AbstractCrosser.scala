package pl.kowalik.grzegorz.crossers

import pl.kowalik.grzegorz.Genetic.{Chap, Population}

import scala.util.Random

abstract class AbstractCrosser(val probability: Float) {
	protected val rand: Random = Random

	def crossPopulation(list: List[(Chap, Chap)]) : Population = {
		list.flatMap(checkProbAndCross)
	}

	def checkProbAndCross(parents: (Chap, Chap)): List[Chap] = {
		if(rand.nextFloat() < probability){
			crossPair(parents)
//			  .map(chap => (chap._1, App.scoreFunction(chap._1)))
//			val family = (parents._1 :: parents._2 :: kids).sortBy(_._2)
//			family.splitAt(2)._1
		}
		else{
			List(parents._1, parents._2)
		}
	}

	def crossPair(pair: (Chap, Chap)): List[Chap]
}
