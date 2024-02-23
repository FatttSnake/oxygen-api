package top.fatweb.oxygen.api.service.api.v1

import top.fatweb.oxygen.api.param.api.v1.avatar.AvatarBaseParam
import top.fatweb.oxygen.api.param.api.v1.avatar.AvatarGitHubParam
import top.fatweb.oxygen.api.vo.api.v1.avatar.AvatarBase64Vo

/**
 * Avatar service interface
 *
 * @author FatttSnake, fatttsnake@gmail.com
 * @since 1.0.0
 */
interface IAvatarService {
    /**
     * Generate random avatar
     *
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     */
    fun random(avatarBaseParam: AvatarBaseParam?): ByteArray

    /**
     * Generate random avatar as base64
     *
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     */
    fun randomBase64(avatarBaseParam: AvatarBaseParam?): AvatarBase64Vo

    /**
     * Generate triangle style avatar
     *
     * @param avatarBaseParam Avatar base parameters
     * @return Avatar byte array
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     * @see AvatarBaseParam
     * @see ByteArray
     */
    fun triangle(avatarBaseParam: AvatarBaseParam?): ByteArray

    /**
     * Generate triangle style avatar as base64
     *
     * @param avatarBaseParam Avatar base parameters
     * @return AvatarBase64Vo object
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     * @see AvatarBaseParam
     * @see AvatarBase64Vo
     */
    fun triangleBase64(avatarBaseParam: AvatarBaseParam?): AvatarBase64Vo

    /**
     * Generate square style avatar
     *
     * @param avatarBaseParam Avatar base parameters
     * @return Avatar byte array
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     * @see AvatarBaseParam
     * @see ByteArray
     */
    fun square(avatarBaseParam: AvatarBaseParam?): ByteArray

    /**
     * Generate square style avatar as base64
     *
     * @param avatarBaseParam Avatar base parameters
     * @return AvatarBase64Vo object
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     * @see AvatarBaseParam
     * @see AvatarBase64Vo
     */
    fun squareBase64(avatarBaseParam: AvatarBaseParam?): AvatarBase64Vo

    /**
     * Generate identicon style avatar
     *
     * @param avatarBaseParam Avatar base parameters
     * @return Avatar byte array
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     * @see AvatarBaseParam
     * @see ByteArray
     */
    fun identicon(avatarBaseParam: AvatarBaseParam?): ByteArray

    /**
     * Generate identicon style avatar as base64
     *
     * @param avatarBaseParam Avatar base parameters
     * @return AvatarBase64Vo object
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     * @see AvatarBaseParam
     * @see AvatarBase64Vo
     */
    fun identiconBase64(avatarBaseParam: AvatarBaseParam?): AvatarBase64Vo

    /**
     * Generate GitHub style avatar
     *
     * @param avatarGitHubParam Avatar base parameters
     * @return Avatar byte array
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     * @see AvatarBaseParam
     * @see ByteArray
     */
    fun github(avatarGitHubParam: AvatarGitHubParam?): ByteArray

    /**
     * Generate GitHub style avatar as base64
     *
     * @param avatarGitHubParam Avatar base parameters
     * @return AvatarBase64Vo object
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     * @see AvatarBaseParam
     * @see AvatarBase64Vo
     */
    fun githubBase64(avatarGitHubParam: AvatarGitHubParam?): AvatarBase64Vo
}