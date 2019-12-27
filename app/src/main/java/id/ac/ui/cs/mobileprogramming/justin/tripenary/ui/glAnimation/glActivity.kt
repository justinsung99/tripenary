package id.ac.ui.cs.mobileprogramming.justin.tripenary.ui.glAnimation


import android.app.Activity
import android.app.ActivityManager
import android.content.Context
import android.os.Bundle
import android.util.Log


class OpenGLES20Activity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (detectOpenGLES30()) { //so we know it a opengl 3.0 and use our extended GLsurfaceview.
            setContentView(OpenGlSurfaceView(this))
        } else { // This is where you could create an OpenGL ES 2.0 and/or 1.x compatible
// renderer if you wanted to support both ES 1 and ES 2.
            Log.e("openglcube", "OpenGL ES 3.0 not supported on device.  Exiting...")
            finish()
        }
    }

    private fun detectOpenGLES30(): Boolean {
        val am =
            getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
        val info = am.deviceConfigurationInfo
        return info.reqGlEsVersion >= 0x30000
    }
}