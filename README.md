# You Should Learn Regex

## Hook + Intro

pattern matching

example of complicated regex

This is a skill that has a much more broad usage than any specific framework or library.

Regex is like a hammer: it's powerful, widely applicable, and completely essential in any software engineer's toolbox.

### Learn once, write anywhere.

Regex can be used is virtually any programming language.

We'll be providing a variety of examples in {{ list languages }}

The reason that we'll be using so many languages is to make the point that Regex is used similarly pretty much anywhere!

Editors (VSCode, Vim, Sublime, Emacs)
Command line

### Useful resources
https://developer.mozilla.org/en-US/docs/Web/JavaScript/Guide/Regular_Expressions
https://regex10-com

## Match Any Number Line

We'll start with a very simple example - Match any line that only contains numbers.

```
^[0-9]+$
```

Let's walk through this piece-by-piece.

- `^` - Signifies the start of a line.
- `[0-9]` - Matches any digit between 0 and -
- `+` - Matches one or more instance of the preceding expression.
- `$` - Signifies the end of the line.

We could re-write this regex in psuedo-English as `[start of line][one or more digits][end of line]`.

Pretty simple right?

> In modern regex implementations, we can replace `[0-9]` with `\d`, which will do the same thing (match any digit).

This is quite easy (and consistant) to test in any number of languages.

We'll use the following input file as an example.
```
1234
abcde
12db2
5362

1
```

Our regular expression should match three lines - 1234, 5362, and 1.

We'll now quickly go through how to perform this regex search in eleven of the most popular/useful programming languages - Javascript, Python, Ruby, Haskell, Perl, PHP, Go, Java, Rust, C++, and Bash.

For instance, here's the implementation in Javascript, with Node.js, reading the input data from a local file - `test.txt`.

#### Javascript
```javascript
const fs = require('fs')

const testFile = fs.readFileSync('test.txt', 'utf8')
const regex = /^([0-9]+)$/gm
let results = testFile.match(regex)
console.log(results)
```

The result will be `[ '1234', '5362', '1' ]`.

Here is the same logic in Python

#### Python
```python
import re

with open('test.txt', 'r') as f:
  test_string = f.read()
  regex = re.compile(r'^([0-9]+)$', re.MULTILINE)
  result = regex.findall(test_string)
  print(result)
```

#### Ruby
In Ruby
```ruby
File.open("test.txt", "rb") do |f|
    test_str = f.read
    re = /^[0-9]+$/m
    test_str.scan(re) do |match|
        puts match.to_s
    end
end
```

#### Haskell
```haskell
import Text.Regex.PCRE

main = do
  fileContents <- readFile "test.txt"
  let stringResult = fileContents =~ "^[0-9]+$" :: AllTextMatches [] String
  print (getAllTextMatches stringResult)
```

#### Perl
```perl
open my $fh, '<', 'test.txt' or die "Unable to open file $!";
read $fh, my $file_content, -s $fh;
close $fh;
my $regex = qr/^([0-9]+)$/mp;
my @matches = $file_content =~ /$regex/g;
print join(',', @matches);
```

#### PHP
```php
<?php
$myfile = fopen("test.txt", "r") or die("Unable to open file.");
$test_str = fread($myfile,filesize("test.txt"));
fclose($myfile);
$re = '/^[0-9]+$/m';
preg_match_all($re, $test_str, $matches, PREG_SET_ORDER, 0);
var_dump($matches);
?>
```

#### Go
```go
package main

import (
    "fmt"
		"io/ioutil"
		"regexp"
)

func main() {
		testFile, err := ioutil.ReadFile("test.txt")
		if err != nil { fmt.Print(err) }
    testString := string(testFile)
		var re = regexp.MustCompile(`(?m)^([0-9]+)$`)
		var results = re.FindAllString(testString, -1)
		fmt.Println(results)
}
```

