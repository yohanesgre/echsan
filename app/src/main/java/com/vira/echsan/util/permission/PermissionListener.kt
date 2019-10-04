package com.vira.echsan.util.permission

interface PermissionListener {

    /**
     * On permission granted.
     * @param tag         the tag
     * @param permissions the permissions
     */
    fun onPermissionGranted(permissions: Array<String>, tag: String)

    /**
     * On permission denied.
     * @param tag         the tag
     * @param permissions the permissions
     */
    fun onPermissionDenied(permissions: Array<String>, tag: String)

    /**
     * On permission disabled.
     * @param tag         the tag
     * @param permissions the permissions
     */
    fun onPermissionDisabled(permissions: Array<String>, tag: String)
}