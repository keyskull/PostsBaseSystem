name := "PostsBaseSystem"

version := "1.0"

scalaVersion := "2.11.7"

libraryDependencies ++= Seq(
  "com.typesafe.akka" % "akka-actor_2.11" % "2.4.8",
  "com.typesafe.akka" % "akka-testkit_2.11" % "2.4.8",
  "com.typesafe.akka" % "akka-slf4j_2.11" % "2.4.8",
  "com.typesafe.akka" % "akka-cluster_2.11" % "2.4.8",
  "org.xerial" % "sqlite-jdbc" % "3.14.2.1")
