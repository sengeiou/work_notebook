@echo off
adb shell monkey -p com.carocean.radio --ignore-crashes  --ignore-timeouts --ignore-security-exceptions  --pct-trackball 0 --pct-nav 0 --pct-majornav 0 --pct-anyevent 0  --pct-syskeys 0 -v -v -v --throttle 500 1200000000 > /storage/emulated/0/monkeysys.txt 2>&1 &
pause