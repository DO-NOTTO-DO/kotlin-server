package nottodo.commonspring.exception

class CustomBadRequestException(
    override val message: String
) : RuntimeException(message) {
}
