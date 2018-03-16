package pl.kowalik.grzegorz.mutators

import pl.kowalik.grzegorz.Genetic.Chap

class DummyMutator(override val probability: Float) extends AbstractMutator(probability) {

	override def mutateSingle(chap: Chap, position: Int): Chap = {
		chap
	}
}
