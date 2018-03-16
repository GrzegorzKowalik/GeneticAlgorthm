package pl.kowalik.grzegorz.mutators

import pl.kowalik.grzegorz.Genetic.Chap

class ReplaceMutator(override val probability: Float) extends AbstractMutator(probability) {

	override def mutateSingle(chap: Chap, position: Int): Chap = {
		val arr = chap._1
		val tmp = arr(position)

		arr(position) = arr((position + 1) % arr.length)
		arr((position + 1) % arr.length) = tmp

		(arr, -1)
	}
}
