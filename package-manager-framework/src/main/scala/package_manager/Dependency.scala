package package_manager

trait Dependency

case class SimpleDependency(name: String, range: VersionRange) 
    extends Dependency

case class OrDependency(list: Seq[SimpleDependency])
    extends Dependency