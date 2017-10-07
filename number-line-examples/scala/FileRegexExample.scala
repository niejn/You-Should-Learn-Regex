import scala.io.Source
import scala.util.matching.Regex

object FileRegexExample {
  def main(args: Array[String]) {
    val fileContents = Source.fromFile("../test.txt").getLines.mkString("\n")
    val pattern = "(?m)^[0-9]+$".r
    println(fileContents)
    println((pattern findAllIn fileContents).mkString(","))
  }
}