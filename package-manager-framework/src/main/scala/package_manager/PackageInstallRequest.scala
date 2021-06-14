package package_manager

case class PackageInstallRequest[V <: Version](
    name: String, 
    verison: VersionRange[V]
)