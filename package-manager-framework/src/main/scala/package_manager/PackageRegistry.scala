package package_manager

trait PackageRegistry[DependencyType <: Dependency] {
    def getPackages(name: String): Seq[PackageInfo[DependencyType]]
    def update: Unit
}