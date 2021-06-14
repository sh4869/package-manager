package package_manager

trait PackageRegistry[DependencyType <: Dependency, V <: Version] {
    def getPackages(name: String): Seq[PackageInfo[DependencyType, V]]
    def update: Unit
}