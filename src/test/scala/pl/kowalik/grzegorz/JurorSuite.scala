package pl.kowalik.grzegorz

import org.junit.Test
import org.scalatest.Assertions
import pl.kowalik.grzegorz.file.FileDataReader

class JurorSuite extends Assertions{

	@Test def testKnownCostResultForHad12(): Unit ={
		val data = FileDataReader.readFile("/had12.dat")

		val juror = new Juror(data)

		assert(juror.scoreFunction(Array(2,9,10,1,11,4,5,6,7,0,3,8)) == 1652)
	}

	@Test def testKnownCostResultForHad14(): Unit ={
		val data = FileDataReader.readFile("/had14.dat")

		val juror = new Juror(data)

		assert(juror.scoreFunction(Array(7,12,9,4,11,10,1,13,2,5,6,0,8,3)) == 2724)
	}

	@Test def testKnownCostResultForHad16(): Unit ={
		val data = FileDataReader.readFile("/had16.dat")

		val juror = new Juror(data)

		assert(juror.scoreFunction(Array(8,3,15,0,6,7,5,13,14,10,11,9,4,2,1,12)) == 3720)
	}

	@Test def testKnownCostResultForHad18(): Unit ={
		val data = FileDataReader.readFile("/had18.dat")

		val juror = new Juror(data)

		assert(juror.scoreFunction(Array(7,14,15,5,6,17,13,10,0,9,11,4,2,12,1,16,8,3)) == 5358)
	}

	@Test def testKnownCostResultForHad120(): Unit ={
		val data = FileDataReader.readFile("/had20.dat")

		val juror = new Juror(data)

		assert(juror.scoreFunction(Array(7,14,15,13,18,5,6,16,0,11,9,10,4,19,1,2,3,8,17,12)) == 6922)
	}
}
