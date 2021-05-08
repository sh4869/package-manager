package package_manager

case class ConflictPackage(name: String, version: Option[VersionRange])
