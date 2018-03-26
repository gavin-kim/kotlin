package reference.classesAndObjects

/**
 * Top-level
 *
 * public (default) : visible everywhere
 * internal         : visible in a module
 * private          : visible in a file
 *
 * Classes and interfaces
 *
 * public    :
 * internal  : visible in a module
 * protected :
 * private   : visible in a class only
 *
 * Modules : intellij module, Maven project, Gradle source set, a set of files complied with on invocation of the Ant task
 * */


/** Top-level properties directly inside a package. Default public is used */
fun defaultPulbicFunction() {}
class DefaultPublicClass {}

/** visible everywhere but setter is visible on in a file*/
public var visibleEverywhere = 0
    private set

/** visible inside the same module */
internal val visibleInsideTheSameModule = 0

/** visible inside a file */
private val visibleInsideAFile = 0
