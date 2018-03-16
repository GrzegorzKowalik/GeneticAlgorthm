package pl.kowalik.grzegorz.selectors
import pl.kowalik.grzegorz.Genetic.{Chap, Population}

class RandomSelector extends AbstractSelector {

	override def select(list: Population): List[(Chap, Chap)] = {
		val (l1, l2) = list.splitAt(list.size / 2)
		l1.zip(l2)
	}
}
