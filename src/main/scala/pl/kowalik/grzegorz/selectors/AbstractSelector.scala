package pl.kowalik.grzegorz.selectors

import pl.kowalik.grzegorz.Genetic.{Chap, Population}

abstract class AbstractSelector {
	def select(list : Population): List[(Chap, Chap)]
}
