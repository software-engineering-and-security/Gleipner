#include "gleipner_chains_jni_Custom001Trampoline.h"
#include <jni.h>

JNIEXPORT void JNICALL Java_gleipner_chains_jni_Custom001Trampoline_native0  (JNIEnv *env, jobject obj) {
    jclass cls = env->GetObjectClass(obj);
    jmethodID mid = env->GetMethodID(cls, "target", "()V");
    if (mid == 0) return;
    env->CallVoidMethod(obj, mid);
}
