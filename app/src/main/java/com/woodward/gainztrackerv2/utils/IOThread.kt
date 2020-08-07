package com.woodward.gainztrackerv2.utils

import java.util.concurrent.Executors

private val IO_EXECUTOR = Executors.newSingleThreadExecutor()

/**
 * Utility method to run blocks on a dedicated background thread, used for io/database work.
 *
 * User for pre population testing with the Room Db
 */
fun ioThread(f : () -> Unit) {
    IO_EXECUTOR.execute(f)
}