#!/bin/sh
echo "uninstalling..."
adb uninstall me.kukkii.janken

echo "installing..."
adb install bin/JankenAndroid-debug.apk

echo "starting logcat..."
adb logcat
