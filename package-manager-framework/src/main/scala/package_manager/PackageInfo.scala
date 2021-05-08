package package_manager

case class PackageBaseInfo(
    name: String,
    version: Version
)

case class PackageInfo[DependencyType <: Dependency](
    baseInfo: PackageBaseInfo, 
    dependencies: Seq[DependencyType],
    conflicts: Seq[ConflictPackage]
) {
    def version: Version =  baseInfo.version
    def name: String = baseInfo.name
}