#### Java
```java
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class FileRegexExample {
  public static void main(String[] args) {
    StringBuilder sb = new StringBuilder();
    try(Stream<String> stream = Files.lines(Paths.get("test.txt"))) {
      	stream.forEach((String line) -> {
          sb.append(line);
          sb.append(System.lineSeparator());
        });
    } catch (IOException e) {
			e.printStackTrace();
		}

    String testString = sb.toString();
    String regex = "^[0-9]+$";
    Pattern pattern = Pattern.compile(regex, Pattern.MULTILINE);
    Matcher matcher = pattern.matcher(testString);

    List<String> matchList = new ArrayList<String>();
    while (matcher.find()) {
      matchList.add(matcher.group());
    }

    System.out.println(matchList);
  }
}
```

#### Rust
```rust
extern crate regex;
use std::fs::File;
use std::io::prelude::*;
use regex::Regex;

fn main() {
  let mut f = File::open("test.txt").expect("file not found");
  let mut test_str = String::new();
  f.read_to_string(&mut test_str).expect("something went wrong reading the file");

  let regex = match Regex::new(r"(?m)^([0-9]+)$") {
    Ok(r) => r,
    Err(e) => {
      println!("Could not compile regex: {}", e);
      return;
    }
  };

  let result = regex.find_iter(&test_str);
  for mat in result {
    println!("{}", &test_str[mat.start()..mat.end()]);
  }
}

```
#### C++
```c++
#include <string>
#include <fstream>
#include <iostream>
#include <sstream>
#include <regex>
using namespace std;

int main () {
  ifstream t("test.txt");
  stringstream buffer;
  buffer << t.rdbuf();
  string testString = buffer.str();

  regex numberLineRegex("(^|\n)([0-9]+)($|\n)");
  sregex_iterator it(testString.begin(), testString.end(), numberLineRegex);
  sregex_iterator it_end;

  while(it != it_end) {
    cout << it -> str();
    ++it;
  }
}
```

```bash
cat test.txt | grep -E "^[0-9]+$"
```

Using the above code, it should be relatively easy to translate the regex script to any other language, such as Typescript/Coffeescript/Elm/Clojurescript (all of which use the Javascript regex engine), Kotlin/Scala/Clojure (which use the Java regex engine), or to C# (which would likely look most similar to a the Java and Javascript examples above).

Writing out the same regex operation in twelve languages was a fun exercise, but we'll be mostly sticking with Javascript and Python for the rest of the tutorial.

## Email Validation

Ok, let's do something slightly more useful.

```
^.[^@\s]+@.[^@\s]+\.\w{2,6}$
```

This is a simplistic regex to match any email address.

- `^` - Start of line
- `.[^@\s]+` - Match any character (except for "@", and whitespace) 1+ times
- `@` - Match the '@' symbol
- `.[^@\s]+` - Match any character (except for "@" and whitespace), 1+ times
- `\.` - Match the '.' character.
- `\w{2,6}` - Match any word character (letter, digit, or underscore), 2-6 times
- `$` - End of line

Hence, we could write this expresion as -
[start of line][1+ characters][@ symbol][1+ characters][period symbol][2-6 characters][end of line]

#### Real-World Example - Validate Email
As an example, let's say we wanted to create a simple Javascript function to check if the input is a valid email.

```javascript
function isValidEmail (input) {
  const regex = /^.[^@\s]+@.[^@\s]+\.\w{2,6}$/g;
  const result = regex.exec(input)
  return result ? true : false
}

console.log(isValidEmail(`test.test@gmail.com`))
console.log(isValidEmail(`test.test`))
console.log(isValidEmail(`gmail.com`))
console.log(isValidEmail(`this is a test@test.com`))
console.log(isValidEmail(`test.test@gmail.comtest.test@gmail.com`))
```

The output of this script should be `true false false false false`.

#### Full Email Regex

