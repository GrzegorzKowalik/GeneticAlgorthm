package pl.kowalik.grzegorz

import org.junit.Test
import pl.kowalik.grzegorz.Genetic.Chap
import pl.kowalik.grzegorz.selectors.RandomSelector

class BasicSelectorSuite {

	@Test def testBasicSelectorSelection(): Unit ={
		val selector = new RandomSelector
		val list: List[Chap] = List(
			(Array(1,2,3,4), 0),
			(Array(3,4,5,6), 0),
			(Array(10,9,8,7), 0),
			(Array(11,21,31,41), 0),
			(Array(10,20,30,40), 0)
		)

		val l = selector.select(list)

		assert(l.head._1._1 sameElements Array(1, 2, 3, 4))
		assert(l.head._2._1 sameElements Array(10,9,8,7))
		assert(l(1)._1._1 sameElements Array(3,4,5,6))
		assert(l(1)._2._1 sameElements Array(11,21,31,41))
	}
}
