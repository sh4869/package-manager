package package_manager

trait Dependency

case class SimpleDependency[V <: Version](name: String, range: VersionRange[V])
    extends Dependency

case class OrDependency[V <: Version](list: Seq[SimpleDependency[V]])
    extends Dependency