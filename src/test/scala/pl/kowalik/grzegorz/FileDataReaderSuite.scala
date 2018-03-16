package pl.kowalik.grzegorz

import org.scalatest.Assertions
import org.junit.Test
import pl.kowalik.grzegorz.file.FileDataReader


class FileDataReaderSuite extends Assertions {

	@Test def testProperFileParsing() {
		val file = FileDataReader.readFile("/testFile.txt")

		assert(file.n == 3)
		assert(file.flow(0).deep == Array(0, 1, 2).deep)
		assert(file.distances(0).deep == Array(0, 9, 7).deep)
	}
}
