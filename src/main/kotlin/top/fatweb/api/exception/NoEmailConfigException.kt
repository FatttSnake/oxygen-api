package top.fatweb.api.exception

class NoEmailConfigException(
    vararg configs: String
) : RuntimeException("Email settings not configured: ${configs.joinToString(", ")}")