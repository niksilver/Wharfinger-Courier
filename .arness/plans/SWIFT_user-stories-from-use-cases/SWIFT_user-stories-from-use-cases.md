# Swift Plan: User Stories from Use Cases

**Complexity:** moderate
**Scope:** Create `.arness/user-stories/` with a README index and one story file per use case. Stories use lightweight format (role/action/benefit + acceptance criteria), derived from main success scenarios and extensions. Pure documentation — no code, no tests, no architectural impact.
**Architect assessment:** Simple scope, additive only, zero risk. 9 new Markdown files following the `.arness/use-cases/` directory convention.

---

## Files to Modify

| File | Action | Rationale |
|------|--------|-----------|
| `.arness/user-stories/README.md` | Create | Index and traceability matrix linking stories back to source UCs |
| `.arness/user-stories/stories-from-UC-001.md` | Create | Stories from UC-001: Compile Current Reading List |
| `.arness/user-stories/stories-from-UC-002.md` | Create | Stories from UC-002: Compile Archive Reading List |
| `.arness/user-stories/stories-from-UC-003.md` | Create | Stories from UC-003: Configure the Tool for First Use |
| `.arness/user-stories/stories-from-UC-004.md` | Create | Stories from UC-004: Fetch Bookmark Feed |
| `.arness/user-stories/stories-from-UC-005.md` | Create | Stories from UC-005: Fetch and Extract Article |
| `.arness/user-stories/stories-from-UC-006.md` | Create | Stories from UC-006: Compile Kindle Document |
| `.arness/user-stories/stories-from-UC-007.md` | Create | Stories from UC-007: Preview Run Without Changes |
| `.arness/user-stories/stories-from-UC-008.md` | Create | Stories from UC-008: Inspect Run Status |

---

## Patterns to Follow

No code patterns apply — this is documentation. The structural pattern to follow is `.arness/use-cases/README.md`: a summary table index plus individual Markdown documents.

| Pattern | How It Applies |
|---------|---------------|
| `.arness/` subdirectory convention | New `user-stories/` subdirectory sits alongside `use-cases/`, `plans/`, `specs/`, etc. |
| README index with summary table | README lists all stories with UC traceability, matching the use-cases README structure |
| One file per source document | `stories-from-UC-00N.md` mirrors `UC-00N-*.md` naming |

---

## Implementation Tasks

1. **Create directory and README index**
   - Files: `.arness/user-stories/README.md`
   - What: Index with a summary table listing all user stories, their source UC, and priority. Include a brief intro explaining the lightweight format used.
   - Depends on: None

2. **Stories for user-goal UCs (UC-001, UC-002, UC-003)**
   - Files: `stories-from-UC-001.md`, `stories-from-UC-002.md`, `stories-from-UC-003.md`
   - What: Derive stories from main scenarios and extensions for the three user-goal use cases. UC-001 is the largest (4 stories), UC-002 and UC-003 are 3 each.
   - Depends on: Task 1

3. **Stories for subfunction UCs (UC-004, UC-005, UC-006)**
   - Files: `stories-from-UC-004.md`, `stories-from-UC-005.md`, `stories-from-UC-006.md`
   - What: Derive stories for the three subfunction use cases. These are internal pipeline steps; stories are framed from Nik's observable perspective ("so that I get reliable results").
   - Depends on: Task 1

4. **Stories for remaining user-goal UCs (UC-007, UC-008)**
   - Files: `stories-from-UC-007.md`, `stories-from-UC-008.md`
   - What: Derive stories for the preview and status inspection use cases.
   - Depends on: Task 1

---

## Test Plan

### Tests to Update

None — no code changes.

### Tests to Add (Smoke Tests)

None — no code changes.

### Verification Command

```bash
ls .arness/user-stories/
```

---

## Risks & Mitigations

None identified by architect assessment.