This is a very simplistic example which ignores lots of email-validity edge cases.  I don't recommend using this in you applications; it would be best to instead use a reputable email-validation library, or to track down a more complete email validation regex.

For instance, here's a more advanced expression from [emailregex.com](http://emailregex.com/) which matches 99% of RFC 5322 compliant email addresses.

```
(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|"(?:[\x01-\x08\x0b\x0c\x0e-\x1f\x21\x23-\x5b\x5d-\x7f]|\\[\x01-\x09\x0b\x0c\x0e-\x7f])*")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\x01-\x08\x0b\x0c\x0e-\x1f\x21-\x5a\x53-\x7f]|\\[\x01-\x09\x0b\x0c\x0e-\x7f])+)\])
```

Yeah, we're not going to walk through that one.

## Year Matching

Let's go through another simple example - matching any valid year in the 20th or 21st centuries.

```
\b(19|20)\d{2}\b
```

This should be pretty comprehensible given what we've learned so far.

Note that we're starting and ending this regex with `\b` instead of `^` and `$`.  `\b` represents a *word boundry*, or a space between two words.  This will allow us to match years that are not on their own lines, which is of course very useful for search through, say, paragraph text.

- `\b` - Word boundary
- `(19|20)` - Matches either '19' or '20' using the OR ('|') operand.
- `\d{2}` - Two digits, same as `[0-9]{2}`
- `\b` - Word boundary

> Note that `\b` differs from `\s`, the code for a whitespace character.  `\b` searches for a place where a word character is is not followed or preceded by another word-character, so it is essentially searching for the *absence* of a word character, whereas `\s` is searching explicitly for a space character.  `\b` is especially appropratiate for cases where we want to match a specific sequence/word, but not the whitespace before or after it.

As an example, we can use this expression in a Python script to find how many times each year (in the 20th or 21st century) is mentioned in a historical Wikipedia article.

```python
import re
import urllib.request
import operator

# Download wiki page
url = "https://en.wikipedia.org/wiki/Diplomatic_history_of_World_War_II"
html = urllib.request.urlopen(url).read()

# Find all mentioned years in the 20th or 21st century
regex = r"\b(?:19|20)\d{2}\b"
matches = re.findall(regex, str(html))

# Form a dict of the number of occurrences of each year
year_counts = dict((year, matches.count(year)) for year in set(matches))

# Print the dict sorted in descending order
for year in sorted(year_counts, key=year_counts.get, reverse=True):
  print(year, year_counts[year])
```

The above script will result in output like this -
```
1941 112
1945 81
1940 66
1939 59
1943 52
1944 49
...
1954 1
```

## Time Matching

Now we'll define a regex expression to match any time in the 24 hour format (say, 16:59).

```
\b([01]?[0-9]|2[0-3]):([0-5]\d)\b
```

- `\b` - Word boundary
- `[01]` - 0 or 1
- `?` - Signifies that the preceding pattern is optional.
- `[0-9]` - any number between 0 and 9
- `|` - OR
- `2[0-3]` - 2, followed by any number between 0 and 3
- `:` - Matches the `:` character
- `[0-5]` - Any number between 0 and 5
- `\d` - Any number between 0 and 9 (same as `[0-9]`)

#### Capture Groups

You might have noticed something new in the above pattern - we're wrapping the hour and minute capture segments in parenthesis (`( ... )`).  This allows us to define each part of the pattern as a *capture group*.

Capture groups are one of the most powerful features of Regex, since they allow us individually extract and transforms parts of each matched pattern.

#### Real-World Example - Time Parsing

For example, in the above 24-hour pattern, we've defined two capture groups - one for the hour and one for the minute.

We can extract these capture groups easily.

For instance, here's how we could use Javascript to parse a 24-hour formatted time into hours and minutes.

```javascript
const regex = /\b([01]?[0-9]|2[0-3]):([0-5]\d)/
const str = `The current time is 16:24`
const result = regex.exec(str)
console.log(`The current hour is ${result[1]}`)
console.log(`The current minute is ${result[2]}`)
```

