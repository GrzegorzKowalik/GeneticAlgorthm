package pl.kowalik.grzegorz

import pl.kowalik.grzegorz.Genetic.{Chap, Population}
import pl.kowalik.grzegorz.file.FileData

class Juror(data: FileData) {

	def scoreFunction(obj : Array[Int]) : Int={
		var res = 0
		for(i <- obj.indices){
			for(j <- obj.indices){
				res += data.flow(i)(j) * data.distances(obj(i))(obj(j))
			}
		}
		res
	}

	def applyScore(pop: Population) : Population = {
		pop.map(chap => (chap._1, scoreFunction(chap._1)))
	}

	def selectBest(pop: Population, currentBest: Chap): Chap = {
		val bc = pop.reduce((c1, c2) => if(c1._2 < c2._2) c1 else c2)
		if(bc._2 < currentBest._2){
			printNewBest(bc)
			return bc
		}
		currentBest
	}

	def printNewBest(chap: Chap): Unit ={
		println(s"\tNew best result found!\t${chap._1.deep} - score: ${chap._2}")
	}
}
