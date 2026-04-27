
# ebook-convert

ebook-convert input_file output_file [options]

Convert an e-book from one format to another.

input_file is the input and output_file is the output. Both must be specified as the first two arguments to the command.

The output e-book format is guessed from the file extension of output_file. output_file can also be of the special format .EXT where EXT is the output file extension. In this case, the name of the output file is derived from the name of the input file. Note that the filenames must not start with a hyphen. Finally, if output_file has no extension, then it is treated as a folder and an “open e-book” (OEB) consisting of HTML files is written to that folder. These files are the files that would normally have been passed to the output plugin.

After specifying the input and output file you can customize the conversion by specifying various options. The available options depend on the input and output file types. To get help on them specify the input and output file and then use the -h option.

For full documentation of the conversion system see E-book conversion

Whenever you pass arguments to ebook-convert that have spaces in them, enclose the arguments in quotation marks. For example: “/some path/with spaces”

The options and default values for the options change depending on both the input and output formats, so you should always check with:

ebook-convert myfile.input_format myfile.output_format -h

Below are the options that are common to all conversion, followed by the options specific to every input and output format.

--help, -h

    show this help message and exit

--input-profile

    Specify the input profile. The input profile gives the conversion system information on how to interpret various information in the input document. For example resolution dependent lengths (i.e. lengths in pixels). Choices are: cybookg3, cybook_opus, default, hanlinv3, hanlinv5, illiad, irexdr1000, irexdr800, kindle, msreader, mobipocket, nook, sony, sony300, sony900

--list-recipes

    List builtin recipe names. You can create an e-book from a builtin recipe like this: ebook-convert "Recipe Name.recipe" output.epub

--output-profile

    Specify the output profile. The output profile tells the conversion system how to optimize the created document for the specified device. In some cases, an output profile can be used to optimize the output for a particular device, but this is rarely necessary. Choices are:cybookg3, cybook_opus, default, generic_eink, generic_eink_hd, generic_eink_large, hanlinv3, hanlinv5, illiad, ipad, ipad3, irexdr1000, irexdr800, jetbook5, kindle, kindle_dx, kindle_fire, kindle_oasis, kindle_pw, kindle_pw3, kindle_scribe, kindle_voyage, kobo, msreader, mobipocket, nook, nook_color, nook_hd_plus, pocketbook_inkpad3, pocketbook_lux, pocketbook_hd, pocketbook_900, pocketbook_pro_912, galaxy, sony, sony300, sony900, sony-landscape, sonyt3, tablet

--version

    show program's version number and exit

## Look and Feel

Options to control the look and feel of the output

--asciiize

    Transliterate Unicode characters to an ASCII representation. Use with care because this will replace Unicode characters with ASCII. For instance it will replace "Pelé" with "Pele". Also, note that in cases where there are multiple representations of a character (characters shared by Chinese and Japanese for instance) the representation based on the current calibre interface language will be used.

--base-font-size

    The base font size in pts. All font sizes in the produced book will be rescaled based on this size. By choosing a larger size you can make the fonts in the output bigger and vice versa. By default, when the value is zero, the base font size is chosen based on the output profile you chose.

--change-justification

    Change text justification. A value of "left" converts all justified text in the source to left aligned (i.e. unjustified) text. A value of "justify" converts all unjustified text to justified. A value of "original" (the default) does not change justification in the source file. Note that only some output formats support justification.

--disable-font-rescaling

    Disable all rescaling of font sizes.

--embed-all-fonts

    Embed every font that is referenced in the input document but not already embedded. This will search your system for the fonts, and if found, they will be embedded. Embedding will only work if the format you are converting to supports embedded fonts, such as EPUB, AZW3, DOCX or PDF. Please ensure that you have the proper license for embedding the fonts used in this document.

--embed-font-family

    Embed the specified font family into the book. This specifies the "base" font used for the book. If the input document specifies its own fonts, they may override this base font. You can use the filter style information option to remove fonts from the input document. Note that font embedding only works with some output formats, principally EPUB, AZW3 and DOCX.

--expand-css

    By default, calibre will use the shorthand form for various CSS properties such as margin, padding, border, etc. This option will cause it to use the full expanded form instead. Note that CSS is always expanded when generating EPUB files with the output profile set to one of the Nook profiles as the Nook cannot handle shorthand CSS.

--extra-css

    Either the path to a CSS stylesheet or raw CSS. This CSS will be appended to the style rules from the source file, so it can be used to override those rules.

--filter-css

    A comma separated list of CSS properties that will be removed from all CSS style rules. This is useful if the presence of some style information prevents it from being overridden on your device. For example: font-family,color,margin-left,margin-right

--font-size-mapping

    Mapping from CSS font names to font sizes in pts. An example setting is 12,12,14,16,18,20,22,24. These are the mappings for the sizes xx-small to xx-large, with the final size being for huge fonts. The font rescaling algorithm uses these sizes to intelligently rescale fonts. The default is to use a mapping based on the output profile you chose.

