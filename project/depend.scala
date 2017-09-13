import sbt._

object Depend {
  lazy val catsVersion = "1.0.0-MF"

  lazy val cats = Seq(
    "org.typelevel" %% "cats-core"
  ).map(_ % catsVersion)

  lazy val dogStatsDClient = Seq(
    "com.datadoghq" % "java-dogstatsd-client" % "2.3"
  )

  lazy val depResolvers = Seq(
    "Scalaz Bintray Repo" at "http://dl.bintray.com/scalaz/releases",
    Resolver.sonatypeRepo("releases")
  )

  lazy val dependencies =
    cats ++
    dogStatsDClient
}
