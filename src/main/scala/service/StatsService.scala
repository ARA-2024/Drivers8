package service

import model.{Driver, RaceResult,Stops}

object StatsService {
  // Devuelve un Map con el nombre completo del piloto y la cantidad de podios (posiciones 1-3)
  def topResultsByDriver(
    drivers: List[Driver],
    results: List[RaceResult]
  ): Map[String, Int] = {
    val driverMap = drivers.map(d => d.driverId -> s"${d.forename} ${d.surname}").toMap
    val a = results
      .filter(r => r.position.exists(p => p >= 1 && p <= 3))
      .groupBy(_.driverId)
      .view
      .mapValues(_.size)
      .toMap
      .map { case (driverId, count) =>
        driverMap.getOrElse(driverId, s"ID $driverId") -> count
      }
      //println(s"Mapa de podios por driver:\n ${a}")
      println("\nMapa final de podios por driver:")
      println("================================\n")
      println(a.take(5))
      return a
  }
  
  def stopsResultsByDriver(
    drivers: List[Driver],
    stops: List[Stops]
  ): Map[String, Int] = {
    val driverMap = drivers.map(d => d.driverId -> s"${d.forename} ${d.surname}").toMap
    val b = stops
      .groupBy(_.driverId)
      .map { case (driverId, stopsList) =>
        driverMap.getOrElse(driverId, s"ID $driverId") -> stopsList.size.toInt
      }
    //println(b)
    println("\nMapa final de Stops por driver:")
    println("===============================\n")
    println(b.take(5))
    println(b("George Russell").getClass)
        
    println("\nMapa final de Stops para los 5 primeros drivers:")
  
    var resultado = Map[String, Int]()
      b.foreach { case (k, v) => {
        if(!resultado.contains(v)) {
          println("No existe")
          resultado += (v-> Set(k))
        }else{
          println("Existe")
          resultado(v) += k
          }
        }}
    println(resultado)
    
    return b
  }

}
