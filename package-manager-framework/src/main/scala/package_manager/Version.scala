package package_manager

trait Version {
    def isNewerThan(that: Version): Boolean
    def toString: String
}