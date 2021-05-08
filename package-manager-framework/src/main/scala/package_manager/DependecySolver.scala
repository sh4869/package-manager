package package_manager

trait DependencySolver[DependencyType <: Dependency] {
  protected val packageManagerOption: PackageManagerOption
  protected val packageRegistry: PackageRegistry[DependencyType]

  def solve(
      newPackages: Seq[PackageInstallRequest],
      remove: Seq[PackageInfo[DependencyType]],
      installed: Seq[PackageInfo[DependencyType]]
  ): Set[InstallPackage]

  def update(
      installed: Seq[PackageInfo[DependencyType]]
  ): Set[InstallPackage] = {
    packageRegistry.update
    var newPackages = scala.collection.mutable.ListBuffer.empty[PackageInstallRequest]
    var remove = scala.collection.mutable.ListBuffer.empty[PackageInfo[DependencyType]]
    installed.foreach(pack => {
      val packages = packageRegistry.getPackages(pack.name)
      val newPackage = packages
        .filter(p => p.version.isNewerThan(pack.version))
        .sortWith((a, b) => a.version.isNewerThan(b.version))
        .headOption
      if (newPackage.nonEmpty) {
        // newPackages.addOne(PackageInstallRequest(newPackage.get.baseInfo.name, newPackage.get.baseInfo.version))
        remove += pack
      }
    })
    solve(newPackages.toSeq, remove.toSeq, installed)
  }
}
