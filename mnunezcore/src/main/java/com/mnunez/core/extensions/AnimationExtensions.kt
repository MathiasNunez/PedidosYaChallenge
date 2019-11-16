package com.mnunez.core.extensions

import android.animation.Animator
import android.animation.ObjectAnimator
import android.view.View


enum class Anim(val propertyName: String) {
    Alpha("alpha"),
    Rotation("rotation"),
    RotationX("rotationX"),
    RotationY("rotationY"),
    ScaleX("scaleX"),
    ScaleY("scaleY"),
    TranslationX("translationX"),
    TranslationY("translationY"),
    TranslationZ("translationZ"),
    X("x"),
    Y("y"),
    Z("z"),
}

fun View.shake(duration: Long = 800) {
    ObjectAnimator
        .ofFloat(
            this, Anim.TranslationX.propertyName,
            0f, 25f, -25f, 25f, -25f, 15f, -15f, 6f, -6f, 0f
        )
        .setDuration(duration)
        .start()

}

fun View.smoothShake(duration: Long = 1500, f: () -> Unit = {}) {
    ObjectAnimator
        .ofFloat(this, Anim.Rotation.propertyName,
            0f, 25f, -25f, 0f, 25f, -25f, 0f, 25f, -25f, 0f, 25f, -25f, 0f, 25f, -25f, 0f, 25f, -25f, 0f, 25f, -25f, 0f)
        .apply {
            setDuration(duration)
            onEnd { f() }
            start()
        }
}

inline fun ObjectAnimator.onStart(crossinline func: () -> Unit) {
    addListener(object : Animator.AnimatorListener {
        override fun onAnimationRepeat(animation: Animator?) {}
        override fun onAnimationEnd(animation: Animator?) {}
        override fun onAnimationCancel(animation: Animator?) {}
        override fun onAnimationStart(animation: Animator?) {
            func()
        }
    })
}

inline fun ObjectAnimator.onEnd(crossinline func: () -> Unit) {
    addListener(object : Animator.AnimatorListener {
        override fun onAnimationRepeat(animation: Animator?) {}
        override fun onAnimationEnd(animation: Animator?) {
            func()
        }

        override fun onAnimationCancel(animation: Animator?) {}
        override fun onAnimationStart(animation: Animator?) {}
    })
}