package pl.kowalik.grzegorz

import org.junit.Test
import pl.kowalik.grzegorz.crossers.InHalfCrosser

class InHalfCrosserSuite {

	@Test def testInHalfCrosserFix(): Unit ={
		val arr1 = Array(2,3,4,3,0,5)

		val crosser = new InHalfCrosser(100, null)
		val res = crosser.fix(arr1)

		assert(res sameElements Array(2,1,4,3,0,5))
	}
}
