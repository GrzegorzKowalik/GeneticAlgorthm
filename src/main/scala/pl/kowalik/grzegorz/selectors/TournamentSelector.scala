package pl.kowalik.grzegorz.selectors
import pl.kowalik.grzegorz.Genetic.{Chap, Population}

import scala.util.Random

class TournamentSelector(tour: Int) extends AbstractSelector {
	override def select(list: Population): List[(Chap, Chap)] = {
		var res: List[(Chap, Chap)] = List()

		for (_ <- 0 until list.length/2){
			val chap1 = runTournament(tour, list)
			val chap2 = runTournament(tour, list)

			res = (chap1, chap2) :: res
		}

		res
	}


	def runTournament(tour: Int, list: Population): Chap ={
		Random.shuffle(list).splitAt(tour)._1.minBy(_._2)
	}
}
