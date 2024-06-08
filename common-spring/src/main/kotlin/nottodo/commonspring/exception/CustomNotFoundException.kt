package nottodo.commonspring.exception

class CustomNotFoundException(
    override val message: String
): RuntimeException(message) {
}
