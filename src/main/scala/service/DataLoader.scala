
package service

import model.{Driver, RaceResult, Stops}
import org.apache.commons.csv.{CSVFormat, CSVParser}
import java.nio.file.{Files, Paths}
import scala.jdk.CollectionConverters._

object DataLoader {
  def loadDrivers(path: String): List[Driver] = {
    val reader = Files.newBufferedReader(Paths.get(path))
    val csv = CSVParser.parse(reader, CSVFormat.DEFAULT.withFirstRecordAsHeader())
    val a = csv.getRecords.asScala.toList.map { rec =>
      Driver(
        rec.get("driverId").toInt,
        rec.get("forename"),
        rec.get("surname")
      )
    }
    println("\nLista de Drivers:")
    println("================\n")
    //println(a)
    println(a.take(5))
    return a
  }

  def loadResults(path: String): List[RaceResult] = {
    val reader = Files.newBufferedReader(Paths.get(path))
    val csv = CSVParser.parse(reader, CSVFormat.DEFAULT.withFirstRecordAsHeader())
    val b = csv.getRecords.asScala.toList.map { rec =>
      RaceResult(
        rec.get("resultId").toInt,
        rec.get("raceId").toInt,
        rec.get("driverId").toInt,
        // Puede que la posición sea "\N", por eso Option
        if (rec.get("position") == "\\N") None else Some(rec.get("position").toInt)
      )
    }
    println("\nLista de Resultados por driver:")
    println("==============================\n")
    println(b.take(5))
    println("\nLista de Resultados de los 5 primeros drivers:\n")
    var i: Int = 0
    for (i <- 0 to 4) {
      println(b(i))
    }
    return b
  }

  def loadStops(path: String): List[Stops] = {
    val reader = Files.newBufferedReader(Paths.get(path))
    val csv = CSVParser.parse(reader, CSVFormat.DEFAULT.withFirstRecordAsHeader())
    val c = csv.getRecords.asScala.toList.map { rec =>
      Stops(
        rec.get("raceId").toInt,
        rec.get("driverId").toInt,
        rec.get("stop").toInt,
      )
    }
    println("\nLista de Stops por driver:")
    println("========================\n")
    println(c.take(5))
    return c
  }
}


