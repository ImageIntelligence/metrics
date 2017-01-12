import sbt._

object Depend {
  lazy val scalazVersion = "7.2.6"

  lazy val scalaz = Seq(
    "org.scalaz" %% "scalaz-core"
  ).map(_ % scalazVersion)

  lazy val statsDClient = Seq(
    "com.timgroup" % "java-statsd-client" % "3.0.1"
  )

  lazy val scalaTestCheck = Seq(
    "org.scalatest"   %% "scalatest"                 % "2.2.4",
    "org.scalacheck"  %% "scalacheck"                % "1.12.1"
  ).map(_.withSources).map(_ % "test")

  lazy val depResolvers = Seq(
    "Scalaz Bintray Repo" at "http://dl.bintray.com/scalaz/releases",
    Resolver.sonatypeRepo("releases")
  )

  lazy val dependencies = 
    scalaz ++
    statsDClient ++
    scalaTestCheck
}
