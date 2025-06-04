package service
import scala.jdk.CollectionConverters._
import org.knowm.xchart.{CategoryChart, CategoryChartBuilder, SwingWrapper}

object ChartService {
  def showBarChart(data: Map[String, Int]): Unit = {
    val chart: CategoryChart = new CategoryChartBuilder()
      .width(1200).height(800)
      .title("Podios por piloto")
      .xAxisTitle("Piloto")
      .yAxisTitle("Podios (top 3)")
      .build()

    val (names, counts) = data.toSeq.sortBy(-_._2).unzip
    chart.addSeries("Podios", names.take(10).toList.asJava, counts.take(10).toList.map(_.asInstanceOf[Number]).asJava)
    new SwingWrapper(chart).displayChart()
    //Thread.sleep(20000)
    Thread.sleep(300)
  }

  def showBarChart8(data: Map[String, Int]): Unit = {
    val chart: CategoryChart = new CategoryChartBuilder()
      .width(1200).height(800)
      .title("Paradas por piloto")
      .xAxisTitle("Piloto")
      .yAxisTitle("Paradas")
      .build()

    val (names, stops) = data.toSeq.sortBy(-_._2).unzip
    chart.addSeries("Paradas", names.take(8).toList.asJava, stops.take(8).toList.map(_.asInstanceOf[Number]).asJava)
    new SwingWrapper(chart).displayChart()
    Thread.sleep(300)
  }











}
