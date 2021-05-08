package package_manager

case class PackageInstallRequest(
    name: String, 
    verison: VersionRange
)