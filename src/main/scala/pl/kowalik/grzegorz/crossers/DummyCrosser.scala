package pl.kowalik.grzegorz.crossers
import pl.kowalik.grzegorz.Genetic.Chap

class DummyCrosser(override val probability: Float) extends AbstractCrosser(probability) {
	override def crossPair(pair: (Chap, Chap)): List[Chap] = {
		List(pair._1, pair._2)
	}
}