> The zeroth capture group is always the entire matched expression.

The above script will output -
```
The current hour is 16
The current minute is 24
```

## Date Matching

Now let's match a `DAY/MONTH/YEAR` style date pattern.

```
\b(0?[1-9]|[12]\d|3[01])([ \/\-])(0?[1-9]|1[012])\2(\d{4})
```

This one is a bit longer, but should look pretty similar to what we've covered already.

- `(0?[1-9]|[12]\d|3[01])` - Match any number between 1 and 31
- `([ \/\-])` - Match the seperator: "/" or "-"
- `(0?[1-9]|1[012])` - Match any number between 1 and 12
- `\2` - Matches the second capture group (the seperator)
- `\d{4}` - Match any 4 digit number (0000 - 9999)

The only new concept here is that we're using `\2` to match the second capture group, which is the divider ("/" or "-").  This enables us avoid repeating our pattern matching specification, and will also require that the dividers are consistant (if the first divider is a "/", then the second must be as well).

#### Capture Group Substitution

Using capture groups, we can dynamically reorganize and transform our string input.

The standard way to refer to capture groups is to use the `$` or `\` symbol, along with the index of the capture group (remember that the zeroth element is the full captured text).

#### Real-World Example - Date Format Transformation

Let's imagine that we were tasked with converting a collection of documents from using the international date format style (`DAY/MONTH/YEAR`) to the American style (`MONTH/DAY/YEAR`)

We could use the above regular expression with a replacement pattern - `$3$2$1$2$4` or `\3\2\1\2\4`.

Let's break the capture groups down.
$1 - First capture group: the day digits.
$2 - Second capture group: the divider.
$3 - Third capture group: the month digits.
$4 - Fourth capture group: the year.

#### Javascript

Here's how we could do this transformation in Javascript -

```javascript
const regex = /\b(0?[1-9]|[12]\d|3[01])([ \/\-])(0?[1-9]|1[012])\2(\d{4})/
const str = `Today's date is 18/09/2017`
const subst = `$3$2$1$2$4`
const result = str.replace(regex, subst)
console.log(result)
```

The above script will print "Today's date is 09/18/2017" to the console.

Here's how the same script would look in Python -

```python
import re
regex = r'\b(0?[1-9]|[12]\d|3[01])([ \/\-])(0?[1-9]|1[012])\2(\d{4})'
test_str = "Today's date is 18/09/2017"
subst = r'\3\2\1\2\4'
result = re.sub(regex, subst, test_str)
print(result)
```

## Code Comment Pattern Matching

One of the most useful ad-hoc usages of regular expressions can be for quickly refactoring code.  Most code editors support regex-based find/replace operations.  A well-formed regex substituion can turn a tedious 30-minute refactor into a single-expression piece of wizardy.

Instead of writing a scripts to perform these operation, try doing them natively in your text editor of choice.  Nearly every text editor supports regex based find-and-replace, here are a few guides for popular editors.

Regex in Sublime - http://docs.sublimetext.info/en/latest/search_and_replace/search_and_replace_overview.html#using-regular-expressions-in-sublime-text

Regex in Vim - http://vimregex.com/#backreferences

Regex in VSCode - https://code.visualstudio.com/docs/editor/codebasics#_advanced-search-options

Regex in emacs - https://www.gnu.org/software/emacs/manual/html_node/emacs/Regexp-Replace.html


#### Single Line CSS Comments

What if we want to find all of the comments with a CSS file?

CSS comments come in the form `/* Comment Here */`

> We're choosing CSS comments here since it has the one of the simplest comment syntaxes: it only allows the near-universal `/* ... */` syntax for multi-line and single-line comments, without the `//`, `#`, `---`, , `<!--`, or `'''` style alternatives found in other languages.  This base expression should be relatively easy to extend with OR operands (`|`) for use with other languages.

