package pl.kowalik.grzegorz

import java.io.FileWriter

import pl.kowalik.grzegorz.crossers.{AbstractCrosser, DummyCrosser, InHalfCrosser}
import pl.kowalik.grzegorz.file.{FileData, FileDataReader}
import pl.kowalik.grzegorz.mutators.{AbstractMutator, DummyMutator, ReplaceMutator}
import pl.kowalik.grzegorz.selectors.{AbstractSelector, RandomSelector, TournamentSelector}


object App {
//	val writer = new FileWriter("result.csv")


	def main(args: Array[String]) {
//		writer.write("run;gens;crossProb;mutateProb;score;pop\n")

		//for (i <- 10 to 100 by 10){
			run(1, 100, 0.5f, 0.1f, 350)
	//	}

//		writer.close()
	}

	def run(n:Int, gens: Int, crossProb: Float, mutateProb: Float, pop: Int): Unit ={
		println(s"-------======= Gens: $gens, cross: $crossProb, mutate: $mutateProb")

		val data: FileData = FileDataReader.readFile("/had20.dat")
		val juror = new Juror(data)
		val selector = new TournamentSelector(5)
		val crosser = new InHalfCrosser(crossProb, juror)
		val mutator = new ReplaceMutator(mutateProb)


		for(i <- 1 to n){
			println(s"---===Run $i out of $n===---")

			val genetic = new Genetic(data, selector, mutator, crosser, juror, pop)

			val result = genetic.start("gens", gens)

//			writer.write(s"$i;" +
//						  s"$gens;" +
//						  s"$crossProb;" +
//						  s"$mutateProb;" +
//						  s"${result._2};"+
//			  			  s"$pop\n")
		}
	}
}
