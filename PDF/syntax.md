# Syntax

## Lexical Conventions

### General 

A PDF file is a sequence of bytes.

The tokens that delimit objects and that describe the structure of a pdf file shall use the ascii character set.

The data values of strings and streams objects may be written either entirely using the ascii character set or entirely in binary data.

A pdf file containing binary data shall be transported as a binary file rather than as a text file to insure that all bytes of the file are faithfully preserved.

Note1. A binary file is not portable to environments that impose reserved character codes, maximum line lengths, end-of-line conventions, or other restrictions.

### Character Set

[white-space characters]

The combination of a carriage return followed immediately by a line feed shall be treated as one eol marker.

[delimiter characters]

a sequence of consecutive regular characters comprises a single token.

pdf is case-sensitive.

### Comments

Any occurrence of the percent sign outside a string or stream introduces a comment.

the comment consists of all characters after the percent sign and up to but not including the end of the line, including regular, delimiter, space and horzontal tab characters.

## Objects

PDF includes eight basis types of objects
- Boolean values.
- Integer and Real numbers.
- Strings.
- Names.
- Arrays.
- Dictionaries.
- Streams.
- null object.

### Numeric Objects

Integer objects

The comment consists of all characters after the percent sign and up to but not including the end of the line, including regular, delimiter, space and horzontal tab characters.

123 32334 + 17 -98 0

Real objects

the value shall be interpreted as a real number and shall be converted to a real object

34.5 -3.62 +123.6 4. -.002 0.0

## String Objects

The length of a string may be subject to implementation limits.

String objects shall be written is one of the floowing two ways.

1. As a sequence of literal characters enclosed in parentheses () "Literal Strings"
2. As hexadecimal data enclosed in angle brackets <> "Hexadecimal Strings"

### Literal Strings

Any characters may appear in a string except unbalanced parentheses `(` `)` `\`, which shall be treated specially as descibed in this sub-clause.

[Escape sequences in literal strings]

The \ddd escape sequence provides a way to represent characters outside the printable ascii character set.

When a document is encrypted, all of its strings are encrypted.

```text
(These \
tow strings \
are the same.
)
(These two strings are the same.)
```

### Hexadecimal Strings

Strings may also be written in hexadecimal form, which is useful for includeing arbitary binary data in a PDF file.

```text
<4E6F7620073686D6F7A206B6120706F702E>
```

Each pair of hexadecimal digits defines one byte of the string.

white-space characters shall be ignored.

if the final digit of a hexadecimal string is missing, if there is an odd number of digits the final digit shall be assumed to be 0.

### Name Objects

When writing a name in a PDF file, a SOLIDUS(`/`) shall be used to introduce a name.

Ordinarily, the bytes making up the name are never treated as text to be presented to a human user or to an application external to a conforming reader.

> However, occasionally the need arises to treat a name object as text, such as one that represents a font name, a colorant name in a separation or DeviceN colour space, or a structure type.

## Array Objects

Unlike arrays in many other computer languages, pdf arrays may be heterogeneous.

an array may have zero elements.

PDF directly supports only one-dimensional arrays.

Arrays of higher dimension can be constructed by using arrays as elements of arrays, nested to any depth.

```text
[549 3.14 false (Ralph) /SomeName]
```

## Dictionary Objects

A dictionary object is an associative table containg pairs of objects.

The first element of each entry is the key and the second element is the value.

```text
<< /Type /Example
   /Subtype /DictionaryExample
   /Version 0.01
   /IntegerItem 12
   /StringItem (a string)
   /Subdictionary << /Item1 0.4
                     /Item2 true
                     /LastItem (not!)
                     /VeryLastItem (OK)
                  >>
>>
```

Dictionary objects are the main building blocks of a pdf document.

They are commonly used to collect and tie together the attributes of a complex object, such as a font or a page of the document, with each entry in the dictionary specifying the name and value of an attribute.

## Stream Objects

```text
dictionary
stream
...Zero or more bytes...
endstream
```

All streams shall be indirect objects and the stream dictionary shall be a direct object.

The stream dictionary specifies the exact number of bytes.

There shall not be any extra bytes, other than white space, between endstream and endobj.

[entries-common-to-all-stream-dictionaries]

## Null Object

## Indirect Objects

The object identifier shall consist of two parts.

1. A positive integer object number. Indirect objects may be numbered sequentially within a PDF file.
2. Nonezero generation numbers may be introduced when the file is later updated.

Together the combination of an object number and a generation number shall uniquely identify an indirect object.

The definition of an indirect object in a PDF file shall consist of its object number and generation number, followed by the value of object bracketed between the keyword `obj` and `endobj`.

```text
12 0 obj
    (brillig)
endobj
```

The object may be referred to from elsewhere in the file by an indirect reference.

Such indirect references shall consist of the object number, the generation number, and the keyword R.

`12 0 R`

