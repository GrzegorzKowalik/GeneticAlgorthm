package pl.kowalik.grzegorz.crossers

import pl.kowalik.grzegorz.Genetic.Chap
import pl.kowalik.grzegorz.Juror

class InHalfCrosser(override val probability: Float, juror: Juror) extends AbstractCrosser(probability) {


	override def crossPair(parents: (Chap, Chap)): List[Chap] = {
		val l1 = parents._1._1
		val l2 = parents._2._1
		val len = l1.length
		var res1: Array[Int] = l1.clone()
		var res2: Array[Int] = l2.clone()

		for (i <- len/2 until len){
			res1(i) = l2(i)
		}

		for (i <- len/2 until len){
			res2(i) = l1(i)
		}

		res1 = fix(res1)
		res2 = fix(res2)

		val kids = ((res1, juror.scoreFunction(res1)), (res2, juror.scoreFunction(res2)))

		selectBest(parents, kids)
	}


	def selectBest(parents: (Chap, Chap), kids: (Chap, Chap)): List[Chap] = {
		val family = parents._1 :: parents._2 :: kids._1 :: kids._2 :: Nil

		family.sortBy(_._2).splitAt(2)._1
	}


	def fix(array: Array[Int]): Array[Int] = {
		val presence = Array.fill(array.length){0}

		for (x <- array){
			presence(x)+=1
		}

		for (i <- presence.indices){
			if(presence(i) == 2){
				for (j <- presence.indices){
					if(presence(j) == 0 && presence(i) == 2){
						val index = array.indexWhere( _ == i)
						array(index) = j
						presence(j) += 1
						presence(i) -= 1
					}
				}
			}
		}

		array
	}
}
