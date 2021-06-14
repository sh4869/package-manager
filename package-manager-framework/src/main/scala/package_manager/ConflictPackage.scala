package package_manager

case class ConflictPackage[V <: Version](name: String, version: Option[VersionRange[V]])
