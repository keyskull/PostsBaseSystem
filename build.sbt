name := "PostsBaseSystem"

version := "1.0"

scalaVersion := "2.11.7"

libraryDependencies ++= Seq(
  "io.netty" % "netty-all" % "4.1.6.Final",
  "org.ethereum" % "ethereumj-core" % "1.3.6-RELEASE",
  "com.typesafe.akka" % "akka-actor_2.11" % "2.4.8",
  "com.typesafe.akka" % "akka-testkit_2.11" % "2.4.8",
  "com.typesafe.akka" % "akka-slf4j_2.11" % "2.4.8",
  "com.typesafe.akka" % "akka-cluster_2.11" % "2.4.8",
  "org.xerial" % "sqlite-jdbc" % "3.14.2.1",
  "org.scalacheck" %% "scalacheck" % "1.13.2" % "test",
  "org.scalatest" %% "scalatest" % "3.0.0" % "test")

