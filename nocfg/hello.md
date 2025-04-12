Alright Sage, here’s a clean and flexible **design doc** for your NOCFG online template system — no code, just architecture, flow, and roles. This is scoped for **Day 5** implementation.

---

## 🧩 Online Template System — Design (NOCFG)

---

### 🎯 **Goal**
Let users:
- Fetch online templates by name (`nocfg template fetch`)
- List all available online templates (`nocfg template list --online`)
- Eventually, allow users to publish templates

---

### 🗂️ Online Repo Structure
**Host:** GitHub repo (e.g., `nocfg-templates`) or your own static server

**Directory layout:**
```
/nocfg-templates/
├── index.json                 <- metadata for listing all templates
├── java-basic.zip             <- zipped template
├── react-frontend.zip         <- zipped template
└── ...more templates
```

---

### 📄 `index.json` Format
This powers the `--online` list and holds template metadata:

```json
[
  {
    "name": "java-basic",
    "description": "Minimal Java Gradle setup",
    "languages": ["java"],
    "tags": ["gradle", "basic"],
    "url": "https://yourhost.com/java-basic.zip"
  },
  {
    "name": "react-frontend",
    "description": "React app with Vite setup",
    "languages": ["javascript"],
    "tags": ["react", "vite"],
    "url": "https://yourhost.com/react-frontend.zip"
  }
]
```

---

### 📦 Template Format
Each `.zip` file should contain a valid folder structure, with the `{{projectName}}` token used for dynamic replacement.

```
/template-root/
├── src/
│   └── Main.java
├── README.md
└── .gitignore
```

---

### 📡 CLI Network Flow

#### `nocfg template list --online`
- Fetch `index.json`
- Print `name`, `description`, and optionally `tags`

#### `nocfg template fetch <name>`
- Match `<name>` from `index.json`
- Download `.zip` from URL
- Extract to `~/.nocfg/templates/<name>/`
- (optional) Add a `fetched.json` locally to track remote info

---

### 🔒 Security & Safety
- Only allow HTTP GET — no remote code execution
- Validate `index.json` format and `zip` structure before extracting
- Warn if template already exists locally

---

### 📈 Future Plans
- `nocfg template publish` (with auth or repo access)
- Template versioning (e.g., `react@2.1`)
- User-defined remote sources (`~/.nocfg/config.json` with custom URLs)

---

Want a diagram too? Or should I turn this into a mini Markdown doc for your repo?