
import service.{DataLoader, StatsService, ChartService}

object Main extends App {
  val drivers = DataLoader.loadDrivers("resources/drivers.csv")
  val results = DataLoader.loadResults("resources/results.csv")
  val topResults = StatsService.topResultsByDriver(drivers, results)
  ChartService.showBarChart(topResults)
  // Gráfica que muestra la cantidad de paradas de 8 pilotos
  val stops = DataLoader.loadStops("resources/pit_stops.csv")
  //println(stops)
  //println(stops.getClass)  // class scala.collection.immutable.$colon$colon
  val stopsResults = StatsService.stopsResultsByDriver(drivers, stops)
  ChartService.showBarChart8(stopsResults)
}
