#!/bin/bash
# 這個腳本會自動在 Terminal 開啟並執行 run.command
osascript -e 'tell application "Terminal" to do script "cd \"$(pwd)\"; ./run.command"'
