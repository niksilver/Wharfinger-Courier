# User Stories from UC-003: Configure the Tool for First Use

Source: [UC-003 Configure the Tool for First Use](../use-cases/UC-003-configure-the-tool-for-first-use.md)

---

## US-008

**Set up the tool with a TOML configuration file**

> As Nik, I want to create a TOML config file from a template and have the tool validate it on first run so that I know the tool is correctly configured before I attempt a real compile.

**Acceptance criteria:**
- The tool accepts a config file at `courier.toml` in the project directory or `~/.config/courier/config.toml`, with the project-directory file taking precedence.
- Required fields are `pinboard_feed_url`, `cache_dir`, and `output_dir`.
- Optional fields (`max_fetch_attempts`, `max_articles_per_run`, `http_timeout`, `min_word_count`, `max_response_size`) have documented defaults.
- The tool creates `cache_dir` and `output_dir` if they do not already exist.
- A validation success entry is written to `courier.log`.
- A documentation template (`config.example.toml`) is available to copy from.

*Derived from: main success scenario.*

---

## US-009

**See clear errors for invalid or missing configuration**

> As Nik, I want the tool to exit with a precise error message when the config file is missing, unparseable, or has missing required fields so that I can fix the problem without guessing.

**Acceptance criteria:**
- If no config file is found in either search location, the error message names both searched paths.
- If the config file has a syntax error, the error message includes the file path and the parse error (with line number if available).
- If a required field is absent, the error message names each missing field.
- If a directory path is invalid or cannot be created, the error message identifies which directory (`cache_dir` or `output_dir`) failed and why.
- No files are modified when any of these errors occur.

*Derived from: extensions 4a, 4b, 5a, 6a.*

---

## US-010

**Continue setup despite a temporarily unreachable Pinboard feed**

> As Nik, I want a feed connectivity failure during initial setup to produce a warning rather than an error so that I can complete configuration even when I'm offline or Pinboard is temporarily down.

**Acceptance criteria:**
- If the test HTTP request to `pinboard_feed_url` fails during configuration validation, the tool prints a warning describing the failure but does not exit with an error.
- The tool confirms the configuration file is otherwise valid and continues.
- The feed URL will be validated on the first real run.

*Derived from: extension 7a.*
