package package_manager

trait PackageRegistry[D <: Dependency, V <: Version] {
    def getPackages(name: String): Seq[PackageInfo[D, V]]
    def update: Unit
}