
# DivineJava

![Divination Eye](img/Title.png)

## 專案簡介

DivineJava 是一個以 Java 21 與 Spring Boot 開發的AI命理占卜 CLI/REST 應用，支援多種占卜方式，並可根據用戶需求自動格式化回應。


## 技術亮點

- 串接 AI API（如 OpenRouter、GPT 等）自動生成占卜解讀

## 主要功能

- 支援多種占卜（塔羅、雷諾曼卡、易經卦象）
- 可以簡易快速新增不同的占卜方式
- 嚴格格式化輸出，適合自動化/AI 應用
- 多語言介面（中/英文）
- CLI 互動式選單與 RESTful API
- Skill 檔案自定義占卜模板

## 安裝與執行

1. 下載專案並安裝 Java 21
2. 編譯專案：
	```bash
	mvn clean package
	```
3. 執行 CLI 版本：
	```bash
	cd divine
	./run.command
	```
4. 或啟動 Spring Boot 伺服器：
	```bash
	mvn spring-boot:run
	```

(之後打算輸出為程式格式)

## Skill 檔案範例

所有占卜格式皆可於 `divine/src/main/resources/` 目錄下自定義（如 tarot-format-SKILL.md、coin-divination-SKILL.md）。

## 專案結構

- `divine/src/main/java/com/otto/divine/`：主程式、服務、控制器
- `divine/src/main/java/com/otto/divine/_openrouter`：AI call控制
- `divine/src/main/resources/`：Skill 模板、設定檔、靜態資源
- `divine/src/main/resources/application.properties` ： AI API Key (暫時是使用作者的免費 OpenRounter API)