To capture any *single-line* CSS comment, we can use the following expression.

```
(\/\*+)(.*)(\*+\/)
```

There are a bunch of `*` symbols here, so let's break it down.

- `\/` - Match `/` symbol (we have to use the `\` escape character)
- `\*+` - Match one or more `*` symbols (again, we have to escape the `*` with `\`).
- `(.*)` - Match any character (besides a new line), any number of times
- `\*+` - Match one or more `*` characters
- `\/` - Match closing `/` symbol.

#### Real-World Example - Convert Single-Line Comments to Multi-Line Comments

We could use this expression to turn each single-line comment into a multi-line comment by performing the following subsitution.

```
$1\n$2\n$3
```

Here, we are simply adding a newline `\n` between each capture group.

If `test.css` has the following contents

```css
/** Double Asterisk Comment */
body {
  background-color: pink;
}

/* Single Asterisk Comment */
h1 {
  font-size: 2rem;
}

/***** Many Asterisk Comment */
h2 {
  font-size: 1rem;
}
```

Then the regex substitution will output

```css
/**
 Double Asterisk Comment
*/
body {
  background-color: pink;
}

/*
 Single Asterisk Comment
*/
h1 {
  font-size: 2rem;
}

/*****
 Many Asterisk Comment
*/
h2 {
  font-size: 1rem;
}
```

#### Real-World Example - Standardize CSS Comment Openings

Let's say we have a big CSS file with some single-line comments starting with `/*`, some with `/**`, and some with `/*****`.

Let's write a regex substituion to standardize all of the single-line comments to use `/*`.

In order to do this, we'll extend our expression to only match comments with *two or more* starting asterisks.

```
(\/\*{2,})(.*)(\*+\/)
```

This expression very similar to the original.  The largest difference is that at the begining we've replaced `\*+` with `\*{2,}`.  The `{2,}` syntax signifies "Two or more" instances of `*`.

Note that we have defined three capture groups in the above expression, for the opening characters (`(\/\*{2,})`), the comment contents (`(.*)`), and the closing characters (`(\*+\/)`).

To standardize the opening of each comment we can pass the following substituion.

```
/*$2$3
```

```css
/** Double Asterisk Comment */
body {
  background-color: pink;
}

/* Single Asterisk Comment */
h1 {
  font-size: 2rem;
}

/***** Many Asterisk Comment */
h2 {
  font-size: 1rem;
}
```

The output will be -
```css
/* Double Asterisk Comment */
body {
  background-color: pink;
}

/* Single Asterisk Comment */
h1 {
  font-size: 2rem;
}

/* Many Asterisk Comment */
h2 {
  font-size: 1rem;
}
```

## URL Matching

Here's a useful regex expression to match any valid URL link.

// https://stackoverflow.com/questions/3809401/what-is-a-good-regular-expression-to-match-a-url
(https?:\/\/)(www\.)?(?<domain>[-a-zA-Z0-9@:%._\+~#=]{2,256}\.[a-z]{2,6})(?<path>\/[-a-zA-Z0-9@:%_\/+.~#?&=]*)?

- `(https?:\/\/)?` - Match http or https (optional)
- `(www\.)?` - Optional "www" prefix
- `(?<domain>[-a-zA-Z0-9@:%._\+~#=]{2,256}` - Match a valid domain name
- `\.[a-z]{2,6})` - Match a domain extension extension (i.e. ".com" or ".org")
- `(?<path>\/[-a-zA-Z0-9@:%_\/+.~#?&=]*)?` - Match URL path (`/posts`), query string (`?limit=1`), and file extension (`.html`), all optional.

#### Named capture groups

You'll notice here that some of the capture groups now begin with a `?<name>`identifier.  This is the syntax for a *named capture group*, which makes the data extraction easier and more clear.

#### Real-World Example - Parse Domain Names From URLs on Webpage

Here's how we could use named capture groups to extract the domain of each URL from a raw webpage HTML string using Python.

```python
import re
import urllib.request

html = str(urllib.request.urlopen("https://moz.com/top500").read())
regex = r"(https?:\/\/)(www\.)?(?P<domain>[-a-zA-Z0-9@:%._\+~#=]{2,256}\.[a-z]{2,6})(?P<path>\/[-a-zA-Z0-9@:%_\/+.~#?&=]*)?"
matches = re.finditer(regex, html)

for match in matches:
  print(match.group('domain'))
```

## Command Line Usage

A final very useful way to utilize regular expressions is in Linux bash commands.
Regular expressions are supported by many Unix command line utilities, here we'll walk through how to use them with `grep` to find specific files, and with `sed` to replace text file content in-place.

#### Real-World Example - Image File Matching With `grep`

Now we'll define another basic regular expression, this time to match image files.

```
^.+\.(?i)(png|jpg|jpeg|gif|webp)$
```

- `^` - Start of line.
- `.` - Match any character (letters, digits, symbols), expect for "\n" (new line).
- `+` - 1+ times.
- `\.` - Match the '.' character.  We have to use the escape character `\`, since `.` on it's own symbolizes any character.
- `(?i)` - Signifies that the next sequence is case-insensitive.
- `(png|jpg|jpeg|gif|webp)` - Matches common image file extensions
- `$` - End of line


For instance, here's how you could list only the image files in your `Downloads` directory.

```bash
ls ~/Downloads | grep -E '^.+\.(?i)(png|jpg|jpeg|gif|webp)$'
```

Here's a breakdown of this command -

- `ls ~/Downloads` - List the files in your downloads directory
- `|` - Pipe the output to the next command
- `grep -E` - Filter the input with regular expression

#### Real-World Example - Email Substitution With `sed`

Another good usage of regular expressions in bash commands could be redacting emails within a text file.

This can be done quite using the `sed` command, along with a modified version of our email regex from earlier.

```bash
sed -E -i 's/^(.*?\s|)[^@]+@[^\s]+/\1\{redacted\}/g' test.txt
```

- `sed` - The Unix "stream editor" utility, which allows for powerful text-stream transformations.
- `-E` - Use extended regex pattern matching
- `-i` - Replace the file stream in-place
- `'s/^(.*?\s|)` - Wrap the beginning of the line in a capture group
- `[^@]+@[^\s]+` - Simplified version of our email regex.
- `/\1\{redacted\}/g'` - Replace the content with the first capture group `\1` and `{redacted}` in place of the email address.
- `test.txt` - Perform the operation on the `test.txt` file.

Warning - This command will automatically remove all email addresses from any `test.txt` that you pass it, so be careful where/when you run it, since *this operation cannot be reveresed*.  To preview the results, simply omit the `-i` flag.

> Note - While the above command should work on most Linux distributions, the macOS uses the BSD implementation is `sed`, which is more limited.  To use `sed` on macOS with decent regex support, I would recomend installing the GNU implementation of `sed` (`brew install gnu-sed`).


```
My email is patrick.triest@gmail.com
```

The result will be a text file with the email redacted:

```
My email is {redacted}
```


## Conclusion

- you can also use with SQL, btw

#### When NOT To Use Regex
- Is a specialized parser available (ex. HTML, JSON, XML), since a full syntax tree implementation is more appropriate
- Is a specialized library available (ex. email validation)
-

#### Resources -
https://code.tutsplus.com/tutorials/8-regular-expressions-you-should-know--net-6149
https://stackoverflow.com/questions/1449817/what-are-some-of-the-most-useful-regular-expressions-for-programmers
https://projects.lukehaas.me/regexhub/
http://www.mkyong.com/regular-expressions/10-java-regular-expression-examples-you-should-know/
http://www.aivosto.com/vbtips/regex.html