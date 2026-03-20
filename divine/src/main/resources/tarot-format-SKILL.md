# Tarot Format Skill

Purpose
-------
Define a reusable skill that specifies the exact output format for a three-card tarot reading. This file contains the schema, required fields, System and User prompt templates, and example usage so other agents or prompts can include it as a canonical specification.

When to use
-----------
- Use this skill when you want an LLM to return a tarot reading in the exact Markdown template used by the project.
- Load and read this SKILL.md before generating prompts that depend on this format.

Schema (short)
---------------
- `querent`: { name: string|null, question: string|null }
- `cards`: array[3] of objects with fields: position (Past|Present|Future), name, orientation (正位|逆位), keywords[], meaning{short,detailed}, advice, image|null, meta
- `summary`: string
- `quote`: { text: string, source: string } | null
- `closing`: string

System prompt template
----------------------
You are a tarot reading assistant. MUST follow these rules:

1. Output exactly the specified Markdown template (see "Markdown template" section below). No extra text, no JSON, no code fences, no internal chain-of-thought or reasoning details. Only the Markdown formatted reading.
2. All textual content must be in Traditional Chinese.
3. For any missing field, use `null` or leave the textual area empty but preserve the template structure.

User prompt template
--------------------
Provide the following JSON payload (replace example values):

```json
{
  "querent": { "name": "使用者名稱", "question": "使用者問題" },
  "cards": [
    { "position": "Past", "name": "三劍 (Three of Swords)", "orientation": "正位", "keywords": ["心碎","悲傷"], "meaning": { "short": "心碎的真相", "detailed": "(3-6 句詳細說明)" }, "advice": "簡短建議", "image": null, "meta": {} },
    { "position": "Present", "name": "醇和 (Temperance)", "orientation": "正位", "keywords": ["平衡","調和"], "meaning": { "short": "在平衡中癒合", "detailed": "(3-6 句詳細說明)" }, "advice": "簡短建議", "image": null, "meta": {} },
    { "position": "Future", "name": "星星 (The Star)", "orientation": "正位", "keywords": ["希望","靈感"], "meaning": { "short": "心靈的曙光來臨", "detailed": "(3-6 句詳細說明)" }, "advice": "簡短建議", "image": null, "meta": {} }
  ],
  "summary": "過去傷痕 → 現在調和 → 未來曙光",
  "quote": { "text": "創傷是光進入你的地方。", "source": "魯米" },
  "closing": "願這份閱讀如同溫柔的星光，照亮您此刻的路徑。"
}
```

Markdown template (the model MUST output this exact structure, in Traditional Chinese):

### 🔮 抽牌結果
| 位置   | 塔羅牌         | 正逆位 | 關鍵詞與意象簡述                     |
|--------|----------------|--------|--------------------------------------|
| **過去** | {cards[0].name} | {cards[0].orientation} | {join keywords} |
| **現在** | {cards[1].name} | {cards[1].orientation} | {join keywords} |
| **未來** | {cards[2].name} | {cards[2].orientation} | {join keywords} |

---

### 📖 單牌解釋

#### 1. 過去：{cards[0].name}
> **「{cards[0].meaning.short}」**
> {cards[0].meaning.detailed}

#### 2. 現在：{cards[1].name}
> **「{cards[1].meaning.short}」**
> {cards[1].meaning.detailed}

#### 3. 未來：{cards[2].name}
> **「{cards[2].meaning.short}」**
> {cards[2].meaning.detailed}

---

### 🌟 總結：{summary}

---

### ✨ 總結之詩（出處註明）
> **「{quote.text}」**
> — {quote.source}

願這份閱讀如同溫柔的星光，照亮您此刻的路徑。{closing}

Usage notes
-----------
- Place this SKILL.md in the repository and load it before generating prompts that depend on this format.
- When calling a model programmatically, use the System template above then pass the User JSON payload. Enforce the model's output to be plain text matching the Markdown template.
- Do not accept or include any `reasoning_details` or chain-of-thought from the model.