--insert-blank-line

    Insert a blank line between paragraphs. Will not work if the source file does not use paragraphs (<p> or <div> tags).

--insert-blank-line-size

    Set the height of the inserted blank lines (in em). The height of the lines between paragraphs will be twice the value set here.

--keep-ligatures

    Preserve ligatures present in the input document. A ligature is a combined character of a pair of characters like ff, fi, fl et cetera. Most readers do not have support for ligatures in their default fonts, so they are unlikely to render correctly. By default, calibre will turn a ligature into the corresponding pair of normal characters. Note that ligatures here mean only unicode ligatures not ligatures created via CSS or font styles. This option will preserve them instead.

--line-height

    The line height in pts. Controls spacing between consecutive lines of text. Only applies to elements that do not define their own line height. In most cases, the minimum line height option is more useful. By default no line height manipulation is performed.

--linearize-tables

    Some badly designed documents use tables to control the layout of text on the page. When converted these documents often have text that runs off the page and other artifacts. This option will extract the content from the tables and present it in a linear fashion.

--margin-bottom

    Set the bottom margin in pts. Default is 5.0. Setting this to less than zero will cause no margin to be set (the margin setting in the original document will be preserved). Note: Page oriented formats such as PDF and DOCX have their own margin settings that take precedence.

--margin-left

    Set the left margin in pts. Default is 5.0. Setting this to less than zero will cause no margin to be set (the margin setting in the original document will be preserved). Note: Page oriented formats such as PDF and DOCX have their own margin settings that take precedence.

--margin-right

    Set the right margin in pts. Default is 5.0. Setting this to less than zero will cause no margin to be set (the margin setting in the original document will be preserved). Note: Page oriented formats such as PDF and DOCX have their own margin settings that take precedence.

--margin-top

    Set the top margin in pts. Default is 5.0. Setting this to less than zero will cause no margin to be set (the margin setting in the original document will be preserved). Note: Page oriented formats such as PDF and DOCX have their own margin settings that take precedence.

--minimum-line-height

    The minimum line height, as a percentage of the element's calculated font size. calibre will ensure that every element has a line height of at least this setting, irrespective of what the input document specifies. Set to zero to disable. Default is 120%. Use this setting in preference to the direct line height specification, unless you know what you are doing. For example, you can achieve "double spaced" text by setting this to 240.

--remove-paragraph-spacing

    Remove spacing between paragraphs. Also sets an indent on paragraphs of 1.5em. Spacing removal will not work if the source file does not use paragraphs (<p> or <div> tags).

--remove-paragraph-spacing-indent-size

    When calibre removes blank lines between paragraphs, it automatically sets a paragraph indent, to ensure that paragraphs can be easily distinguished. This option controls the width of that indent (in em). If you set this value negative, then the indent specified in the input document is used, that is, calibre does not change the indentation.

--smarten-punctuation

    Convert plain quotes, dashes and ellipsis to their typographically correct equivalents. For details, see: https://daringfireball.net/projects/smartypants.

--subset-embedded-fonts

    Subset all embedded fonts. Every embedded font is reduced to contain only the glyphs used in this document. This decreases the size of the font files. Useful if you are embedding a particularly large font with lots of unused glyphs.

--transform-css-rules

    Path to a file containing rules to transform the CSS styles in this book. The easiest way to create such a file is to use the wizard for creating rules in the calibre GUI. Access it in the "Look & feel->Transform styles" section of the conversion dialog. Once you create the rules, you can use the "Export" button to save them to a file.

--transform-html-rules

    Path to a file containing rules to transform the HTML in this book. The easiest way to create such a file is to use the wizard for creating rules in the calibre GUI. Access it in the "Look & feel->Transform HTML" section of the conversion dialog. Once you create the rules, you can use the "Export" button to save them to a file.

--unsmarten-punctuation

    Convert fancy quotes, dashes and ellipsis to their plain equivalents.

## Structure Detection

Control auto-detection of document structure.

--add-alt-text-to-img

    When an <img> tag has no alt attribute, check the associated image file for metadata that specifies alternate text, and use it to fill in the alt attribute. The alt attribute improves accessibility by providing text descriptions for users who cannot see or fully interpret visual content.

--chapter

    An XPath expression to detect chapter titles. The default is to consider <h1> or <h2> tags that contain the words "chapter", "book", "section", "prologue", "epilogue" or "part" as chapter titles as well as any tags that have class="chapter". The expression used must evaluate to a list of elements. To disable chapter detection, use the expression "/". See the XPath Tutorial in the calibre User Manual for further help on using this feature.

--chapter-mark

    Specify how to mark detected chapters. A value of "pagebreak" will insert page breaks before chapters. A value of "rule" will insert a line before chapters. A value of "none" will disable chapter marking and a value of "both" will use both page breaks and lines to mark chapters.

