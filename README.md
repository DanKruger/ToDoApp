# Todoer

---

Todoer is a simple command-line application for managing notes in a database. It supports creating, updating, reading, listing, and deleting notes by interacting with a database through the terminal.

## Usage

Run the `todoer` program by specifying a command followed by any required arguments.

### Commands:

-   `create`
    Creates a new note in the database.

-   `update <id>`
    Updates the note with the specified `id` in the database.

-   `read <id>`
    Displays the contents of the note with the specified `id`.

-   `list`
    Lists all notes currently stored in the database.

-   `delete <id>`
    Deletes the note with the specified `id` from the database.

### Arguments:

-   `<id>`: A numerical identifier representing a note in the database. This is required for the `update`, `read`, and `delete` commands.

## Examples

```bash
# Create a new note

todoer create

# Update an existing note with id 3

todoer update 3

# Read a note with id 1

todoer read 1

# List all notes in the database

todoer list

# Delete a note with id 5

todoer delete 5
```

For detailed usage information on each command, run `todoer <command>`.
