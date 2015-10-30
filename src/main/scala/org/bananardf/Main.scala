package org.bananardf

import org.openrdf.sail._
import org.openrdf.repository._
import org.openrdf.repository.sail._
import org.openrdf.sail.memory._
import org.openrdf.sail.inferencer.fc.ForwardChainingRDFSInferencer
import org.openrdf.model._, impl._
import org.openrdf.model.vocabulary._

object Main {

  def main(args: Array[String]): Unit = {

    val sail: Sail = new ForwardChainingRDFSInferencer(new MemoryStore())
    val rep: Repository = new SailRepository(sail)

    rep.initialize()

    val namespace = ""

    val f: ValueFactory = SimpleValueFactory.getInstance()
    import f.{ createIRI => iri }

    val conn: RepositoryConnection = rep.getConnection()

    conn.add(iri("class:A"), RDFS.SUBCLASSOF, iri("class:B"))
    conn.add(iri("class:B"), RDFS.SUBCLASSOF, iri("class:C"))
    conn.add(iri("class:B"), RDFS.SUBCLASSOF, iri("class:CC"))
    conn.add(iri("class:C"), RDFS.SUBCLASSOF, iri("class:D"))

    val result = conn.getStatements(iri("class:A"), RDFS.SUBCLASSOF, null, null)
    import scala.collection.JavaConverters._
    import info.aduna.iteration._
    Iterations.asList(result).asScala.foreach(println)

    conn.close()

  }

}
