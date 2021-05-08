package package_manager

case class PackageManagerOption(
    canInstallMultipleVersion: Boolean,
    canSpecifyConflictPacakge: Boolean,
    allowCircularDependency: Boolean
)
