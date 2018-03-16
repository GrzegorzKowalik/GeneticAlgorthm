package pl.kowalik.grzegorz.selectors
import pl.kowalik.grzegorz.Genetic.{Chap, Population}

import scala.util.Random

class RouletteSelector extends AbstractSelector {

	override def select(list: Population): List[(Chap, Chap)] = {
		var sumOfFitness = 0

		for (ch <- list){
			sumOfFitness += ch._2
		}

		var sumOfProbabilities = 0.0
		var probabilities: List[Double] = List()

		for (ch <- list){
			val prob = sumOfProbabilities + ch._2 / sumOfFitness
			sumOfProbabilities += prob
			probabilities = prob :: probabilities
		}

		var res: List[(Chap, Chap)] = List()

		for (_ <- 0 until list.length / 2){
			res = (selectOne(list, probabilities), selectOne(list, probabilities)) :: res
		}

		res
	}

	def selectOne(list: Population, probabilities: List[Double]): Chap = {
		val rand = Random.nextFloat()

		for(i <- list.indices){
			if(rand > probabilities(i) && rand < probabilities(i+1)){
				return list(i)
			}
		}

		list.last
	}
}