--disable-remove-fake-margins

    Some documents specify page margins by specifying a left and right margin on each individual paragraph. calibre will try to detect and remove these margins. Sometimes, this can cause the removal of margins that should not have been removed. In this case you can disable the removal.

--insert-metadata

    Insert the book metadata at the start of the book. This is useful if your e-book reader does not support displaying/searching metadata directly.

--page-breaks-before

    An XPath expression. Page breaks are inserted before the specified elements. To disable use the expression: /

--prefer-metadata-cover

    Use the cover detected from the source file in preference to the specified cover.

--remove-first-image

    Remove the first image from the input e-book. Useful if the input document has a cover image that is not identified as a cover. In this case, if you set a cover in calibre, the output document will end up with two cover images if you do not specify this option.

--start-reading-at

    An XPath expression to detect the location in the document at which to start reading. Some e-book reading programs (most prominently the Kindle) use this location as the position at which to open the book. See the XPath tutorial in the calibre User Manual for further help using this feature.

## Table of Contents

Control the automatic generation of a Table of Contents. By default, if the source file has a Table of Contents, it will be used in preference to the automatically generated one.

--duplicate-links-in-toc

    When creating a TOC from links in the input document, allow duplicate entries, i.e. allow more than one entry with the same text, provided that they point to a different location.

--level1-toc

    XPath expression that specifies all tags that should be added to the Table of Contents at level one. If this is specified, it takes precedence over other forms of auto-detection. See the XPath Tutorial in the calibre User Manual for examples.

--level2-toc

    XPath expression that specifies all tags that should be added to the Table of Contents at level two. Each entry is added under the previous level one entry. See the XPath Tutorial in the calibre User Manual for examples.

--level3-toc

    XPath expression that specifies all tags that should be added to the Table of Contents at level three. Each entry is added under the previous level two entry. See the XPath Tutorial in the calibre User Manual for examples.

--max-toc-links

    Maximum number of links to insert into the TOC. Set to 0 to disable. Default is: 50. Links are only added to the TOC if less than the threshold number of chapters were detected.

--no-chapters-in-toc

    Don't add auto-detected chapters to the Table of Contents.

--toc-filter

    Remove entries from the Table of Contents whose titles match the specified regular expression. Matching entries and all their children are removed.

--toc-threshold

    If fewer than this number of chapters is detected, then links are added to the Table of Contents. Default: 6

--use-auto-toc

    Normally, if the source file already has a Table of Contents, it is used in preference to the auto-generated one. With this option, the auto-generated one is always used.

## Metadata

Options to set metadata in the output

--pubdate

    Set the publication date (assumed to be in the local timezone, unless the timezone is explicitly specified)

--publisher

    Set the e-book publisher.

--title

    Set the title.

--title-sort

    The version of the title to be used for sorting.

## Debug

Options to help with debugging the conversion

--debug-pipeline, -d

    Save the output from different stages of the conversion pipeline to the specified folder. Useful if you are unsure at which stage of the conversion process a bug is occurring.

--verbose, -v

    Level of verbosity. Specify multiple times for greater verbosity. Specifying it twice will result in full verbosity, once medium verbosity and zero times least verbosity.


## HTML Input Options

--allow-local-files-outside-root

    Normally, resources linked to by the HTML file or its children will only be allowed if they are in a sub-folder of the original HTML file. This option allows including local files from any location on your computer. This can be a security risk if you are converting untrusted HTML and expecting to distribute the result of the conversion.

--breadth-first

    Traverse links in HTML files breadth first. Normally, they are traversed depth first.

--dont-package

    Normally this input plugin re-arranges all the input files into a standard folder hierarchy. Only use this option if you know what you are doing as it can result in various nasty side effects in the rest of the conversion pipeline.

--input-encoding

    Specify the character encoding of the input document. If set this option will override any encoding declared by the document itself. Particularly useful for documents that do not declare an encoding or that have erroneous encoding declarations.

--max-levels

    Maximum levels of recursion when following links in HTML files. Must be non-negative. 0 implies that no links in the root HTML file are followed. Default is 5.


## AZW3 Output Options

--dont-compress

    Disable compression of the file contents.

--extract-to

    Extract the contents of the generated AZW3 file to the specified folder. The contents of the folder are first deleted, so be careful.

--mobi-toc-at-start

    When adding the Table of Contents to the book, add it at the start of the book instead of the end. Not recommended.

--no-inline-toc

    Don't add Table of Contents to the book. Useful if the book has its own table of contents.

--prefer-author-sort

    When present, use author sort field as author.

--pretty-print

    If specified, the output plugin will try to create output that is as human readable as possible. May not have any effect for some output plugins.

--share-not-sync

    Enable sharing of book content via Facebook etc. on the Kindle. WARNING: Using this feature means that the book will not auto sync its last read position on multiple devices. Complain to Amazon.

--toc-title

    Title for any generated inline table of contents.

