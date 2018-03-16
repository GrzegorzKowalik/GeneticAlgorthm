package pl.kowalik.grzegorz.mutators

import pl.kowalik.grzegorz.Genetic.{Chap, Population}

import scala.util.Random

abstract class AbstractMutator(val probability: Float) {
	protected val rand: Random = Random

	def mutateAll(list: Population) : Population = {
		list.map(mutateChap)
	}

	def mutateChap(chap: Chap): Chap = {
		var c = chap
		for (i <- c._1.indices){
			if(rand.nextFloat() < probability){
				c = mutateSingle(c, i)
			}
		}
		c
	}

	def mutateSingle(chap: Chap, position: Int): Chap
}
