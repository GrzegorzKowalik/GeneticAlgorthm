package pl.kowalik.grzegorz

import org.junit.Test
import org.scalatest.Assertions
import pl.kowalik.grzegorz.Genetic.Chap
import pl.kowalik.grzegorz.mutators.ReplaceMutator

class MutatorSuite extends Assertions{

	@Test def testReplaceMutatorSwapMiddle(): Unit ={
		val mutator = new ReplaceMutator(1)
		val chap : Chap = (Array(1,2,3,4,5), 0)

		val res = mutator.mutateSingle(chap, 2)

		assert(res._1 sameElements Array(1, 2, 4, 3, 5))
	}

	@Test def testReplaceMutatorSwapLast(): Unit ={
		val mutator = new ReplaceMutator(1)
		val chap : Chap = (Array(1,2,3,4,5), 0)

		val res = mutator.mutateSingle(chap, 4)

		assert(res._1 sameElements Array(5, 2, 3, 4, 1))
	}
}
