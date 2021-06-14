package package_manager

case class PackageBaseInfo[V <: Version](
    name: String,
    version: V
)

case class PackageInfo[DependencyType <: Dependency, V <: Version](
    baseInfo: PackageBaseInfo[V], 
    dependencies: Seq[DependencyType],
    conflicts: Seq[ConflictPackage[V]]
) {
    def version: Version =  baseInfo.version
    def name: String = baseInfo.name
}