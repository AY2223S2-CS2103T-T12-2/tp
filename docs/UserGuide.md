---
layout: page
title: User Guide
---

CookHub is a **desktop app for managing recipes, optimized for use via a Command Line Interface** (CLI) while still having the benefits of a Graphical User Interface (GUI). This recipe tracking app is targeted for student chefs on a tight budget and schedule with limited ingredients. 


* Table of Contents
{:toc}

--------------------------------------------------------------------------------------------------------------------

## Quick start

1. Ensure you have Java `11` or above installed in your Computer.

1. Download the latest `cookhub.jar` from [here](https://github.com/AY2223S2-CS2103T-W09-1/tp/releases).

1. Copy the file to the folder you want to use as the _home folder_ for your CookHub.

1. Open a command terminal, `cd` into the folder you put the jar file in, and use the `java -jar cookhub.jar` command to run the application.<br>
   A GUI similar to the below should appear in a few seconds. Note how the app contains some sample data.<br>
   ![Ui](images/Ui.png)

1. Type the command in the command box and press Enter to execute it. e.g. typing **`help`** and pressing Enter will open the help window.<br>
   Some example commands you can try:

   * `add t/Watermelon juice d/Quick and easy watermelon juice i/One watermelon s/Juice the watermelon` : Add a new recipe titled `Watermelon juice` that has a description `Quick and easy watermelon juice`, an ingredient of `One watermelon`, and a step of `Juice the watermelon`
   * `delete 1 ` : Delete a recipe at index 1
   * `list` : Lists all recipes
   * `exit`: Exits the app


1. Refer to the [Features](#features) below for details of each command.

--------------------------------------------------------------------------------------------------------------------


## Features

<div markdown="block" class="alert alert-info">

**:information_source: Notes about the command format:**<br>

* Words in `UPPER_CASE` are the parameters to be supplied by the user.<br>
  - e.g. `add t/TITLE d/DESCRIPTION i/INGREDIENT s/STEP` can be `add t/Corndogs d/Delicious i/Flour s/Mix batter`

* Items in square brackets are optional.<br>
  - e.g  `add t/Corndogs d/Delicious i/Flour s/Mix batter` is without the `tag/` flag as it is optional
* Items in between dollar signs, means that at least one of them is required
    - e.g. `edit 1 t/New title` is valid
    - e.g. `edit 1 t/New title d/Another description` is valid
    - e.g. `edit 1` is invalid because one of the flags `t/`, `d/`, `s/`, `i/`, `tag/` is needed
* Items in double dollar signs, means that exactly one of them is required
    - `find r/Corndogs` is valid
    - `find` is not valid
    - `find r/Corndogs i/Flour` is not valid
* Items with `…`​ after them can be used multiple times including zero times.<br>
  - e.g. `add t/Corndogs d/Delicious i/Flour i/Eggs s/Mix batter s/Fry` is valid because the `i/` and `s/` flag can be used multiple times. Since `i/` and `s/` are not surrounded by square brackets, they have to appear at least one time

* Parameters can be in any order, .<br>
    - e.g. `add t/Corndogs d/Delicious i/Flour s/Mix batter` and `add d/Delicious t/Corndogs i/Flour s/Mix batter` are the same
    - e.g. `add t/Corndogs d/Delicious i/Flour s/Mix batter s/Fry` and `add t/Corndogs s/Mix batter d/Delicious i/Flour s/Fry` is the same

* `s/` flags need to be chronological for accurate step numbering
    - e.g. `add t/Corndogs d/Delicious i/Flour s/Mix batter s/Fry` would result in mixing batter before frying, but `add t/Corndogs d/Delicious i/Flour s/Fry s/Mix batter` would result in frying before mixing batter
    - e.g. `add t/Corndogs d/Deliciouss s/Mix batter i/Flour s/Fry`. Since the mixing batter still comes before frying, this is correct

* If a parameter is expected only once in the command but you specified it multiple times, only the last occurrence of the parameter will be taken.<br>
  - e.g. `add t/Corndogs d/Delicious i/Flour s/Mix batter` and `add t/Corndogs d/Awful d/Delicious i/Flour s/Mix batter` are the same as `d/` is only expected once

* Extraneous parameters for commands that do not take in parameters (such as `help`, `list`, `exit` and `clear`) will be ignored.<br>
    - `list`, `exit`, `help`

</div>

### Viewing help : `help`

Shows a message explaining how to access the help page.

![help message](images/helpMessage.png)

Format: `help`


### Add a recipe : `add`

Adds a recipe to the recipe book.

Format: `add t/TITLE d/DESCRIPTION i/INGREDIENT... s/STEP... [t/TAG]...`

:bulb: Tip: A recipe can have any number of tags (including 0)


Examples:

- `add t/Orange juice d/Yummy i/Orange s/Juice the orange` is valid
- `add t/Orange juice d/Yummy i/Orange` is not valid because `s/` is compulsory
- `add t/Orange juice d/Yummy i/Orange i/Sugar s/Juice the orange` is valid because we can have multiple `i/` flags

### Edit a recipe: `edit {recipe number}`
Edits a recipe in the recipe book.

Format: `edit {recipe number} $[t/TITLE] [d/DESCRIPTION] [i/INGREDIENT] [s/STEP] [t/TAG]...$`

- The *recipe number* refers to the index number shown in the displayed recipe book
- The *recipe number* must be a positive integer starting from 1 and must exist in the recipe book


Example:
- `edit 1 t/Corndogs` is valid
- `edit 1` is not valid as at least one flag is required
- `edit 1 t/Corndogs i/200g flour` is valid as multiple flags are accepted


### Delete a recipe : `delete {recipe no.}`
Deletes the recipe at the specified *task number* from the recipe book.

Format: `delete {recipe number}`

- The *recipe number* refers to the index number shown in the displayed recipe book
- The *recipe number* must be a positive integer starting from 1 and must exist in the recipe book

Examples:
- `delete 1`
- `delete 2`


### List recipe : `list`

Lists out all the recipes that you have added to CookHub

Format: `list`

Example: `list`
Expected outcome: You should see a list of all the recipes in CookHub

### Clear recipe : `list`
Clears the entire CookHub of all recipes
Format: `clear`
Expected outcome: You should see that CookHub has zero recipes in it


### Find recipe : `find`
Finds the recipes in CookHub according to what you are looking for

Format: `find $$[r/RECIPE] [t/TITLE] [s/STEP] [i/INGREDIENT] [tag/]$$`
- the flag `r/` searches through the entire recipe and its components
- the flag `t/` searches only through the recipe's title
- the flag `s/` searches only through the recipe's steps
- the flag `i/` seaches only through the recipe's ingredients
- the flag `tag/` searches only through the recipe's tags

Examples:
- `find r/eggs` is valid
- `find r/eggs i/fry the egg` is not valid because at most one flag is allowed
- `find` is not valid as at least one flag is required

### Search recipe with limited ingredients: `only`

Searches for recipes that can be made with only those ingredients

Format: `only INGREDIENT...`
Examples:
- e.g. `only` is not valid as a parameter is needed
- e.g. `only eggs` is valid
- e.g. `only eggs` will search for recipes that have ingredients that at most contain eggs, and nothing more
- e.g. `only eggs ham cheese` will search for recipes that have ingredients that at most contains eggs, ham and cheese. A recipe that only needs eggs will also be valid


---

### Saving the data

CookHub data are saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

### Editing the data file

CookHub data are saved as a JSON file `[JAR file location]/data/cookhub.json`. Advanced users are welcome to 
update data directly by editing that data file.

<div markdown="span" class="alert alert-warning">:exclamation: **Caution:**
If your changes to the data file makes its format invalid, CookHub will discard all data and start with an empty data file at the next run.
</div>

### Archiving data files `[coming in v2.0]`

_Details coming soon ..._

--------------------------------------------------------------------------------------------------------------------

## FAQ

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous CookHub home folder.

--------------------------------------------------------------------------------------------------------------------

## Command summary

| Action | Format, Examples  |
|--------|-------------------|